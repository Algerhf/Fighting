package com.example.kotlindemo2.s3
/**
 * 内置函数的总结:
 *
 * apply函数:
 * 1.持有 this
 * 2.返回 this
 *
 * also函数：
 * 1.持有 it
 * 2.返回 this
 *
 * 每次调用需要对同一个对象链式调用时，使用 apply 或者 also
 *
 *
 *
 *
 * let函数：
 * 1.持有 it
 * 2.返回 匿名函数最后一行的结果
 *
 * run函数：
 * 1.持有 this
 * 2.返回 匿名函数最后一行的结果
 *
 * with函数:  with(str)  with 和 run基本上一样，只不过就是使用的时候不一样
 * 1.持有 this
 * 2.返回 匿名函数最后一行的结果
 *
 * 每次调用需要对上一个的结果进行链式调用时，使用 let 或者 run   with比较特殊
 *
 *
 *
 *
 * takeIf： name.takeIf{ true/false}
 * true：直接返回name
 * false：直接返回null
 *
 * takeUnless： 与takeIf相反
 */

