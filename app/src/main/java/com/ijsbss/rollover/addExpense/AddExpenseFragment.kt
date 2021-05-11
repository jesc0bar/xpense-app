package com.ijsbss.rollover.addExpense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ijsbss.rollover.R
import com.ijsbss.rollover.data.db.AppDatabase
import com.ijsbss.rollover.data.db.ExpenseRepository
import com.ijsbss.rollover.databinding.FragmentAddExpenseScreenBinding

class AddExpenseFragment : Fragment(){
    private lateinit var addExpenseFragmentViewModel: AddExpenseFragmentViewModel
    private var _binding : FragmentAddExpenseScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_expense_screen, container,  false)
        val dao = AppDatabase.getInstance(requireActivity().application).expenseDao()
        val repository = ExpenseRepository(dao)
        addExpenseFragmentViewModel = AddExpenseFragmentViewModel(repository)
        binding.addExpenseViewModel = addExpenseFragmentViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.expense_save_button).setOnClickListener {
            if(addExpenseFragmentViewModel.inputName.value != null && addExpenseFragmentViewModel.inputAmount.value != null && addExpenseFragmentViewModel.inputCreditOrDebit.value != null ){
                val categoryId = arguments?.getInt("categoryId")!!
                val categoryName = arguments?.getString("categoryName")
                val categoryColor = arguments?.getInt("categoryColor")
                val totalSpent = arguments?.getFloat("totalSpent")!!
                val expectation = arguments?.getFloat("expectation")
                val rolloverPeriod = arguments?.getByte("rolloverPeriod")
                val date = arguments?.getString("date")

                val newTotalSpent = totalSpent + addExpenseFragmentViewModel.inputAmount.value!!.toFloat()

                addExpenseFragmentViewModel.inputExpense(categoryId, totalSpent)

                val bundle = bundleOf(
                        ("categoryId" to categoryId),
                        ("categoryName" to categoryName),
                        ("categoryColor" to categoryColor),
                        ("totalSpent" to newTotalSpent),
                        ("expectation" to expectation),
                        ("rolloverPeriod" to rolloverPeriod),
                        ("date" to date)

                )
                findNavController().navigate(R.id.action_AddExpenseScreenFragment_to_CategoryScreenFragment, bundle)
            }
            else{
                Toast.makeText(view.context, "Please Enter All The Information", Toast.LENGTH_LONG).show()
            }
        }

        view.findViewById<Button>(R.id.expense_cancel_button).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

