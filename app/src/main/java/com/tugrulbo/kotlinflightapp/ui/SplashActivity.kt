package com.tugrulbo.kotlinflightapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugrulbo.kotlinflightapp.R
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Eğer checkRemember "true" dönerse homeactivity'e geç
       if(checkRemember()){
           Timer().schedule(object : TimerTask() {
               override fun run() {
                   startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                   finish()
               }
           }, 3000)
       }else //Eğer checkRemember "false" dönerse loginactivity'e geç
       {
           Timer().schedule(object : TimerTask() {
               override fun run() {
                   startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                   finish()
               }
           }, 3000)
       }
    }

    private fun checkRemember() : Boolean{
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email","")
        val password = sharedPreferences.getString("password","")
        val isRemember = sharedPreferences.getBoolean("isRemember",false)

        return email?.isNotEmpty() == true && password?.isNotEmpty() == true

    }
}
