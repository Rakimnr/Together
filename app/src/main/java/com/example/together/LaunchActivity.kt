package com.example.together

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        // Delay 2 seconds, then go to Onboarding1
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Onboarding1Activity::class.java)
            startActivity(intent)
            finish()  // close splash so back button won’t return here
        }, 2000)
    }
}
