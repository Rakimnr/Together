package com.example.together

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class Onboarding1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.OnboardingTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)

        val btnNext: Button = findViewById(R.id.btn_next)
        val tvSkip: TextView = findViewById(R.id.tv_skip)

        btnNext.setOnClickListener {
            startActivity(Intent(this, Onboarding2Activity::class.java))
            finish()
        }

        tvSkip.setOnClickListener {
            getSharedPreferences("together_prefs", MODE_PRIVATE).edit {
                putBoolean("onboarding_done", true)
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
