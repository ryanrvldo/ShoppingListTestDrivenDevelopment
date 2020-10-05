package com.ryanrvldo.shoppinglisttestdrivendevelopment.data

import androidx.lifecycle.LiveData
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.local.ShoppingItem
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.responses.ImageResponse
import com.ryanrvldo.shoppinglisttestdrivendevelopment.util.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}