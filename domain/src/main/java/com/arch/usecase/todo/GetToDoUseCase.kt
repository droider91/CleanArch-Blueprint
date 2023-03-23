package com.arch.usecase.todo

import androidx.paging.PagingConfig
import com.arch.entity.ToDoData
import com.arch.error.BaseError
import com.arch.repository.ToDoRepository
import com.arch.usecase.base.BaseUseCase
import com.arch.usecase.base.Params
import com.arch.utils.Either
import javax.inject.Inject


class GetToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository,
) : BaseUseCase<GetToDoUseCase.ToDoParams, Either<BaseError, List<ToDoData>>> {

    override suspend fun execute(params: ToDoParams): Either<BaseError, List<ToDoData>> {
        return toDoRepository.getToDoData(params.pagingConfig)
    }

    class ToDoParams(
        val pagingConfig: PagingConfig
    ) : Params {
        override fun verify(): Boolean {
            // Write your validations here
            return true
        }

    }

}

