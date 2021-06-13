package com.tugrulbo.kotlinflightapp.utils

object Constant {

    //API tarafından gelen key
    const val apiKey = "ed9ad3b330d2b81e1b1b69e192b296c5"

    //Login işlemi için yapılan statik tanımlamalar
    const val email = "appcent@appcent.mobi"
    const val password = "123456"

    //HomeActivity içinde bulunan data yüklenmediği zaman gösterilecek mesaj
    const val homeNotLoading = "Şuan görüntülenememektedir."

    //LoginActivity içinde bulunan giriş bilgileri hatalı olduğu zaman çıkacak hata
    const val loginError = "E-Posta veya Şifre yanlış!"

    //Sharedpref detayları
    const val sharedPrefName = "LoginInfo"
    const val sharedPrefEmail = "email"
    const val sharedPrefPass = "password"
    const val sharedPrefBoolean = "isRemember"

    //Null dönen değerler için
    const val nullValue = "-"
}
