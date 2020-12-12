package com.kovaliov.testapplication.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

inline fun <reified T> Context.startActivity(){
    startActivity(Intent(this, T::class.java))
}

fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

@Suppress("DEPRECATION")
fun Context.isNetworkAvailable(): Boolean{
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnected?:false
}