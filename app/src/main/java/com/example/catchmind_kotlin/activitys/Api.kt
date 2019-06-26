package com.example.catchmind_kotlin.activitys

import com.example.catchmind_kotlin.models.LoginInfoResponse
import com.example.catchmind_kotlin.models.registerResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    companion object {
        val base_url : String = "http://45.119.144.145/"
    }


//    @FormUrlEncoded
//    @POST("register")
//    fun postRequest(@Field("id") id: String,
//                    @Field("pw") pw: String): Call<LoginInfoResponse>

    @FormUrlEncoded
    @POST("register.php")
    fun postRequest(@Body params: RequestBody): Call<LoginInfoResponse>

}