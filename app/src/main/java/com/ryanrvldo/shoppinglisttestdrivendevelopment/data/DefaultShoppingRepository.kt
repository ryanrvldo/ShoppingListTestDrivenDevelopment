package com.ryanrvldo.shoppinglisttestdrivendevelopment.data

import androidx.lifecycle.LiveData
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.local.ShoppingDao
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.local.ShoppingItem
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.PixabayApi
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.responses.ImageResponse
import com.ryanrvldo.shoppinglisttestdrivendevelopment.util.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) : ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayApi.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred")
            } else {
                Resource.error("An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection")
        }
    }
}