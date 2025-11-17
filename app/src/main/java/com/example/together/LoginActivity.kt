package com.example.together

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Sign In -> Home (plug real auth later)
        findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Sign Up -> Register step 1
        findViewById<TextView>(R.id.tv_sign_up).setOnClickListener {
            startActivity(Intent(this, Register1Activity::class.java))
        }
    }
}
