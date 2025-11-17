package com.example.together

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register1)

        val etFullName: EditText = findViewById(R.id.et_full_name)
        val etAge: EditText = findViewById(R.id.et_age)              // present in your XML
        val etPhone: EditText = findViewById(R.id.et_phone)          // present in your XML
        val spinner: Spinner = findViewById(R.id.spinner_leadership) // present in your XML
        val btnContinue: Button = findViewById(R.id.btn_continue)    // present in your XML

        // Populate spinner from arrays.xml
        val positions = resources.getStringArray(R.array.leadership_positions)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, positions)

        btnContinue.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val ageText = etAge.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val pos = spinner.selectedItem?.toString().orEmpty()

            // minimal validation
            if (name.isEmpty()) { etFullName.error = "Full name required"; etFullName.requestFocus(); return@setOnClickListener }
            val ageOk = ageText.toIntOrNull()?.let { it in 10..100 } == true
            if (!ageOk) { etAge.error = "Enter a valid age (10–100)"; etAge.requestFocus(); return@setOnClickListener }
            if (phone.length !in 9..15) { etPhone.error = "Enter a valid phone"; etPhone.requestFocus(); return@setOnClickListener }
            if (pos.isEmpty() || spinner.selectedItemPosition == 0) { Toast.makeText(this, "Select a position", Toast.LENGTH_SHORT).show(); return@setOnClickListener }

            // Pass data forward if you want (optional)
            val i = Intent(this, Register2Activity::class.java).apply {
                putExtra("full_name", name)
                putExtra("age", ageText)
                putExtra("phone", phone)
                putExtra("position", pos)
            }
            startActivity(i)
        }
    }
}
