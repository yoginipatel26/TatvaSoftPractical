package com.example.yoginipatelpractical.di.modules

import com.example.yoginipatelpractical.api.UserInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitObj() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com/2.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    @Provides
    @Singleton
    fun provideUserInterfaceObj(retrofit: Retrofit) : UserInterface{
        return retrofit.create(UserInterface::class.java)
    }
}