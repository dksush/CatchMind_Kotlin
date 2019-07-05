package com.example.catchmind_kotlin.apis

import com.example.catchmind_kotlin.models.LoginInfoResponse
import com.example.catchmind_kotlin.models.registerResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



interface Api {

    companion object {
        const val base_url : String = "http://210.89.188.64/"
    }



    // @Multipart
    @FormUrlEncoded
    @POST("register.php")
    fun postRequest2(@Body params: RequestBody): Call<LoginInfoResponse>


    @FormUrlEncoded
    @POST("Register.php")
    fun postRequest(@Field("id") id:String,
                    @Field("pw") pw:String) : Call<Void>



    @FormUrlEncoded
    @POST("Register.php")
    fun postRequest2(@Field("id") id:String,
                    @Field("pw") pw:String) : Call<registerResponse>
//    @POST("register.php")
//    fun postRequest(@Body params: RequestBody): Call<Void> // 별도의 응답 형태가 아니라 연결 성공/실패 여부만 판단할시.




}