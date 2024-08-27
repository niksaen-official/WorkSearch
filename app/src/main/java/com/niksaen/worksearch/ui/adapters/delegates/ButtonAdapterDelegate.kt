package com.niksaen.worksearch.ui.adapters.delegates

import android.widget.Button
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.ButtonListModel

val buttonAdapterDelegate = adapterDelegate<ButtonListModel,BaseModel>(R.layout.item_list_button){
    val button = findViewById<Button>(R.id.button)

    bind { payloads ->
        run {
            button.text = item.text
            button.setOnClickListener { item.onClickAction() }
        }
    }
}