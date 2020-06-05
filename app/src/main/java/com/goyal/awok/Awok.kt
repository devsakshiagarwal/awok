package com.goyal.awok

import android.app.Application
import com.goyal.awok.arch.CompRoot

class Awok : Application() {

  private lateinit var compRoot: CompRoot

  override fun onCreate() {
    super.onCreate()
    compRoot = CompRoot(this)
  }

  fun compRoot() = compRoot
}