package com.arch.presentation.model

import android.os.Parcelable
import com.arch.entity.ToDoData
import com.arch.utils.mapper.BaseLayerDataTransformer
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDoPresentation(
    var id: Int? = -1,
    var title: String? = "",
    var subTitle: String? = "",
) : Parcelable, BaseLayerDataTransformer<ToDoPresentation, ToDoData>() {
    override fun restore(data: ToDoData): ToDoPresentation {
        return ToDoPresentation(
            id = data.id,
            title = data.title,
            subTitle = data.subTitle,
        )
    }
}