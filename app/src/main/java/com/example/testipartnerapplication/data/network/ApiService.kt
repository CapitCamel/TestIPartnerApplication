package com.example.testipartnerapplication.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/ppp/index/")
    suspend fun search(
        @Query("id")id:Int? = null,
        @Query("search")search:String?= null,
        @Query("crop_id")crop_id:Int?= null,
        @Query("harmful_object_id")harmful_object_id:Int?= null,
        @Query("ingredient_id")ingredient_id:Int?= null,
        @Query("offset")offset:Int?= null,
        @Query("limit")limit:Int?= null

    ): List<DrugResponseItem>

    @GET("/api/ppp/item/")
    suspend fun getDataDrug(
        @Query("id")id:Int
    ): DrugResponseItem

}

