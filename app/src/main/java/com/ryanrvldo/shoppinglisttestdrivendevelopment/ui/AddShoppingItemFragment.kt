package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*

class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    private val viewModel by activityViewModels<ShoppingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: 10/7/20 CREATE UI TEST
        ivShoppingImage.setOnClickListener {
            findNavController().navigate(AddShoppingItemFragmentDirections.actionAddShoppingItemToImagePick())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // TODO: 10/7/20 if current image url is actually empty after we press back button
                viewModel.setCurrentImageUrl("")
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}