package com.goyal.awok.model.apis

import com.goyal.awok.arch.Urls
import com.goyal.awok.model.schema.HomeSchema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

  @GET(Urls.HOME_URL)
  fun getVerticalData(): Call<HomeSchema>

  @GET(Urls.FLASH_URL)
  fun getHorizontalData(): Call<HomeSchema>
}