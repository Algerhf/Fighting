# 一、认识协程
## 1.1 为什么协程理解起来有难度？
- Java中不曾出现的，新概念。
- 概念不清晰，我们看到的大都是不同语言对于协程的实现或衍生。
- Kotlin基础不扎实。
- 多线程编程基础太薄弱。

## 1.2 协程是什么？
- 协程基于线程，它是轻量级线程。
- 协程让``异步逻辑同步化``，杜绝回调地狱。
- 协程最核心的点就是，函数或一段程序能够被``挂起``，稍后再在挂起的位置``恢复``。

## 1.3 在Android中协程用来解决什么问题？
- ``处理耗时任务``，这种任务常常会阻塞主线程。
- ``保证主线程安全``，即确保安全地从主线程调用任何suspend函数。

## 1.4 协程的挂起与恢复
常规函数基础操作包括：invoke（或call）和return,协程新增了``suspend``和``resume``。
- ``suspend``：也称为挂起或暂停，用于暂停执行当前协程，并保存所有局部变量。
- ``resume``：用于让已暂停的协程从其暂停处继续执行。

## 1.5 挂起函数
- 使用suspend关键字修饰的函数叫作挂起函数。
- 挂起函数只能在``协程体内``或其他``挂起函数内``调用。

## 1.6 协程的两部分实现分为两个层次
基础设施层：标准库的协程API,主要对协程提供了概念和语义上最基本的支持。
业务框架层：协程的上层框架支持。

## 1.7 协程的调度器
所有协程必须在调度器中运行，即使他们在主线程上运行也是如此。
1. Dispatchers.Main：Android上的主线程，用来处理UI交互和一些轻量级任务： 调用suspend函数、调用UI函数、更新LiveData。
2. Dispatchers.IO：非主线程，专为磁盘和网络IO进行了优化：数据库、文件读写、网络处理。
3. Dispatchers.Default：非主线程，专为CPU密集型任务进行了优化：数组排序，JSON数据解析，处理差异判断。

## 1.8 任务泄露
当某个协程任务丢失，无法追踪，会导致内存、CPU、磁盘等资源浪费，甚至发送一个无用的网络请求，这种情况称为任务泄露。
为了能够避免协程泄露，Kotlin引入了结构化并发。

## 1.9 结构化并发
使用结构化并发可以做到：
- ``取消任务``：当某项任务不再需要时取消它。
- ``追踪任务``：当任务正在执行时，追踪它。
- ``发出错误信号``：当协程失败时，发出错误信号表明有错误发生。

## 1.10 CoroutineScope
- 定义协程必须指定其CoroutineScope，它会跟踪所有协程，同样它还可以取消由它所启动的所有协程。
- 常用的相关API有：
  - ``GlobalScope``：生命周期是process级别的，即使Activity或Fragment已经被销毁，协程仍然在执行。
  - ``MainScope``：在Activity中使用，可以在OnDestroy中取消协程。
  - ``viewModelScope``：只能在ViewModel中使用，绑定ViewModel的生命周期。
  - ``lifecycleScope``：只能在Activity、Fragment中使用，会绑定Activity和Fragment的生命周期。
 
# 二、协程的启动与取消
## 2.1 协程的启动
### 2.1.1 协程构建器
- launch与async构建器都用来启动新协程。
  - ``launch``：返回一个Job并且不附带任何结果值。
  - ``async``：返回一个Deferred,Deferred也是一个Job,可以使用.await()在一个延期的值上得到它的最终结果。
- 等待一个作业 
  - join与await。
  - 组合并发。

### 2.1.2 协程的启动模式
1. DEFAULT：协程创建后，立即开始调度，在调度前如果协程被取消，其将直接进入取消响应的状态。
2. ATOMIC：协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消。
3. LAZY：只有协程被需要时，包括主动调用协程的start、join或者await等函数时才会开始调度，如果调度前被取消，那么该协程将
直接进入异常结束状态。
4. UNDISPATCHED：协程创建后立即在``当前函数调用栈``中执行，直到遇到第一个真正挂起的点。

