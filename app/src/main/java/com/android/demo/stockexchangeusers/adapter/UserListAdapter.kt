package com.android.demo.stockexchangeusers.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.stockexchangeusers.UserDetailActivity
import com.android.demo.stockexchangeusers.databinding.AdapterUsersBinding
import com.android.demo.stockexchangeusers.repository.AllApi.USER_DATA
import com.android.demo.stockexchangeusers.repository.Items
import com.android.demo.stockexchangeusers.utils.ValidationUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class UserListAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var userList = mutableListOf<Items>()
    var userMainList = mutableListOf<Items>()
    lateinit var mcontext: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<Items>) {
        this.userList = users.toMutableList()
        this.userMainList = users.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSubUsers(users: List<Items>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        mcontext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUsersBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val user = userList[position]
        if (ValidationUtil.validateList(user)) {
            val userId = user.userId.toString()
            val userName = user.displayName
            holder.binding.name.text = userName
            holder.binding.id.text = userId
        }
        holder.binding.carddata.setOnClickListener {
            val intent = Intent(mcontext, UserDetailActivity::class.java).apply {
                val gson= Gson()
                val dataString = gson.toJson(user)
                putExtra(USER_DATA, dataString)
            }
            mcontext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    fun filter(query: CharSequence?) {
        val list = mutableListOf<Items>()

        // perform the data filtering
        if (!query.isNullOrEmpty()) {
            val filteredList = ArrayList<Items>()
            userMainList.forEach { items ->
                if (items.displayName?.lowercase()?.contains(query.toString().lowercase()) == true) {
                    filteredList.add(items)
                }
            }
            list.addAll(filteredList)
        } else {

            list.addAll(userMainList)
        }

        setSubUsers(list)
    }
}

class MainViewHolder(val binding: AdapterUsersBinding) : RecyclerView.ViewHolder(binding.root) {

}