package com.alfikri.rizky.data.rest

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL

import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class ApiConnection private constructor(requestUrl: String) : Callable<String> {

    private var response = ""
    private var url: URL = URL(requestUrl)

    companion object{
        fun createGET(url: String): ApiConnection{
            return ApiConnection(url)
        }
    }

    fun requestSyncCall(): String {
        connectToApi()
        return response
    }

    private fun connectToApi() {
        val okHttpClient = createClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        try {
            response = okHttpClient.newCall(request).execute().body()?.string().toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(15000, TimeUnit.MILLISECONDS)
            .build()
    }

    override fun call(): String {
        return requestSyncCall()
    }
}