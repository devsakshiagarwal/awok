package com.goyal.awok.model.schema

import com.squareup.moshi.Json

data class HomeSchema(
  @field:Json(name = "STATUS") val status: Status,
  @field:Json(name = "OUTPUT") val output: Output
)

data class Status(
  @field:Json(name = "CODE") val code: Int = 0,
  @field:Json(name = "MESSAGE") val message: String = ""
)

data class Output(
  @field:Json(name = "DATA") val data: Data = Data()
)

data class Data(
  @field:Json(name = "ITEMS") val items: List<Item> = ArrayList()
)

data class Item(
  @field:Json(name = "NAME") val name: String = "",
  @field:Json(name = "IMAGE") val image: Image = Image(),
  @field:Json(name = "PRICES") val prices: Prices = Prices()
)

data class Prices(
  @field:Json(name = "PRICE_NEW") val newPrice: String = "0",
  @field:Json(name = "PRICE_OLD") val oldPrice: String = "0",
  @field:Json(name = "DISCOUNT") val discount: String = "0"
)

data class Image(
  @field:Json(name = "SRC") val imageSource: String = ""
)