### 2.1.3 协程的作用域构建器
>runBlocking是常规函数，而coroutineScope是挂起函数
>它们都会等其协程体以及所有子协程结束，主要区别在于runBlocking方法会阻塞当前线程来等待，而coroutineScope只是挂起
>会释放底层线程用于其他用途
1. ``coroutineScope``：一个协程失败了，所有其他兄弟协程也会被取消。
2. ``supervisorScope``:一个协程失败了，不会影响其他兄弟协程。
3. 自身异常时，作用域内的其他协程也会被取消。

### 2.1.4 Job对象的生命周期
- 对于每一个创建的协程（通过launch或者async）,会返回一个Job实例，该实例是协程的唯一标示，并且负责管理协程的生命周期。
- 一个任务可以包含一系列状态：新创建（New）、活跃（active）、完成中（Completing）、已完成（Completed）、取消中（canceling）
 和已取消（canceled）。虽然我们无法直接访问这些状态，但是我们可以访问Job的属性，isActive、isCancelled和isCompleted。

- 如果协程处于活跃状态，协程运行出错或者调用job.cancel()都会将当前任务置为取消中（Canceling）状态（isActive = false）
isCancelled = true)。当所有的子协程都完成后，协程会进入已取消（Canceled）状态，此时isCompleted = true。
-  new -> active -> Completing -> Completed
- (active、Completing) -> canceling -> canceled

## 2.2 协程的取消
### 2.2.1 协程的取消
1. 取消作用域会取消它的子协程。
2. 被取消的子协程并不会影响其余兄弟协程。
3. 协程通过抛出一个特殊的异常CancellationException来处理取消操作。
4. 所有Kotlinx.coroutines中的挂起函数（withContext、delay等），都是可取消的。
### 2.2.2 CPU密集型任务的取消
- isActive是一个可以被使用在CoroutineScope中的扩展属性，检查job是否处于活跃状态。
- ensureActive(),如果Job处于非活跃状态，这个方法会立即抛出异常。
- yield函数会检查所在协程的状态，如果已经取消，则抛出CancellationException予以响应。此外它还会尝试出让线程的执行权，
给其他协程提供执行机会。   
### 2.2.3 协程取消的副作用
- 在finally中释放资源。
- use函数：该函数只能被实现了Closeable的对象使用，程序结束的时候会自动调用close方法，适合文件对象。
### 2.2.4 不能取消的任务
- 处于取消中状态的协程不能够挂起（运行不能取消的代码），当协程被取消后需要调用挂起函数，我们需要将清理任务的代码放置。
于NonCancellable CoroutineContext中。 这样会挂起运行中的代码，并保持协程的取消中状态直到任务处理完成。
### 2.2.5 超时任务
- 很多情况下取消一个协程的理由是它有可能超时。
- withTimeoutOrNull通过返回null来进行超时操作，从而替代抛出一个异常。

# 三、协程的异常处理
## 3.1 协程的上下文
``CoroutineContext``是一组用于定义协程行为的元素。它由如下几项构成
- ``job``：控制协程的生命周期
- ``CoroutineDispatcher``：向合适的线程分发任务
- ``CoroutineName``：协程的名称，调试的时候很有用
- ``CoroutineExceptionHandler``：处理未被捕获的异常
### 3.1.1 组合上下文的元素 
有时我们需要在协程上下文中定义多个元素。我们可以使用+操作符来实现。比如说，我们可以显式地指定一个调度器来启动协程并且同时显
式指定一个命名。
### 3.1.2 协程上下文的继承
对于新创建的协程，它的CoroutineContext会包含一个全新的Job实例，它会帮助我们控制协程的生命周期。而
剩下的元素会从CoroutineContext的父类继承，该父类可能是另外一个协程或者创建该协程的CoroutineScope。

