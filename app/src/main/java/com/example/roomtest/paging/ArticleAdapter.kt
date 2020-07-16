package com.example.roomtest.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.R
import com.example.roomtest.beans.Article

class ArticleAdapter : PagingDataAdapter<Article,ArticleAdapter.ArticleViewHolder>(COMPARATOR) {

    companion object{
        val COMPARATOR=object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id==newItem.id
            }
        }
    }




    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvName.text = "我是数据"+getItem(position)?.id.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvname)
    }
}