package com.sduwh.onlineBills.service

import com.google.gson.Gson
import com.sduwh.onlineBills.domain.Config
import com.sduwh.onlineBills.domain.RestfulResponse
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import org.springframework.beans.factory.annotation.Value

interface OnlineConfigService {
    fun getValue(key: String): String?
    fun getConfigs(): List<Config>
    fun updateConfig(config: Config)
}

class LocalOnlineConfigService: OnlineConfigService {
    @Value("\${appkey}")
    lateinit var appkey: String

    private val gson = Gson()

    override fun getValue(key: String): String? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
        request.url("http://127.0.0.1:8001/v1/configs/$key?appKey=$appkey")
        val call = okHttpClient.newCall(request.build())
        val response = call.execute()
        if(response.isSuccessful) {
            val gson = Gson()
            val restfulResponse = gson.fromJson(response.body().toString(), RestfulResponse::class.java)
            if(restfulResponse.success)
                return restfulResponse.data.toString()
        }
        return null
    }

    override fun getConfigs(): List<Config> {
        val result = visitConfigServer("http://127.0.0.1:8001/v1/configs?appKey=$appkey")
        if(result != null) {
            return gson.fromJson(result, Array<Config>::class.java).toList()
        }
        return ArrayList()
    }

    override fun updateConfig(config: Config) {
        val okHttpClient = OkHttpClient()
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(config))
        val request = Request.Builder()
        request.url("http://27.0.0.1:8001/v1/configs/${config.key}").put(requestBody)
        val call = okHttpClient.newCall(request.build())
        val response = call.execute()
        println(response.body().string())
//        if(response.isSuccessful) {
//            val restfulResponse = gson.fromJson(response.body().string(), RestfulResponse::class.java)
//            if(restfulResponse.success)
//                println(restfulResponse)
//        }
    }

    private fun visitConfigServer(url: String): String? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
        request.url(url)
        val call = okHttpClient.newCall(request.build())
        val response = call.execute()
        if(response.isSuccessful) {
            val restfulResponse = gson.fromJson(response.body().string(), RestfulResponse::class.java)
            if(restfulResponse.success)
                return restfulResponse.data.toString()
        }
        return null
    }
}
