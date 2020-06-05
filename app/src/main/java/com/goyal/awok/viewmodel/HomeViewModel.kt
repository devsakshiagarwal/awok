package com.goyal.awok.viewmodel

import androidx.lifecycle.ViewModel
import com.goyal.awok.model.repository.HomeRepository
import com.goyal.awok.model.schema.Item

class HomeViewModel : ViewModel(), HomeRepository.HomeApiListener {
  override fun onHorizontalResponseSuccess(horizontalList: List<Item>) {
    TODO("Not yet implemented")
  }

  override fun onVerticalResponseSuccess(verticalList: List<Item>) {
    TODO("Not yet implemented")
  }

  override fun onHorizontalResponseFailure(message: String) {
    TODO("Not yet implemented")
  }

  override fun onVerticalResponseFailure(message: String) {
    TODO("Not yet implemented")
  }

}