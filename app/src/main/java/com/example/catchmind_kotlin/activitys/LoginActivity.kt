package com.example.catchmind_kotlin.activitys

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.catchmind_kotlin.R
import com.example.catchmind_kotlin.databinding.ActivityLoginBinding
import com.example.catchmind_kotlin.util.SharedPreferencesManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private var sp: SharedPreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

//        if (savedInstanceState == null) {     // 1st time
//            this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_fade_out)  // 새로생기는 화면, 원래있던 화면
//        } else{ // already created so reverse animation
//            //  onStartCount = 2
//            }



        setUi()

    }

    fun setUi(){

        // 로그인 여부 확인
        sp = SharedPreferencesManager(this)
        if (!sp?.getId().isNullOrBlank()){ // isNullOrBlank : null, empty, 공백일 경우 true 를 뱉는다.
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // 로그인
        // 세부 처리 귀찮다.
        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        // 회원가입
        binding.btnRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
