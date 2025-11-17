package com.example.together

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ManageTaskActivity : AppCompatActivity() {

    private val cal: Calendar = Calendar.getInstance()
    private val dateFmt = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_task)

        // Header back
        findViewById<ImageButton?>(R.id.btnBack)?.setOnClickListener { finish() }

        // Inputs
        val etTaskName: EditText?   = findViewById(R.id.etTaskName)
        val spinnerAssign: Spinner? = findViewById(R.id.spinnerAssignTo)
        val etDueDate: EditText?    = findViewById(R.id.etDueDate)
        val ivCalendar: ImageView?  = findViewById(R.id.ivCalendar)
        val btnAddTask: Button?     = findViewById(R.id.btnAddTask)

        // Populate spinner from arrays.xml -> leadership_positions (safe + simple)
        spinnerAssign?.let { sp ->
            val options = resources.getStringArray(R.array.leadership_positions)
            sp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        }

        // Date picker (tap field or icon)
        val openDate = {
            val y = cal.get(Calendar.YEAR)
            val m = cal.get(Calendar.MONTH)
            val d = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, { _, yy, mm, dd ->
                cal.set(yy, mm, dd)
                etDueDate?.setText(dateFmt.format(cal.time))
            }, y, m, d).show()
        }
        etDueDate?.setOnClickListener { openDate() }
        ivCalendar?.setOnClickListener { openDate() }

        // Add task (basic validation -> toast)
        btnAddTask?.setOnClickListener {
            val name = etTaskName?.text?.toString()?.trim().orEmpty()
            val due  = etDueDate?.text?.toString()?.trim().orEmpty()
            val assignee = spinnerAssign?.selectedItem?.toString().orEmpty()

            when {
                name.isEmpty() -> { etTaskName?.error = "Task name required"; etTaskName?.requestFocus() }
                due.isEmpty()  -> { etDueDate?.error = "Select a due date"; etDueDate?.requestFocus() }
                else -> {
                    Toast.makeText(
                        this,
                        "Task added:\n$name\nDue: $due\nAssign: ${assignee.ifBlank { "—" }}",
                        Toast.LENGTH_LONG
                    ).show()
                    // TODO: persist to DB / API; for now we just clear inputs
                    etTaskName?.setText("")
                    etDueDate?.setText("")
                    spinnerAssign?.setSelection(0)
                }
            }
        }

        // Sample task checkbox toggle (from your static card)
        findViewById<CheckBox?>(R.id.cbTask)?.setOnCheckedChangeListener { _, checked ->
            Toast.makeText(this, if (checked) "Task completed" else "Task marked pending", Toast.LENGTH_SHORT).show()
        }
    }
}
