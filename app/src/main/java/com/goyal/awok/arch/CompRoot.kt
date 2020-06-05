package com.goyal.awok.arch

import androidx.room.Room
import com.sakshi.gamechange.Configuration
import com.sakshi.gamechange.GameChange
import com.sakshi.gamechange.model.apis.IosSdkIssueApi
import com.sakshi.gamechange.model.database.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CompRoot(val gameChange: GameChange) {

    private lateinit var retrofit: Retrofit
    private lateinit var client: OkHttpClient

    init {
        initHttpClient()
        initRetrofit()
        initRoom()
    }

    //OkHttpClient for retrofit
    private fun initHttpClient() {
        //logging interceptor for retrofit client
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

    private fun initRoom() {
        Room.databaseBuilder(
            gameChange,
            AppDatabase::class.java, "repo-list.db"
        ).build()
    }

    fun getIsoSdkApi(): IosSdkIssueApi = getRetrofit().create(IosSdkIssueApi::class.java)
}