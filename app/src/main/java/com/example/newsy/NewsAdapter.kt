package com.example.newsy

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsy.databinding.ArticleListItemBinding

class NewsAdapter(val a: Activity, private val articles: ArrayList<Article>) :
    Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ArticleListItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val b = ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(b)
    }

    override fun getItemCount(): Int = articles.size




    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.articleTxt.text = articles[position].title
        Glide
            .with(holder.binding.articleImage.context)
            .load(articles[position].urlToImage)
            .error(R.drawable.news_launcher_ii)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(holder.binding.articleImage)
        val url = articles[position].url
        holder.binding.articleContainer.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, url.toUri())
            a.startActivity(i)
        }
        holder.binding.shareFab.setOnClickListener {
            ShareCompat
                .IntentBuilder(a)
                .setType("text/plain")
                .setChooserTitle("Share article with: ")
                .setText(url)
                .startChooser()
        }
        holder.binding.articleTxt.isSelected = true
    }


}