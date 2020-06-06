package com.goyal.awok.model.apis

import com.goyal.awok.arch.Urls
import com.goyal.awok.model.schema.HomeSchema
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApi {

  @FormUrlEncoded
  @POST(Urls.HOME_URL)
  fun getVerticalData(@Field("PAGED") id: String): Call<HomeSchema>

  @GET(Urls.FLASH_URL)
  fun getHorizontalData(): Call<HomeSchema>
}