package com.ryanrvldo.shoppinglisttestdrivendevelopment.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.ryanrvldo.shoppinglisttestdrivendevelopment.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*
* Small test because this is unit test
* */
@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase

    private lateinit var dao: ShoppingDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeAllShoppingItems() = runBlockingTest {
        val fakeShoppingItemList = mutableListOf<ShoppingItem>()
        for (i in 0 until 3) {
            val shoppingItem = ShoppingItem("name", (i + 1 * 2), (i + 1 * 2f), "url", i + 1)
            fakeShoppingItemList.add(shoppingItem)
            dao.insertShoppingItem(shoppingItem)
        }

        val shoppingItemList = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(shoppingItemList).isEqualTo(fakeShoppingItemList)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("name", 2, 10f, "url")
        val shoppingItem2 = ShoppingItem("name", 4, 5.5f, "url")
        val shoppingItem3 = ShoppingItem("name", 1, 100f, "url")

        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo((2 * 10f) + (4 * 5.5f) + (1 * 100f))
    }
//
//    @Test
//    fun testLaunchFragmentInHiltContainer() {
//        launchFragmentInHiltContainer<ShoppingFragment> {
//
//        }
//    }
}