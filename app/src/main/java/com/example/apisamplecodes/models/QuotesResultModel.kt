package com.example.apisamplecodes.models

data class QuotesResultModel(
    var _id: String,
    var author: String,
    var content: String,
    var tags: List<String>,
    var authorSlug: String,
    var length: Int,
    var dateAdded: String,
    var dateModified: String,

)
