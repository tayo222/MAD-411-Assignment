import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Expense
import com.example.assignment.ExpenseAdapter
import com.example.assignment.R
import org.json.JSONArray
import org.json.JSONObject

class ExpenseListFragment : Fragment() {
    private val expenseList = mutableListOf<Expense>()
    private lateinit var adapter: ExpenseAdapter
    private lateinit var recyclerView: RecyclerView
    private val FILENAME = "expenses.json"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_expense_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExpenseAdapter(expenseList) { position ->
            // Handle item click if needed
        }
        recyclerView.adapter = adapter

        loadExpenses()
    }

    fun addNewExpense(expense: Expense) {
        expenseList.add(expense)
        saveExpenses()
        adapter.notifyItemInserted(expenseList.size - 1)
    }

    private fun deleteExpense(position: Int) {
        expenseList.removeAt(position)
        saveExpenses()
        adapter.notifyItemRemoved(position)
    }

    private fun saveExpenses() {
        try {
            val jsonArray = JSONArray().apply {
                expenseList.forEach { expense ->
                    put(JSONObject().apply {
                        put("name", expense.name)
                        put("amount", expense.amount)
                        put("date", expense.date)
                    })
                }
            }

            requireContext().openFileOutput(FILENAME, Context.MODE_PRIVATE).use { output ->
                output.write(jsonArray.toString().toByteArray())
            }
        } catch (e: Exception) {
            Log.e("ExpenseListFragment", "Save error", e)
        }
    }

    private fun loadExpenses() {
        try {
            val file = requireContext().getFileStreamPath(FILENAME)
            if (!file.exists()) return

            val jsonString = requireContext().openFileInput(FILENAME)
                .bufferedReader()
                .use { it.readText() }

            val jsonArray = JSONArray(jsonString)
            expenseList.clear()

            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                expenseList.add(
                    Expense(
                    name = json.getString("name"),
                    amount = json.getDouble("amount"),
                    date = json.getString("date")
                )
                )
            }

            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
            Log.e("ExpenseListFragment", "Load error", e)
        }
    }
}