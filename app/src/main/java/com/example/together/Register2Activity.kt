package com.example.together

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Register2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val etConfirm: EditText = findViewById(R.id.et_confirm_password)
        val tvUpper: TextView = findViewById(R.id.tv_check_uppercase)
        val tvNumber: TextView = findViewById(R.id.tv_check_number)
        val tvSpecial: TextView = findViewById(R.id.tv_check_special)
        val tvStrength: TextView = findViewById(R.id.tv_password_strength)
        val cbTerms: CheckBox = findViewById(R.id.cb_terms)
        val btnCreate: Button = findViewById(R.id.btn_create_account)

        // live password checks
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = updatePasswordChecks(etPassword.text.toString(), tvUpper, tvNumber, tvSpecial, tvStrength)
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        etPassword.addTextChangedListener(watcher)
        etConfirm.addTextChangedListener(watcher)

        btnCreate.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString()
            val confirm = etConfirm.text.toString()

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email"; etEmail.requestFocus(); return@setOnClickListener
            }
            if (!isStrong(pass)) {
                Toast.makeText(this, "Password does not meet the requirements", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass != confirm) {
                etConfirm.error = "Passwords do not match"; etConfirm.requestFocus(); return@setOnClickListener
            }
            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please agree to the terms", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: call your backend create-user here
            // on success:
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun isStrong(pw: String): Boolean {
        val hasLen = pw.length >= 8
        val hasUpper = pw.any { it.isUpperCase() }
        val hasDigit = pw.any { it.isDigit() }
        val hasSpecial = pw.any { "!@#\$%^&*()_+-=[]{}|;':\",.<>?/`~".contains(it) }
        return hasLen && hasUpper && hasDigit && hasSpecial
    }

    private fun updatePasswordChecks(
        pw: String,
        tvUpper: TextView,
        tvNumber: TextView,
        tvSpecial: TextView,
        tvStrength: TextView
    ) {
        val hasUpper = pw.any { it.isUpperCase() }
        val hasDigit = pw.any { it.isDigit() }
        val hasSpecial = pw.any { "!@#\$%^&*()_+-=[]{}|;':\",.<>?/`~".contains(it) }
        val hasLen = pw.length >= 8

        // Toggle markers: ✗/✓ + color
        fun set(tv: TextView, ok: Boolean) {
            tv.text = if (ok) "✓" else "✗"
            tv.setTextColor(getColor(if (ok) R.color.success_green else R.color.error_red))
        }

        set(tvUpper, hasUpper)
        set(tvNumber, hasDigit)
        set(tvSpecial, hasSpecial)

        // Strength text
        val score = listOf(hasLen, hasUpper, hasDigit, hasSpecial).count { it }
        tvStrength.text = when (score) {
            0, 1 -> "Password Strength: Very Weak"
            2 -> "Password Strength: Weak"
            3 -> "Password Strength: Good"
            else -> "Password Strength: Strong"
        }
    }
}
