package com.tugrulbo.kotlinflightapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tugrulbo.kotlinflightapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val email = "appcent@appcent.mobi"
    val password = "123456"
    var isRemember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickEvents()
    }

    private fun clickEvents(){

        //Login işlemi için buton tıklaması
        loginBtn.setOnClickListener {
                if(email == loginEditTMail.text.toString() && password == loginEditTPassword.text.toString() ){
                    if(isRemember){
                        var editTextEmail = loginEditTMail.text.toString()
                        var editTextPass = loginEditTPassword.text.toString()
                       saveMailAndPass(editTextEmail,editTextPass,isRemember)
                    }
                    else
                    {
                        clearEmailAndPass()
                    }

                    var loginIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(loginIntent)
                    finish()

                }else{
                    Toast.makeText(this@LoginActivity,"E-Posta veya Şifre yanlış!",Toast.LENGTH_LONG).show()
                }
        }

        //Beni hatırla kısmını takip eden kısım
        loginCheck.setOnCheckedChangeListener { _, isChecked ->
            isRemember = isChecked
        }
    }

    private fun saveMailAndPass(email:String,password:String,isRemember:Boolean){
        var sharedPref = getSharedPreferences("LoginInfo",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putString("email",email)
        editor.putString("password",password)
        editor.putBoolean("isRemember",isRemember)
        editor.commit()
    }

    private fun clearEmailAndPass(){
        var sharedPref = getSharedPreferences("LoginInfo",Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.remove("email")
        editor.remove("password")
        editor.remove("isRemember")
        editor.commit()
    }
}