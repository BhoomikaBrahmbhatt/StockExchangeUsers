package com.android.demo.stockexchangeusers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.stockexchangeusers.databinding.AdapterUsersBinding
import com.android.demo.stockexchangeusers.repository.Items
import com.android.demo.stockexchangeusers.utils.ValidationUtil

class UserListAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var userList = mutableListOf<Items>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<Items>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUsersBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val user = userList[position]
        if (ValidationUtil.validateList(user)) {
            val userId= user.userId.toString()
            val userName = user.displayName
            holder.binding.name.text =userName
            holder.binding.id.text = userId
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class MainViewHolder(val binding: AdapterUsersBinding) : RecyclerView.ViewHolder(binding.root) {

}