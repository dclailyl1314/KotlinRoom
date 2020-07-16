package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.paging.ArticleAdapter
import com.example.roomtest.paging.PagingViewModel
import com.example.roomtest.paging.PostsLoadStateAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(PagingViewModel::class.java) }


    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        val refreshView: SmartRefreshLayout = findViewById(R.id.refreshLayout)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter.withLoadStateFooter(PostsLoadStateAdapter(adapter))
        //获取数据并渲染UI
        viewModel.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
        //监听刷新状态当刷新完成之后关闭刷新
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest {
                if(it.refresh !is LoadState.Loading){
                    refreshView.finishRefresh()
                }
            }
        }
        refreshView.setOnRefreshListener {
            adapter.refresh()
        }
    }
}