``协程的上下文`` = ``默认值`` + ``继承的CoroutineContext`` + ``参数``
- 一些元素包含默认值：Dispatchers.Default是默认的CoroutineDispatcher,以及”coroutine“作为默认的CoroutineName;
- 继承的CoroutineContext是CoroutineScope或者其父协程的CoroutineContext;
- 传入协程构建器的参数的优先级高于继承的上下文参数，因此会覆盖对应的参数值。

## 3.2 协程的异常处理
当应用出现一些意外情况时，给用户提供合适的体验非常重要，一方面，目睹应用崩溃是个很糟糕的体验，另一外面，在用户操作失败时，
也必须要能给出正确的提示信息。
- 协程构建器有两种形式：自动传播异常（launch与actor)，向用户暴露异常（async与produce)当这些构建器用于创建一个根协程时（
  该协程不是另一个协程的子协程），前者这类构建器，异常会在它发生的第一时间被抛出，而后者则依赖用户来最终消费异常，例如通过
  await或receiver。
- 创建的``非根协程``中，产生的异常总是会被传播。

### 3.2.1 异常的传播特性
``当一个协程由于一个异常而运行失败时，它会传播这个异常并传递给它的父级。接下来，父级会进行下面几步操作：``
- 取消它自己的子级
- 取消它自己
- 将异常传播并传递给它的父级 
#### 3.2.1.1 SupervisorJob
- 使用SupervisorJob时，一个子协程的运行失败不会影响到其他的子协程。SupervisorJob不会传播异常给它的父级，它会让子协程
  自己处理异常
#### 3.2.1.2 supervisorScope
当作业自身执行失败的时候，所有子作业将会被全部取消。 

### 3.2.2 异常的捕获
- 使用CoroutineExceptionHandler对协程的异常进行捕获。 
- 以下条件被满足时，异常就会被捕获：
  - ``时机``：异常是被自动抛出异常的协程所抛出的（使用launch,而不是async时） 
  - ``位置``：在CoroutineScope的CoroutineContext中或在一个根协程（CoroutineScope或者supervisorScope的直接子协程）
  中

### 3.2.3 全局异常处理
- 全局异常处理可以获取到所有协程未处理的未捕获异常，不过它并不能对异常进行捕获，虽然不能阻止程序崩溃，全局异常处理器在程序
调试和异常上报等场景中仍然有非常大的用处。
- 我们需要在classpath下面创建META-INF/services目录，并在其中创建一个名为kotlinx.coroutines.CoroutineExceptionHandler
的文件，文件内容就是我们的全局异常处理器的全类名。

### 3.2.4 取消与异常
- 取消与异常紧密相关，协程内部使用CancellationException来进行取消，这个异常会被忽略。
- 当子协程被取消时，不会取消它的父协程。
- 如果一个协程遇到了CancellationException以外的异常，它将使用该异常取消它的父协程。当父协程的所有子协程都结束后，异常才
会被父协程处理。

### 3.2.5 异常聚合
当协程的多个子协程因为异常失败时，一般情况下取第一个异常进行处理。在第一个异常之后发生的所有其他异常，都将绑定到第一个异常上。
 



