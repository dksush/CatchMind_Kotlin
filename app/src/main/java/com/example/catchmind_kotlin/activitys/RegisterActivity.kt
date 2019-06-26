package com.example.catchmind_kotlin.activitys

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap

import com.example.catchmind_kotlin.apis.NetworkUtil

import com.example.catchmind_kotlin.databinding.ActivityRegisterBinding
import com.example.catchmind_kotlin.models.LoginInfoResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.example.catchmind_kotlin.R.layout.activity_register)


        init()
    }





    fun init(){
        // 회원가입하기 버튼
        mBinding.btnSignUp.setOnClickListener {
            register()
        }
    }


    fun register(){
        val retrofit = NetworkUtil.getRetrofit(Api.base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java) // !! 널값이 아님을 단언.

        var jsonParams = ArrayMap<String, Any>()
        jsonParams.put("id", mBinding.etEmail.toString())
        jsonParams.put("pw", mBinding.etPassword.toString())
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())
        api.postRequest(body).enqueue(object : retrofit2.Callback<LoginInfoResponse>{
            override fun onFailure(call: Call<LoginInfoResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<LoginInfoResponse>, response: Response<LoginInfoResponse>) {

                val httpCode = response.code()
                if(httpCode == 200){
                    var res : LoginInfoResponse? = response.body() // ?  널 허용.

                }


            }

        })
    }

}
