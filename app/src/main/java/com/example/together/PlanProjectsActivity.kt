package com.example.together

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PlanProjectsActivity : AppCompatActivity() {

    private val cal: Calendar = Calendar.getInstance()
    private val dateFmt = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private val timeFmt = SimpleDateFormat("hh:mm a", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_projects)

        // Back button in header
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        // Inputs
        val etProjectName: EditText = findViewById(R.id.etProjectName)
        val etDate: EditText        = findViewById(R.id.etDate)
        val ivCalendar: ImageView   = findViewById(R.id.ivCalendarIcon)
        val etTime: EditText        = findViewById(R.id.etTime)
        val ivTime: ImageView       = findViewById(R.id.ivTimeIcon)
        val etLocation: EditText    = findViewById(R.id.etLocation)
        val btnSubmit: Button       = findViewById(R.id.btnSubmit)

        // --- Date picker ---
        val openDate = {
            val y = cal.get(Calendar.YEAR)
            val m = cal.get(Calendar.MONTH)
            val d = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, { _, yy, mm, dd ->
                cal.set(yy, mm, dd)
                etDate.setText(dateFmt.format(cal.time))
            }, y, m, d).show()
        }
        etDate.setOnClickListener { openDate() }
        ivCalendar.setOnClickListener { openDate() }

        // --- Time picker ---
        val openTime = {
            val h = cal.get(Calendar.HOUR_OF_DAY)
            val min = cal.get(Calendar.MINUTE)
            TimePickerDialog(this, { _, hh, mm ->
                cal.set(Calendar.HOUR_OF_DAY, hh)
                cal.set(Calendar.MINUTE, mm)   // ✅ FIXED: correct constant
                etTime.setText(timeFmt.format(cal.time))
            }, h, min, false).show() // false = 12h clock with AM/PM
        }
        etTime.setOnClickListener { openTime() }
        ivTime.setOnClickListener { openTime() }

        // --- Submit button ---
        btnSubmit.setOnClickListener {
            val name = etProjectName.text.toString().trim()
            val date = etDate.text.toString().trim()
            val time = etTime.text.toString().trim()
            val loc  = etLocation.text.toString().trim()

            when {
                name.isEmpty() -> {
                    etProjectName.error = "Project name required"
                    etProjectName.requestFocus()
                }
                date.isEmpty() -> {
                    etDate.error = "Select a date"
                    etDate.requestFocus()
                }
                time.isEmpty() -> {
                    etTime.error = "Select a time"
                    etTime.requestFocus()
                }
                loc.isEmpty() -> {
                    etLocation.error = "Location required"
                    etLocation.requestFocus()
                }
                else -> {
                    // TODO: save to DB or send to API
                    Toast.makeText(
                        this,
                        "Project saved:\n$name @ $date $time\n$loc",
                        Toast.LENGTH_LONG
                    ).show()
                    finish() // return after "saving"
                }
            }
        }
    }
}
