package com.niksaen.worksearch.ui.adapters.delegates

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.niksaen.worksearch.R
import com.niksaen.worksearch.models.BaseModel
import com.niksaen.worksearch.models.OffersListModel
import com.niksaen.worksearch.ui.adapters.OfferAdapter

fun offerListAdapterDelegate(onItemClick:(url:String)->Unit) = adapterDelegate<OffersListModel,BaseModel>(R.layout.item_offers_list){
    val list = findViewById<RecyclerView>(R.id.offers)

    bind {
        payloads -> list.adapter = OfferAdapter(item.offers,onItemClick)
    }
}