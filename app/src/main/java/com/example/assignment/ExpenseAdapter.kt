package com.example.assignment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val expenseList: MutableList<Expense>,
    private val onItemClickListener: (Int) -> Unit // Add this parameter
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseNameTextView: TextView = itemView.findViewById(R.id.textExpenseName)
        val expenseAmountTextView: TextView = itemView.findViewById(R.id.textExpenseAmount)
        val expenseDateTextView: TextView = itemView.findViewById(R.id.itemDate)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)
        val showDetailsButton: Button = itemView.findViewById(R.id.buttonShowDetails)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expenseList[position]
        holder.expenseNameTextView.text = currentExpense.name
        holder.expenseAmountTextView.text = currentExpense.amount.toString()
        holder.expenseDateTextView.text = currentExpense.date.toString()

        holder.showDetailsButton.setOnClickListener {
            onItemClickListener(position)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener(position) // Delegate to fragment
        }
    }
    override fun getItemCount(): Int = expenseList.size

}