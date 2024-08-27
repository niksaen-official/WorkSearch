package com.niksaen.worksearch.models

import com.niksaen.worksearch.accessToApi.models.Offer
/**
 * Model for presentation offers list in a RecyclerView
 * */
data class OffersListModel(
    val offers:ArrayList<Offer>
):BaseModel