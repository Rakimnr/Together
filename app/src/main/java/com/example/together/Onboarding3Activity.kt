package com.example.together

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class Onboarding3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Use onboarding theme for look & feel
        setTheme(R.style.OnboardingTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)

        val btnGetStarted: Button = findViewById(R.id.btn_get_started)
        val tvSkip: TextView = findViewById(R.id.tv_skip)

        val goLogin = {
            // mark onboarding complete
            getSharedPreferences("together_prefs", MODE_PRIVATE).edit {
                putBoolean("onboarding_done", true)
            }
            // jump to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnGetStarted.setOnClickListener { goLogin() }
        tvSkip.setOnClickListener { goLogin() }
    }
}
