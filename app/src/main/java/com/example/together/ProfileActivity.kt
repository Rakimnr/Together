package com.example.together

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // --- Back button ---
        findViewById<ImageView?>(R.id.iv_back)?.setOnClickListener {
            finish()
        }

        // --- Edit profile (pencil icon) ---
        findViewById<ImageView?>(R.id.iv_edit_profile)?.setOnClickListener {
            Toast.makeText(this, "Edit profile clicked", Toast.LENGTH_SHORT).show()
            // TODO: startActivity(Intent(this, EditProfileActivity::class.java))
        }

        // --- Account Settings card ---
        findViewById<View?>(R.id.cv_account_settings)?.setOnClickListener {
            Toast.makeText(this, "Account Settings", Toast.LENGTH_SHORT).show()
        }

        // --- Privacy card ---
        findViewById<View?>(R.id.cv_privacy)?.setOnClickListener {
            Toast.makeText(this, "Privacy & Security", Toast.LENGTH_SHORT).show()
        }

        // --- Help card ---
        findViewById<View?>(R.id.cv_help)?.setOnClickListener {
            Toast.makeText(this, "Help & Support", Toast.LENGTH_SHORT).show()
        }

        // --- Logout card ---
        findViewById<View?>(R.id.cv_logout)?.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            // TODO: clear prefs and send to LoginActivity
            // val i = Intent(this, LoginActivity::class.java)
            // startActivity(i)
            // finishAffinity()
        }
    }
}
