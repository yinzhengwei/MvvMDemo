package cn.com.mvvmdemo.helper

import kotlinx.coroutines.*

/**
 * 协程
 */

/**
 * Dispatchers：Dispatchers帮助协同程序决定在哪个线程上完成工作。
 * 主要有三种类型的调度程序，分别是IO，Default和Main。
 *
 * IO调度程序用于执行与网络和磁盘相关的工作。;
 * Default用于执行CPU密集型工作;
 * Main是Android的UI线程。
 *
 * Android中主要是用的是以Main为主
 */
fun launchUi(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = GlobalScope.launch(Dispatchers.Main, start, block)

fun launch(callback: (launch: Job) -> Unit): Job {
    val job = GlobalScope.launch {
    }
    callback(job)
    return job
}