# 四、Flow-异步流
## 4.1 认识Flow
### 4.1.1 如何表示多个值？
- 挂起函数可以异步地返回单个值，但是该如何异步地返回多个计算好的值呢？
### 4.1.2 异步返回多个值的方案
- 集合
- 序列
- 挂起函数
- Flow
### 4.1.3 Flow与其他方式的区别
- 名为flow的Flow类型构建器函数。
- flow{}构建块中的代码可以挂起。
- 函数simpleFlow不再标有suspend修饰符。
- 流使用emit函数发射值。
- 流使用collect函数收集值。
### 4.1.4 FLow应用
- 在Android当中，文件下载是Flow的一个非常典型的应用。
### 4.1.5 冷流
- flow是一种类似于序列的冷流，flow构建器中的代码直到数据被收集的时候才运行。
### 4.1.6 流的连续性
流的每次单独收集都是按顺序执行的，除非使用特殊操作符。
从上游到下游每个过渡操作符都会处理每个发射出的值，然后再交给末端操作符。
### 4.1.7 流构建器
flowOf构建器定义了一个发射固定值集的流。 使用.asFlow()扩展函数，可以将各种集合与序列转换为流
### 4.1.8 流上下文
- 流的收集总是在调用协程的上下文中发生，流的该属性称为``上下文保存``。
- flow{...}构建器中的代码必须遵循上下文保存属性，并且不允许从其他上下文中发射（emit）。
- ``flowOn``操作符，该函数用于更改流发射的上下文
### 4.1.9 启动流
- 使用launchIn替换collect我们可以在单独的协程中启动流的收集
### 4.1.10 流的取消
- 流采用与协程同样的协作取消。像往常一样，流的收集可以是当流在一个可取消的挂起函数（例如delay）中挂起的时候取消。
### 4.1.11 流的取消检测
- 为方便起见，流构建器对每个发射值执行附加的ensureActive检测以进行取消，这意味着从flow{}发出的繁忙循环是可以取消的。
- 出于性能原因，大多数其他流操作不会自行执行其他取消检测，在协程处于繁忙循环的情况下，必须明确检测是否取消。
- 通过cancellable操作符来执行此操作。
### 4.1.12 背压
- buffer(),并发运行流中发射元素的代码。       缓冲
- conflate(),合并发射项，不对每个值进行处理。  跳过中间值
- collectLatest(),取消并重新发射最后一个值。  最后一个值
- 当必须更改CoroutineDispatcher时，flowOn操作符使用了相同的缓冲机制，但是buffer函数显式地请求缓冲而不改变执行上下文。


## 4.2 操作符：
- // 转换操作符：transform
- // 限长操作符：take
- // 末端操作符：reduce  fold  toList  toSet  first single
- // 组合操作符：zip
- // 展平操作符：flatMapConcat flatMapMerge flatMapLatest
### 4.2.1 过渡操作符
- 可以使用操作符转换流，就像使用集合与序列一样。
- 过渡操作符应用于上游流，并返回下游流。
- 这些操作符也是冷操作符，就像流一样。这类操作符本身不是挂起函数。
- 它运行的速度很快，返回心的转换流的定义
- 转换操作符：map transform
- 限长操作符：take
### 4.2.2 末端操作符
- 末端流操作符是在流上用于启动流收集的挂起函数。collect是最基础的末端操作符，但是还有另外一些更方便使用的
末端流操作符：
  - 转换为各种集合，例如toList与toSet
  - 获取第一个（first）值与确保流发射单个（single）值的操作符
  - 使用reduce与fold将流规约到单个值
- reduce  fold  toList  toSet  first single
### 组合多个流
- 就像Kotlin标准库中的Sequence.zip扩展函数一样，流拥有一个zip操作符用于组合两个流中的相关值
- zip
### 展平流
- 流表示异步接收的值序列，所以很容易遇到这样的情况：每个值都会触发对另一个值序列的请求，然而，由于流具有异步的
性质，因此需要不同的展平模式，为此，存在一系列的流展平操作符：
  - flatMapConcat连接模式，
  - flatMapMerge合并模式
  - flatMapLatest最新展平模式


## 4.3 异常
### 4.3.1 流的异常处理
- 当运算符中的发射器或代码抛出异常时，有几种处理异常的方法：
  - try/catch块
  - catch函数
### 4.3.2 流的完成
- 当流收集完成时（普通情况或异常情况），它可能需要执行一个动作。
  - 命令式finally块
  - onCompletion声明式处理

