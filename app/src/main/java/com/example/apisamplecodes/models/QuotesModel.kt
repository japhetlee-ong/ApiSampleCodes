package com.example.apisamplecodes.models

data class QuotesModel(
    var count: Int,
    var totalCount: Int,
    var page: Int,
    var totalPages: Int,
    var lastItemIndex: Int,
    var results : ArrayList<QuotesResultModel>
)
