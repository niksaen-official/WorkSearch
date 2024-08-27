package com.niksaen.worksearch.ui.adapters.delegates

import android.widget.EditText
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.SearchFieldModel

val searchFieldAdapterDelegate = adapterDelegate<SearchFieldModel,BaseModel>(R.layout.item_seacrh_field){
    val field = findViewById<EditText>(R.id.searchField)

    bind {
        field.hint = item.hint
    }
}