# 五、Channel
## 5.1 Channel通道
### 5.1.1 认识channel
- Channel实际上是一个``并发安全的队列``，它可以用来连接协程，实现不同协程的通信。
### 5.1.2 Channel的容量
- Channel实际上就是一个队列，队列中一定存在缓冲区，那么一旦这个缓冲区满了，并且也一直没有人调用receive函数， 
send就需要挂起。故意让接收端的节奏放慢，发现send总是挂起，直到receive之后才会继续往下执行。
### 5.1.3 迭代Channel
- Channel本身确实像序列，所以我们在读取的时候可以直接获取一个Channel的iterator.
### 5.1.4 produce与actor
- 构造生产者与消费者的便捷方法。
- 我们可以通过produce方法启动一个生产者协程，并返回一个ReceiveChannel,其他协程就可以用这个Channel来接收数据了。
返过来，我们可以用actor启动一个消费者协程。
### 5.1.5 Channel的关闭
- produce和actor返回的channel都会随着对应的协程执行完毕而关闭，也正是这样，Channel才被称为热数据流。
- 对于一个channel,如果我们调用了它的close方法，它会立即停止接收新元素，也就是说这时它的isClosedForSend
会立即返回true.而由于Channel缓冲区的存在，这时候可能还有一些元素没有被处理完，因此要等所有的元素都被读取之后
isClosedForReceive才会返回true。
- Channel的生命周期最好由主导方来维护，建议由主导的一方实现关闭。
### 5.1.6 BroadcastChannel
- 前面提到，发送端和接收端在Channel中存在一对多的情形，从数据处理本身来讲，虽然有多个接收端，但是同一个元素只会被一个
接收端读到。广播则不然，多个接收端不存在互斥行为。

## 5.2 多路复用
### 5.2.1 什么是多路复用
- 数据通信系统或计算机网络系统中，传输媒体的带宽或容量往往会大雨传输单一信号的需求，为了有效地利用通信线路，希望一个信道
同时传输多路信号，这就是所谓的多路复用技术（Multiplexing）。
### 5.2.2 复用多个await
- 两个API分别从网络和本地缓存获取数据，期望哪个先返回就先用哪个做展示。
### 5.2.3 复用多个Channel
跟await类似，会接收到最快的那个Channel消息。
### 5.2.4 SelectClause
- 我们怎么知道哪些事件可以被select呢？其实所有能够被Select的事件都是SelectClauseN类型，包括：
  - SelectClause0：对应事件没有返回值，例如join没有返回值，那么onJoin就是SelectClauseN类型。使用时，onJoin的参数
  是一个无参函数。
  - SelectClause1：对应事件有返回值，前面的onAwait和onReceive都是此类情况。
  - SelectClause2：对应事件有返回值，此外还需要一个额外的参数，例如Channel.onSend有两个参数，第一个是Channel数据类
  型的值，表示即将发送的值；第二个是发送成功的回调参数。
- 如果我们想要确认挂起函数是否支持select,只需查看其是否存在对应的SelectClauseN类型可回调即可。 
### 5.2.5 使用Flow实现多路复用
- 多数情况下，我们可以通过构造合适的Flow来实现多路复用的效果。

## 5.3 并发安全
### 5.3.1 不安全的并发访问
- 我们使用线程在解决并发问题的时候总是会遇到线程安全的问题，而Java平台上的Kotlin协程实现免不了存在并发调度的情况，因此线
程安全同样值得留意
### 5.3.2 协程的并发工具
- 除了我们在线程中常用的解决并发问题的手段之外，协程框架也提供了一些并发安全的工具，包括：
  - Channel：并发安全的消息通道，我们已经非常熟悉
  - Mutex：轻量级锁，它的lock和unlock从语义上与线程锁比较类似，之所有轻量是因为它在获取不到锁时不会阻塞线程，而是挂起等
  待锁的释放
  - Semaphore：轻量级信号量，信号量可以有多个，协程在获取到信号量后即可执行并发操作。当Semaphore的参数为1时，效果等价
  于Mutex
### 5.3.3 避免访问外部可变状态
- 编写函数时要求它不得访问外部状态，只能基于参数做运算，通过返回值提供运算结果。

# 6
## StateFlow
StateFlow是一个状态容器式``可观察数据流``，可以向其收集器发出当前状态更新和新状态更新。还可以通过其value属性读取当前状态值。
## SharedFlow
SharedFlow会向从其中收集值得所有使用方发出数据。