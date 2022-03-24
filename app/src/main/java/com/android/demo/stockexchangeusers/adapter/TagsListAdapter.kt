package com.android.demo.stockexchangeusers.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.stockexchangeusers.databinding.AdapterTagsBinding
import com.android.demo.stockexchangeusers.databinding.AdapterUsersBinding
import com.android.demo.stockexchangeusers.repository.Items
import com.android.demo.stockexchangeusers.repository.Tags

class TagsListAdapter : RecyclerView.Adapter<TagViewHolder>() {

    var tagsList = mutableListOf<Tags>()
    lateinit var mcontext: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setTags(tags: List<Tags>) {
        this.tagsList = tags.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        mcontext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTagsBinding.inflate(inflater, parent, false)
        return TagViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {

        val tag = tagsList[position]

            holder.binding.tagName.text = tag.tagName
            holder.binding.tagPosts.text = tag.questionCount.toString().plus(" posts")


    }

    override fun getItemCount(): Int {
        return tagsList.size
    }


}

class TagViewHolder(val binding: AdapterTagsBinding) : RecyclerView.ViewHolder(binding.root) {

}