package com.goyal.awok.arch

import com.goyal.awok.Awok
import com.goyal.awok.Configuration
import com.goyal.awok.model.apis.HomeApi
import com.goyal.awok.model.repository.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CompRoot(val awok: Awok) {

  private lateinit var retrofit: Retrofit
  private lateinit var client: OkHttpClient

  init {
    initHttpClient()
    initRetrofit()
  }

  private fun initHttpClient() {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    client =
      OkHttpClient().newBuilder()
          .addInterceptor(loggingInterceptor)
          .build()
  }

  private fun initRetrofit() {
    retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Configuration.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
  }

  private fun getRetrofit() = retrofit

  private fun getHomeApi(): HomeApi = getRetrofit().create(HomeApi::class.java)

  fun getHomeRepository() = HomeRepository(getHomeApi())

}