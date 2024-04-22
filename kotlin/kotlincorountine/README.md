# 协程是什么？
协程基于线程，它是轻量级线程

# 协程的调度器
1. Dispatchers.Main：Android上的主线程，用来处理UI交互和一些轻量级任务： 调用suspend函数、调用UI函数、更新LiveData
2. Dispatchers.IO：非主线程，专为磁盘和网络IO进行了优化：数据库、文件读写、网络处理
3. Dispatchers.Default：非主线程，专为CPU密集型任务进行了优化：数组排序，JSON数据解析，处理差异判断

# 协程的启动模式
1. DEFAULT：协程创建后，立即开始调度，在调度前如果协程被取消，其将直接进入取消响应的状态
2. ATOMIC：协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消
3. LAZY：只有协程被需要时，包括主动调用协程的start、join或者await等函数时才会开始调度
        如果调度前被取消，那么该协程将直接进入异常结束状态
4. UNDISPATCHED：协程创建后立即在当前函数调用栈中执行，直到遇到第一个真正挂起的点

# 协程的作用域构建器
>runBlocking是常规函数，而coroutineScope是挂起函数
>它们都会等其协程体以及所有子协程结束，主要区别在于runBlocking方法会阻塞当前线程来等待，而coroutineScope只是挂起
>会释放底层线程用于其他用途
1. coroutineScope：一个协程失败了，所有其他兄弟协程也会被取消
2. supervisorScope:一个协程失败了，不会影响其他兄弟协程
3. 自身异常时，作用域内的其他协程也会被取消

# Job对象的生命周期
- new -> active -> Completing -> Completed
- (active、Completing) -> canceling -> canceled

# 协程的取消
1. 取消作用域会取消它的子协程
2. 被取消的子协程并不会影响其余兄弟协程
3. 协程通过抛出一个特殊的异常CancellationException来处理取消操作
4. 所有Kotlinx.coroutines中的挂起函数（withContext、delay等），都是可取消的
## CPU密集型任务取消
- isActive是一个可以被使用在CoroutineScope中的扩展属性，检查job是否处于活跃状态
- ensureActive(),如果Job处于非活跃状态，这个方法会立即抛出异常。
- yield函数会检查所在协程的状态，如果已经取消，则抛出CancellationException予以响应。此外它还会尝试出让线程的执行权，
给其他协程提供执行机会。
## 协程取消的副作用
- 在finally中释放资源。
- use函数：该函数只能被实现了Closeable的对象使用，程序结束的时候会自动调用close方法，适合文件对象。
## 不能取消的任务
- 处于取消中状态的协程不能够挂起（运行不能取消的代码），当协程被取消后需要调用挂起函数，我们需要将清理任务的代码放置
与NonCancellable CoroutineContext中。 这样会挂起运行中的代码，并保持协程的取消中状态直到任务处理完成。

# 超时任务
- 很多情况下取消一个协程的理由是它有可能超时。
- withTimeoutOrNull通过返回null来进行超时操作，从而替代抛出一个异常。

# 协程的异常处理
## 协程的上下文
CoroutineContext是一组用于定义协程行为的元素。它由如下几项构成
- job：控制协程的生命周期
- CoroutineDispatcher：向合适的线程分发任务
- CoroutineName：协程的名称，调试的时候很有用
- CoroutineExceptionHandler：处理未被捕获的异常
### 组合上下文的元素 
有时我们需要在协程上下文中定义多个元素。我们可以使用+操作符来实现。比如说，我们可以显式地指定一个调度器来启动协程并且同时显
式指定一个命名。
### 协程上下文的继承
对于新创建的协程，它的CoroutineContext会包含一个全新的Job实例，它会帮助我们控制协程的生命周期。而
剩下的元素会从CoroutineContext的父类继承，该父类可能是另外一个协程或者创建该协程的CoroutineScope。

``协程的上下文 = 默认值 + 继承的CoroutineContext + 参数``
- 一些元素包含默认值：Dispatchers.Default是默认的CoroutineDispatcher,以及”coroutine“作为默认的CoroutineName;
- 继承的CoroutineContext是CoroutineScope或者其父协程的CoroutineContext;
- 传入协程构建器的参数的优先级高于继承的上下文参数，因此会覆盖对应的参数值。

## 协程的异常处理
当应用出现一些意外情况时，给用户提供合适的体验非常重要，一方面，目睹应用崩溃是个很糟糕的体验，另一外面，在用户操作失败时，
也必须要能给出正确的提示信息。
- 协程构建器有两种形式：自动传播异常（launch与actor)，向用户暴露异常（async与produce)当这些构建器用于创建一个根协程时（
  该协程不是另一个协程的子协程），前者这类构建器，异常会在它发生的第一时间被抛出，而后者则依赖用户来最终消费异常，例如通过
  await或receiver。
- 其他协程所创建的协程中，产生的异常总是会被传播。
## 异常的传播特性
``当一个协程由于一个异常而运行失败时，它会传播这个异常并传递给它的父级。接下来，父级会进行下面几步操作：``
- 取消它自己的子级
- 取消它自己
- 将异常传播并传递给它的父级 
### SupervisorJob
- 使用SupervisorJob时，一个子协程的运行失败不会影响到其他的子协程。SupervisorJob不会传播异常给它的父级，它会让子协程
  自己处理异常
### supervisorScope
当作业自身执行失败的时候，所有子作业将会被全部取消

## 异常的捕获
- 使用CoroutineExceptionHandler对协程的异常进行捕获。
- 以下条件被满足时，异常就会被捕获：
  - ``时机``：异常是被自动抛出异常的协程所抛出的（使用launch,而不是async时） 
  - ``位置``：在CoroutineScope的CoroutineContext中或在一个根协程（CoroutineScope或者supervisorScope的直接子协程）
  中

## 全局异常处理
- 全局异常处理可以获取到所有协程未处理的未捕获异常，不过它并不能对异常进行捕获，虽然不能阻止程序崩溃，全局异常处理器在程序
调试和异常上报等场景中仍然有非常大的用处。
- 我们需要在classpath下面创建META-INF/services目录，并在其中创建一个名为kotlinx.coroutines.CoroutineExceptionHandler
的文件，文件内容就是我们的全局异常处理器的全类名。

## 取消与异常
- 取消与异常紧密相关，协程内部使用CancellationException来进行取消，这个异常会被忽略
- 当子协程被取消时，不会取消它的父协程。
- 如果一个协程遇到了CancellationException以外的异常，它将使用该异常取消它的父协程。当父协程的所有子协程都结束后，异常才
会被父协程处理。

## 异常聚合
当协程的多个子协程因为异常失败时，一般情况下取第一个异常进行处理。在第一个异常之后发生的所有其他异常，都将绑定到第一个异常上。

# Flow-异步流
## 认识
### 特性
### 构建器与上下文
### 启动
### 取消与取消检测
### 缓冲

## 操作符
### 过渡操作符
### 末端操作符
### 组合
### 展平

## 异常
### 异常处理
### 完成
