package com.goyal.awok.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goyal.awok.model.repository.HomeRepository
import com.goyal.awok.model.schema.Item

class HomeViewModel : ViewModel(), HomeRepository.HomeApiListener {

  private lateinit var homeRepository: HomeRepository
  var liveDataVerticalList = MutableLiveData<List<Item>>()
  var liveDataHorizontalList = MutableLiveData<List<Item>>()
  var liveDataErrorMessage = MutableLiveData<String>()
  private var pageNumber = 1

  fun setRepository(homeRepository: HomeRepository) {
    this.homeRepository = homeRepository
    homeRepository.setHomeApiListener(this)
    homeRepository.fetchHomeData()
  }

  fun performPagination() {
    pageNumber += 1
    homeRepository.fetchVerticalData(pageNumber)
  }

  fun cancelApiCall() {
    homeRepository.cancelApiCalls()
  }

  override fun onHorizontalResponseSuccess(horizontalList: List<Item>) {
    liveDataHorizontalList.value = horizontalList
  }

  override fun onVerticalResponseSuccess(verticalList: List<Item>) {
    liveDataVerticalList.value = verticalList
  }

  override fun onHorizontalResponseFailure(message: String) {
    liveDataErrorMessage.value = message
  }

  override fun onVerticalResponseFailure(message: String) {
    liveDataErrorMessage.value = message
  }

}