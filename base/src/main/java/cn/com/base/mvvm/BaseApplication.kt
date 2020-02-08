package cn.com.base.mvvm

import android.app.Application

/**
 *  Created by yinzhengwei on 2020-02-03.
 *  @Function
 */
open class BaseApplication : Application() {

    object getInstance {
        lateinit var sApplication: Application
    }

    override fun onCreate() {
        super.onCreate()

        getInstance.sApplication = this

    }

}