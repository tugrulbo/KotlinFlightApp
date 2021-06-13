package com.tugrulbo.kotlinflightapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tugrulbo.kotlinflightapp.R
import com.tugrulbo.kotlinflightapp.utils.Constant
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var isRemember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickEvents()
    }

    private fun clickEvents(){

        //Login işlemi için buton tıklaması
        loginBtn.setOnClickListener {
                if(Constant.email == loginEditTMail.text.toString() && Constant.password == loginEditTPassword.text.toString() ){
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
                    Toast.makeText(this@LoginActivity,Constant.loginError,Toast.LENGTH_LONG).show()
                }
        }

        //Beni hatırla kısmını takip eden kısım
        loginCheck.setOnCheckedChangeListener { _, isChecked ->
            isRemember = isChecked
        }
    }

    private fun saveMailAndPass(email:String,password:String,isRemember:Boolean){
        var sharedPref = getSharedPreferences(Constant.sharedPrefName,Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putString(Constant.sharedPrefEmail,email)
        editor.putString(Constant.sharedPrefPass,password)
        editor.putBoolean(Constant.sharedPrefBoolean,isRemember)
        editor.commit()
    }

    private fun clearEmailAndPass(){
        var sharedPref = getSharedPreferences(Constant.sharedPrefName,Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.remove(Constant.sharedPrefEmail).commit()
        editor.remove(Constant.sharedPrefPass).commit()
        editor.remove(Constant.sharedPrefBoolean).commit()
    }
}