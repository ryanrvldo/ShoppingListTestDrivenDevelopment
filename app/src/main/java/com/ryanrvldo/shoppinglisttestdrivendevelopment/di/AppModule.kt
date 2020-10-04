package com.ryanrvldo.shoppinglisttestdrivendevelopment.di

import android.content.Context
import androidx.room.Room
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.local.ShoppingItemDatabase
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.PixabayApi
import com.ryanrvldo.shoppinglisttestdrivendevelopment.util.Constants.BASE_URL
import com.ryanrvldo.shoppinglisttestdrivendevelopment.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingItemDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ShoppingItemDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideShoppingDao(database: ShoppingItemDatabase) = database.shoppingDao()

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(PixabayApi::class.java)
}