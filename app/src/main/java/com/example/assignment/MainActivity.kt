package com.example.assignment

import android.content.Intent
import android.net.Uri
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.headerContainer, HeaderFragment())
            .commit()

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
        expenseAdapter = ExpenseAdapter(expenseList) { position ->
            // Handle item click
            val expense = expenseList[position]
            val intent = Intent(this, ExpenseDetailsActivity::class.java)
            intent.putExtra("EXPENSE_NAME", expense.name)
            intent.putExtra("EXPENSE_AMOUNT", expense.amount)
            intent.putExtra("EXPENSE_DATE", expense.date)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // Set click listener for the "Add Expense" button
        addExpenseButton.setOnClickListener { addExpense() }


        supportFragmentManager.beginTransaction()
            .replace(R.id.footerContainer, FooterFragment())
            .commit()
        // Set click listener for the "Financial Tips" button
        val financialTipsButton = findViewById<Button>(R.id.buttonFinancialTips)
        financialTipsButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nerdwallet.com/h/category/personal-finance"))
            startActivity(intent)
        }
    }
    private fun addExpense() {
        val name = expenseNameEditText.text.toString().trim()
        val amountStr = expenseAmountEditText.text.toString().trim()
        val date = expenseDateEditText.text.toString().trim()

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

                // Update total expenses in FooterFragment
                updateTotalExpenses()
            } else {
                Toast.makeText(this, "Invalid amount!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a name, amount, and date!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTotalExpenses() {
        val totalExpense = expenseList.sumOf { it.amount }
        val footerFragment = supportFragmentManager.findFragmentById(R.id.footerContainer) as FooterFragment
        footerFragment.updateTotalExpense(totalExpense)
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