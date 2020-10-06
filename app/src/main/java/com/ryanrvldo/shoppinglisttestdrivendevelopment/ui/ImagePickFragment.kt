package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R

class ImagePickFragment : Fragment(R.layout.fragment_image_pick) {

    private val viewModel by activityViewModels<ShoppingViewModel>()
}