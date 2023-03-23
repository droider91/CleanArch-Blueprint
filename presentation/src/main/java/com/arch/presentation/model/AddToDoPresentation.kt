package com.arch.presentation.model

import com.arch.entity.AddToDoResponse
import com.arch.utils.mapper.BaseLayerDataTransformer

data class AddToDoPresentation(
   var isAdded: Boolean = false
) : BaseLayerDataTransformer<AddToDoResponse, AddToDoResponse>() {
    override fun restore(response: AddToDoResponse): AddToDoResponse {
        return AddToDoResponse(
            isAdded = response.isAdded
        )
    }
}