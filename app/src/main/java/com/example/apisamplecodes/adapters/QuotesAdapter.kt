package com.example.apisamplecodes.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apisamplecodes.databinding.ContentQuotesRvBinding
import com.example.apisamplecodes.models.QuotesModel
import com.example.apisamplecodes.models.QuotesResultModel

class QuotesAdapter(private var quotesList: ArrayList<QuotesResultModel>): RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

    inner class QuotesViewHolder(val binding: ContentQuotesRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemData: QuotesResultModel){
            binding.txtQuote.text = itemData.content
            binding.txtAuthor.text = itemData.author
            binding.txtTagList.text = itemData.tags.joinToString(",")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val binding = ContentQuotesRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quotesData = quotesList[position]
        holder.bind(quotesData)
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateQuotesData(quotesList : ArrayList<QuotesResultModel>){
        this.quotesList.addAll(quotesList)
        this.notifyItemInserted(this.quotesList.size)
    }

}