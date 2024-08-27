package com.niksaen.worksearch.ui.adapters.delegates

import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.TextModel

val vacanciesListHeaderAdapterDelegate = adapterDelegate<TextModel,BaseModel>(R.layout.item_list_header){
    val text = findViewById<TextView>(R.id.textView)
    bind {
        payloads -> text.text = item.text
    }
}