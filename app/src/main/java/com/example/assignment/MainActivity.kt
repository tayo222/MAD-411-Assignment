package com.example.assignment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var expenseDateEditText: EditText
    private lateinit var addExpenseButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        expenseNameEditText = findViewById(R.id.textExpenseName)
        expenseAmountEditText = findViewById(R.id.textExpenseAmount)
        expenseDateEditText = findViewById(R.id.itemDate)
        addExpenseButton = findViewById(R.id.addExpenseButton)
        recyclerView = findViewById(R.id.expenseRecyclerView)

        // Log to check if views are initialized
        if (expenseNameEditText == null) println("expenseNameEditText is null!")
        if (expenseAmountEditText == null) println("expenseAmountEditText is null!")
        if (expenseDateEditText == null) println("expenseDateEditText is null!")
        if (addExpenseButton == null) println("addExpenseButton is null!")
        if (recyclerView == null) println("recyclerView is null!")

        // Initialize RecyclerView and adapter
        expenseAdapter = ExpenseAdapter(expenseList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // Set click listener for the "Add Expense" button
        addExpenseButton.setOnClickListener { addExpense() }
    }

    private fun addExpense() {
        // Log to check if the method is being called
        println("Add Expense button clicked!")

        // Get input values
        val name = expenseNameEditText.text.toString().trim()
        val amountStr = expenseAmountEditText.text.toString().trim()
        val date = expenseDateEditText.text.toString().trim()

        // Validate input
        if (name.isNotEmpty() && amountStr.isNotEmpty() && date.isNotEmpty()) {
            val amount = amountStr.toDoubleOrNull()
            if (amount != null) {
                // Add the new expense to the list
                expenseList.add(Expense(name, amount, date))
                expenseAdapter.notifyItemInserted(expenseList.size - 1)

                // Clear input fields
                expenseNameEditText.text.clear()
                expenseAmountEditText.text.clear()
                expenseDateEditText.text.clear()

                // Log success
                println("Expense added successfully!")
            } else {
                // Show error for invalid amount
                Toast.makeText(this, "Invalid amount!", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Show error for missing fields
            Toast.makeText(this, "Please enter a name, amount, and date!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }
}