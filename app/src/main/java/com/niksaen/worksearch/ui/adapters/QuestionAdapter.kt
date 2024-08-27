package com.niksaen.worksearch.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niksaen.worksearch.R
import org.koin.java.KoinJavaComponent.inject

class QuestionAdapter(
    private val list:List<String>,
    private val onItemClick:(item:String)->Unit = {}
) : RecyclerView.Adapter<QuestionVH>() {

    private val context:Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuestionVH(LayoutInflater.from(context).inflate(R.layout.item_question,parent,false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: QuestionVH, position: Int) {
        holder.itemView.setOnClickListener {onItemClick(list[position])}
        holder.bind(list[position])
    }
}

class QuestionVH(itemView:View):RecyclerView.ViewHolder(itemView){
    fun bind(text:String){
        itemView.findViewById<TextView>(R.id.question).text = text
    }
}