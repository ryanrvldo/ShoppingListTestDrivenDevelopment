package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    private val viewModel by activityViewModels<ShoppingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingToAddShoppingItem())
        }
    }
}