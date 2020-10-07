package com.ryanrvldo.shoppinglisttestdrivendevelopment.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.ryanrvldo.shoppinglisttestdrivendevelopment.R
import com.ryanrvldo.shoppinglisttestdrivendevelopment.util.Status.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import javax.inject.Inject

class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager,
) : Fragment(R.layout.fragment_add_shopping_item) {


    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

        subscribeToObservers()

        btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                etShoppingItemName.text.toString(),
                etShoppingItemAmount.text.toString(),
                etShoppingItemPrice.text.toString()
            )
        }

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

    private fun subscribeToObservers() {
        viewModel.currentImageUrl.observe(viewLifecycleOwner) {
            glide.load(it).into(ivShoppingImage)
        }

        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    SUCCESS -> Snackbar.make(
                        requireActivity().rootLayout,
                        "Added Shopping Item",
                        Snackbar.LENGTH_LONG
                    ).show()
                    ERROR -> Snackbar.make(
                        requireActivity().rootLayout,
                        result.message ?: "An unknown error occurred.",
                        Snackbar.LENGTH_LONG
                    ).show()
                    LOADING -> TODO()
                }
            }
        }
    }
}