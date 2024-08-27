package com.niksaen.worksearch.models
/**
 * Model for presentation Button in a RecyclerView
 */
data class ButtonListModel(
    val text:String,
    val onClickAction:()->Unit
):BaseModel