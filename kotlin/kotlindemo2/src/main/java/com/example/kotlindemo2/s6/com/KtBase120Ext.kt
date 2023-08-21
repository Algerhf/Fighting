package com.example.kotlindemo2.s6.com

// 扩展文件一般都是public,如果private外界无法使用
//
fun <E> Iterable<E>.randomItemValue() = this.shuffled().first()