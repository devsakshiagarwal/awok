package com.goyal.awok.model.repository

import com.goyal.awok.model.apis.HomeApi
import com.goyal.awok.model.schema.HomeSchema
import com.goyal.awok.model.schema.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(homeApi: HomeApi) {

  private val verticalCall = homeApi.getVerticalData()
  private val horizontalCall = homeApi.getHorizontalData()
  private lateinit var homeApiListener: HomeApiListener

  fun setHomeApiListener(homeApiListener: HomeApiListener) {
    this.homeApiListener = homeApiListener
  }

  fun fetchHomeData() {
    verticalCall.enqueue(object : Callback<HomeSchema> {
      override fun onResponse(
        call: Call<HomeSchema>,
        response: Response<HomeSchema>
      ) {
        if (response.code() == 200) {
          val homeSchema = response.body()!!
          homeApiListener.onVerticalResponseSuccess(homeSchema.output.data.items)
        } else {
          homeApiListener.onVerticalResponseFailure("Something does not seem to be right!")
        }
      }

      override fun onFailure(
        call: Call<HomeSchema>,
        t: Throwable
      ) {
        homeApiListener.onVerticalResponseFailure(t.localizedMessage!!)
      }
    })
    horizontalCall.enqueue(object : Callback<HomeSchema> {
      override fun onResponse(
        call: Call<HomeSchema>,
        response: Response<HomeSchema>
      ) {
        if (response.code() == 200) {
          val homeSchema = response.body()!!
          homeApiListener.onHorizontalResponseSuccess(homeSchema.output.data.items)
        } else {
          homeApiListener.onHorizontalResponseFailure("Something does not seem to be right!")
        }
      }

      override fun onFailure(
        call: Call<HomeSchema>,
        t: Throwable
      ) {
        homeApiListener.onHorizontalResponseFailure(t.localizedMessage!!)
      }
    })
  }

  fun cancelApiCalls() {
    verticalCall.cancel()
    horizontalCall.cancel()
  }

  interface HomeApiListener {
    fun onHorizontalResponseSuccess(horizontalList: List<Item>)

    fun onVerticalResponseSuccess(verticalList: List<Item>)

    fun onHorizontalResponseFailure(message: String)

    fun onVerticalResponseFailure(message: String)
  }

}