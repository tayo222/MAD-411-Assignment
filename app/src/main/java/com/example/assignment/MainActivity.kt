package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val showNameButton = findViewById<Button>(R.id.showNameButton)
        val theResultText = findViewById<TextView>(R.id.theResultText)

        showNameButton.setOnClickListener{
                val enteredName = nameInput.text.toString().trim()
            theResultText.text = "hello, $enteredName"
        }
    }
}