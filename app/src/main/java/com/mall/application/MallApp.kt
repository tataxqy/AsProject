package com.mall.application

import android.app.Application
import com.mall.library.global.Mall

class MallApp:Application()
{

    override fun onCreate() {
        super.onCreate()
        Mall.init(this)
            .withLoaderDelayed(2000)
            .withApiHost("http://mock.fulingjie.com/mock/api/")
            .configure()

    }
}