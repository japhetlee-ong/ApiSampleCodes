package com.example.apisamplecodes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apisamplecodes.adapters.QuotesAdapter
import com.example.apisamplecodes.databinding.ActivityMainBinding
import com.example.apisamplecodes.models.QuotesModel
import com.example.apisamplecodes.models.QuotesResultModel
import com.example.apisamplecodes.services.helpers.RetrofitHelper
import com.example.apisamplecodes.services.repositories.QuotesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var quotesList : ArrayList<QuotesResultModel>
    private lateinit var adapter: QuotesAdapter
    private var page: Int = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quotesList = ArrayList()

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = QuotesAdapter(quotesList)
        binding.rvQuotesList.adapter = adapter
        binding.rvQuotesList.layoutManager = layoutManager
        binding.rvQuotesList.visibility = View.GONE
        binding.llLoadingContainer.visibility = View.VISIBLE
        binding.rvQuotesList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    if(!isLoading){
                        isLoading = true
                        page += 1
                        getQuotes(page)
                    }
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val result = quotesAPI.getQuotes()
            val quoteData = result.body()

            if(quoteData != null){
                withContext(Dispatchers.Main){
                    quotesList.addAll(quoteData.results)
                    binding.llLoadingContainer.visibility = View.GONE
                    binding.rvQuotesList.visibility = View.VISIBLE
                    adapter.updateQuotesData(quotesList)
                }
            }
        }
    }

    private fun getQuotes(pageNum : Int){
        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val result = quotesAPI.getQuotes(pageNum)
            val quoteData = result.body()

            if(quoteData != null){
                withContext(Dispatchers.Main){
                    quotesList.addAll(quoteData.results)
                    adapter.updateQuotesData(quotesList)
                    isLoading = false
                }
            }

        }
    }

}