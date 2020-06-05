package com.goyal.awok.arch

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import com.goyal.awok.Awok

@SuppressLint("Registered")
open class BaseActivity : FragmentActivity() {

  private var compRoot: CompRoot? = null

  protected fun compRoot(): CompRoot? {
    if (compRoot == null) {
      compRoot = (application as Awok).compRoot()
    }
    return compRoot
  }
}