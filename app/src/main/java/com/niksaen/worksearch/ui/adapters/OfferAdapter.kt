package com.niksaen.worksearch.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.worksearch.R
import com.niksaen.worksearch.accessToApi.models.Offer
import org.koin.java.KoinJavaComponent.inject

class OfferAdapter(
    private val offers: ArrayList<Offer>,
    private val onItemClick:(url:String)->Unit
) : RecyclerView.Adapter<OfferVH>() {

    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OfferVH(LayoutInflater.from(context).inflate(R.layout.item_offer,parent,false))

    override fun getItemCount() = offers.size

    override fun onBindViewHolder(holder: OfferVH, position: Int) {
        holder.bind(offers[position])
        holder.itemView.setOnClickListener {
            if(offers[position].link.isNotEmpty()){
                onItemClick(offers[position].link)
            }
        }
    }
}

class OfferVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val context: Context by inject(Context::class.java)
    private val icon = itemView.findViewById<ImageView>(R.id.imageView2)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val button = itemView.findViewById<TextView>(R.id.button)

    fun bind(offer: Offer){
        if(offer.id != null) {
            when (offer.id) {
                "near_vacancies" -> icon.setImageDrawable(getDrawable(context,R.drawable.near_vacancies))
                "level_up_resume" -> icon.setImageDrawable(getDrawable(context,R.drawable.level_up_resume))
                "temporary_job" -> icon.setImageDrawable(getDrawable(context,R.drawable.temporary_job))
            }
        }
        else icon.visibility = View.INVISIBLE

        title.text = offer.title
        if(offer.button != null){
            button.visibility = View.VISIBLE
            button.text = offer.button!!["text"]
        }

    }
}