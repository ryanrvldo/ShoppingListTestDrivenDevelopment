package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R

class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    private val viewModel by activityViewModels<ShoppingViewModel>()
}