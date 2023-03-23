package com.arch.usecase.todo

import com.arch.entity.AddToDoResponse
import com.arch.entity.ToDoData
import com.arch.error.AppError
import com.arch.error.AppErrorType
import com.arch.error.BaseError
import com.arch.repository.ToDoRepository
import com.arch.usecase.base.BaseUseCase
import com.arch.usecase.base.Params
import com.arch.utils.Either
import com.arch.utils.Validator
import javax.inject.Inject


class AddToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository,
) : BaseUseCase<AddToDoUseCase.ToDoParams, Either<BaseError, AddToDoResponse>> {

    override suspend fun execute(params: ToDoParams): Either<BaseError, AddToDoResponse> {
        return toDoRepository.addToDo(params.toDoData)
    }

    class ToDoParams(
        val toDoData: ToDoData
    ) : Params {
        override fun verify(): Boolean {
            return if (Validator.isEmpty(toDoData.title)) throw AppError(
                appErrorType = AppErrorType.TitleEmpty,
            )
            else if (Validator.isEmpty(toDoData.subTitle)) throw AppError(
                appErrorType = AppErrorType.SubTitleEmpty,
            )
            else true
        }

    }

}

