package com.arch.di

import com.arch.repository.ResourceRepository
import com.arch.repository.ToDoRepository
import com.arch.repository.UserRepository
import com.arch.usecase.GetResourcesUseCase
import com.arch.usecase.LoginUseCase
import com.arch.usecase.todo.AddToDoUseCase
import com.arch.usecase.todo.GetToDoUseCase
import com.arch.usecase.todo.UpdateToDoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesLoginUseCase(userRepository: UserRepository): LoginUseCase =
        LoginUseCase(userRepository)

    @Provides
    fun providesGetResourcesUseCase(resourceRepository: ResourceRepository): GetResourcesUseCase =
        GetResourcesUseCase(resourceRepository)


    @Provides
    fun providesTodoUseCase(toDoRepository: ToDoRepository): GetToDoUseCase =
        GetToDoUseCase(toDoRepository)

    @Provides
    fun providesAddTodoUseCase(toDoRepository: ToDoRepository): AddToDoUseCase =
        AddToDoUseCase(toDoRepository)

    @Provides
    fun providesUpdateTodoUseCase(toDoRepository: ToDoRepository): UpdateToDoUseCase =
        UpdateToDoUseCase(toDoRepository)

}