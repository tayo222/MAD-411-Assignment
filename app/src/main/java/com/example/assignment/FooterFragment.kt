package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FooterFragment : Fragment() {

    private lateinit var totalExpenseTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_footer, container, false)
        totalExpenseTextView = view.findViewById(R.id.totalExpenseTextView)
        return view
    }

    fun updateTotalExpense(totalExpense: Double) {
        // Format the total expense as a dollar value
        totalExpenseTextView.text = "Total Expenses: $${String.format("%.2f", totalExpense)}"
    }
}