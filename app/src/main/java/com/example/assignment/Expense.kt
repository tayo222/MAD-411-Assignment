package com.example.assignment

import org.json.JSONObject

data class Expense(val name: String, val amount: Double, val date: String) {

    fun toJson(): String {
        return JSONObject().apply {
            put("name", name)
            put("amount", amount)
            put("date", date)
        }.toString()
    }

    companion object {

        fun fromJson(jsonString: String): Expense {
            val json = JSONObject(jsonString)
            return Expense(
                name = json.getString("name"),
                amount = json.getDouble("amount"),
                date = json.getString("date")
            )
        }
    }
}