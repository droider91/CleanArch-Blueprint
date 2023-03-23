package com.arch.data.di

import android.app.Application
import com.arch.data.database.AppDatabase
import com.arch.data.database.DatabaseProperties
import com.arch.data.database.dao.ToDoEntityDao
import com.arch.data.database.dao.UserEntityDao
import com.arch.data.source.todo.local.ToDoDataSource
import com.arch.data.source.todo.local.ToDoLocalSourceImpl
import com.arch.data.source.user.local.UserLocalDataSource
import com.arch.data.source.user.local.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {


    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase =
        DatabaseProperties.getInstance(application)

    @Provides
    fun providerUserDao(appDatabase: AppDatabase): UserEntityDao =
        appDatabase.userDao()

    @Singleton
    @Provides
    fun userLocalSource(userEntityDao: UserEntityDao): UserLocalDataSource =
        UserLocalDataSourceImpl(userEntityDao)


    @Provides
    fun providerToDoDao(appDatabase: AppDatabase): ToDoEntityDao =
        appDatabase.toDoDao()

    @Singleton
    @Provides
    fun toDoLocalSource(toDoEntityDao: ToDoEntityDao): ToDoDataSource =
        ToDoLocalSourceImpl(toDoEntityDao)
}
