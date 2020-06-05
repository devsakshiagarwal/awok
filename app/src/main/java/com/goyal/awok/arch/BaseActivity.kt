package com.sakshi.gamechange.arch

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import com.sakshi.gamechange.GameChange

@SuppressLint("Registered")
open class BaseActivity : FragmentActivity() {

  private var compRoot: CompRootUi? = null

  protected fun compRoot(): CompRootUi? {
    if (compRoot == null) {
      compRoot = CompRootUi(
          (application as GameChange).getCompRoot(),
          this
      )
    }
    return compRoot
  }
}