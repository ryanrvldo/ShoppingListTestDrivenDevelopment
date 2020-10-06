package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R

class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private val viewModel by activityViewModels<ShoppingViewModel>()
}