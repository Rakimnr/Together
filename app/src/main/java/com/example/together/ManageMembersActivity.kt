package com.example.together

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ManageMembersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Uses your default AppTheme; no need to call setTheme
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_members)

        // 1) Back button -> finish screen
        findViewById<ImageButton?>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        // 2) Search bar: hide keyboard on IME action / clear focus
        val etSearch = findViewById<EditText?>(R.id.etSearch)
        etSearch?.setOnEditorActionListener { v, _, _ ->
            hideKeyboardAndClearFocus(v)
            Toast.makeText(this, "Searching: ${etSearch.text}", Toast.LENGTH_SHORT).show()
            true
        }
        // Optional: clicking outside hides keyboard (attach to root if you like)
        findViewById<View>(android.R.id.content)?.setOnClickListener {
            if (etSearch?.hasFocus() == true) {
                hideKeyboardAndClearFocus(etSearch)
            }
        }

        // 3) "Mark All Present" -> (for now) just a success toast
        //    You can replace with real logic later (API call / local state).
        //    Button in XML is a normal Button; this works with either Button or MaterialButton.
        val btnMarkAll = findViewById<View?>(R.id.btnMarkAllPresent)
        btnMarkAll?.setOnClickListener {
            Toast.makeText(this, "All members marked present", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideKeyboardAndClearFocus(view: View) {
        view.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
