package com.example.newsapp.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.Model.Article
import com.example.newsapp.R
import kotlinx.android.synthetic.main.list_row.view.*

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    var articleList: List<Article>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false))


    override fun getItemCount(): Int {
        return articleList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList?.get(position)
        holder.populateItem(article!!)
    }

    fun setNewsList(newsList: List<Article>) {
        articleList = newsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateItem(article: Article) {
            itemView.tvAuthor.text = article.author
            itemView.tvSource.text = article.source.name
            itemView.tvTitle.text = article.title
            Glide.with(itemView)
                .load(article.urlToImage)
                .into(itemView.ivNewsArticleImage)
        }
    }
}
