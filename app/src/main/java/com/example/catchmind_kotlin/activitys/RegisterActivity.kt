package com.example.catchmind_kotlin.activitys

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.widget.Toast
import com.example.catchmind_kotlin.apis.Api

import com.example.catchmind_kotlin.apis.NetworkUtil

import com.example.catchmind_kotlin.databinding.ActivityRegisterBinding
import com.example.catchmind_kotlin.models.registerResponse
import com.example.catchmind_kotlin.util.SharedPreferencesManager
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityRegisterBinding
    var sp : SharedPreferencesManager? = null
   // internal var category_info: registerResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.example.catchmind_kotlin.R.layout.activity_register)


        init()
    }





    fun init(){
        // 회원가입하기 버튼
        mBinding.btnSignUp.setOnClickListener { register() }
    }


    fun register(){

        val retrofit = NetworkUtil.getRetrofit(Api.base_url, GsonConverterFactory.create())
        val api = retrofit.create(Api::class.java) // !! 널값이 아님을 단언.

        val jsonParams = ArrayMap<String, Any>()

        jsonParams["id"] = mBinding.etEmail.text.toString()
        jsonParams["pw"] = mBinding.etPassword.text.toString()
        api.postRequest(mBinding.etEmail.text.toString(),  mBinding.etPassword.text.toString()).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.v("dksush","d실패")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if(response.code() == 200){
                    Toast.makeText(this@RegisterActivity, "가입가입", Toast.LENGTH_LONG).show()

                    sp = SharedPreferencesManager(this@RegisterActivity)
                    sp?.saveId(mBinding.etEmail.text.toString()) // 쉐어드에 아이디 저장.
                    Log.v("dksush_shard", sp?.getId().toString())

                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)

                }
            }

        })
    }








}
