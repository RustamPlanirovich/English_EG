package com.shainurov.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("uc?export=download&confirm=no_antivirus&id=11C647TsRXhK3JowXTQ8U_gKHQ5nTvzDt")
    suspend fun getData(): Response<ResponseBody>
}
