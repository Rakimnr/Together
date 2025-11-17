package com.example.together

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class TrackBudgetActivity : AppCompatActivity() {

    // UI refs
    private lateinit var btnBack: ImageButton
    private lateinit var spinnerType: Spinner
    private lateinit var etAmount: EditText
    private lateinit var etTitle: EditText
    private lateinit var etDate: EditText
    private lateinit var ivCalendar: ImageView
    private lateinit var btnAddRecord: Button
    private lateinit var tvTotalBalance: TextView

    // Simple in-memory totals to mirror the sample values in your XML
    private var totalIncome = 15600.0
    private var totalExpenses = 3250.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_budget)

        // Bind views
        btnBack = findViewById(R.id.btnBack)
        spinnerType = findViewById(R.id.spinnerType)
        etAmount = findViewById(R.id.etAmount)
        etTitle = findViewById(R.id.etTitle)
        etDate = findViewById(R.id.etDate)
        ivCalendar = findViewById(R.id.ivCalendar)
        btnAddRecord = findViewById(R.id.btnAddRecord)
        tvTotalBalance = findViewById(R.id.tvTotalBalance)

        // Header back button
        btnBack.setOnClickListener { finish() }

        // Spinner (Income / Expense)
        val types = listOf("Income", "Expense")
        spinnerType.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            types
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        // Initialize the big balance text from our totals
        updateBalance()

        // Date picker
        ivCalendar.setOnClickListener { showDatePicker() }
        etDate.setOnClickListener { showDatePicker() }

        // Add record
        btnAddRecord.setOnClickListener {
            val type = spinnerType.selectedItem?.toString() ?: "Income"
            val amountTxt = etAmount.text.toString().trim()
            val title = etTitle.text.toString().trim()
            val date = etDate.text.toString().trim()

            // Validate inputs
            val amount = amountTxt.toDoubleOrNull()
            if (amount == null || amount <= 0.0) {
                etAmount.error = "Enter a valid amount"
                return@setOnClickListener
            }
            if (title.isEmpty()) {
                etTitle.error = "Title is required"
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                etDate.error = "Pick a date"
                return@setOnClickListener
            }

            // Update totals
            if (type == "Income") {
                totalIncome += amount
            } else {
                totalExpenses += amount
            }
            updateBalance()

            Toast.makeText(this, "$type added: Rs. ${formatAmount(amount)}", LENGTH_SHORT).show()

            // Clear inputs for the next entry
            etAmount.text?.clear()
            etTitle.text?.clear()
            etDate.text = null
            spinnerType.setSelection(0)
        }
    }

    private fun updateBalance() {
        val balance = totalIncome - totalExpenses
        tvTotalBalance.text = "Rs. ${formatAmount(balance)}"
    }

    private fun formatAmount(value: Double): String {
        // 12,350.00 style
        return String.format(Locale.US, "%,.2f", value)
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        val dlg = DatePickerDialog(
            this,
            { _, y, m, d ->
                // dd/MM/yyyy
                val day = String.format("%02d", d)
                val mon = String.format("%02d", m + 1)
                etDate.setText("$day/$mon/$y")
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dlg.show()
    }
}
