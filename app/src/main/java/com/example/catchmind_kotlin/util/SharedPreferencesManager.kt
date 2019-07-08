package com.example.catchmind_kotlin.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesManager { // 코틀린 디폴트 컨스트럭터.
    var sp : SharedPreferences? = null

    // 생성자 : 클래스 선언부에 디폴트로 선언할수도 있다.
    // constructor 라고 선언하고, 외부 클래스에서 사용할때는 그냥 'SharedPreferencesManager' 로 불러낼 수 있다.
    constructor(context: Context){
        sp = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

    }

    //로그인 저장
    fun saveId(id:String){
        var edit = sp?.edit()
        edit?.putString("id", id)
        edit?.apply()
    }

    fun getId(): String? {
        var data: String? = null
        data = sp?.getString("id","")

        return data
    }







}