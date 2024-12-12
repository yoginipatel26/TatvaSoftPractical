package com.example.yoginipatelpractical.di.modules

import android.content.Context
import com.example.yoginipatelpractical.database.AppDatabase
import com.example.yoginipatelpractical.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseObj(@ApplicationContext context: Context) : AppDatabase{
        return  AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDaoObj(appDatabase: AppDatabase) : UserDao{
        return appDatabase.userDao()
    }
}