package com.example.apisamplecodes.services.repositories

import com.example.apisamplecodes.models.QuotesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {
    @GET("/quotes")
    suspend fun getQuotes(): Response<QuotesModel>
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuotesModel>
}