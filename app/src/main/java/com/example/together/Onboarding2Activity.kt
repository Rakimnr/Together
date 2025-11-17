package com.example.together

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class Onboarding2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Use onboarding theme for look & feel
        setTheme(R.style.OnboardingTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        val btnNext: Button = findViewById(R.id.btn_next)
        val tvSkip: TextView = findViewById(R.id.tv_skip)

        // NEXT -> Onboarding 3
        btnNext.setOnClickListener {
            startActivity(Intent(this, Onboarding3Activity::class.java))
            finish()
        }

        // SKIP -> Login (mark onboarding as done)
        tvSkip.setOnClickListener {
            getSharedPreferences("together_prefs", MODE_PRIVATE).edit {
                putBoolean("onboarding_done", true)
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
