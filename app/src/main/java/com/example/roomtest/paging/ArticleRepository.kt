package com.example.roomtest.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

class ArticleRepository {
    fun getArticleData() = Pager(PagingConfig(pageSize = 10)){
        ArticleDataSource()
    }.flow

}