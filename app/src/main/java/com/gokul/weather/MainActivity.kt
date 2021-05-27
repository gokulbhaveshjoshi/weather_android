package com.gokul.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splash()
    }

    private fun splash() {
        try{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, WeatherActivity::class.java )
                startActivity(intent)
                finish()

            },3000)
        }
        catch (e: Exception){
            Log.e("Error","Splash Error $e")
        }
    }
}