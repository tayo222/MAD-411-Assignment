package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExpenseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_details)

        // Get the expense data from the intent
        val expenseName = intent.getStringExtra("EXPENSE_NAME")
        val expenseAmount = intent.getDoubleExtra("EXPENSE_AMOUNT", 0.0)
        val expenseDate = intent.getStringExtra("EXPENSE_DATE")

        // Display the data in TextViews
        val nameTextView = findViewById<TextView>(R.id.textViewExpenseName)
        val amountTextView = findViewById<TextView>(R.id.textViewExpenseAmount)
        val dateTextView = findViewById<TextView>(R.id.textViewExpenseDate)

        nameTextView.text = expenseName
        amountTextView.text = "Amount: $${String.format("%.2f", expenseAmount)}"
        dateTextView.text = "Date: $expenseDate"
    }
}