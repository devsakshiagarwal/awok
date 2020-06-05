package com.goyal.awok

import android.app.Application
import com.sakshi.gamechange.arch.CompRoot

class GameChange : Application() {

    private lateinit var compRoot: CompRoot

    override fun onCreate() {
        super.onCreate()
        compRoot = CompRoot(this)
    }

    fun getCompRoot() = compRoot
}