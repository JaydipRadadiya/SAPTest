package com.app.saptest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.saptest.databinding.ItemUserBinding
import com.app.saptest.model.UserModel
import com.app.saptest.utils.OnListItemClickListener
import com.bumptech.glide.Glide

class UserAdapter(private val onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var userList = mutableListOf<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val userModel = userList[position]
        holder.binding.txtName.text = userModel.name ?: ""
        holder.binding.txtDescription.text = userModel.description ?: ""
        if (!userModel.image.isNullOrEmpty()) {
            Glide.with(holder.itemView.context).load(userModel.image).into(holder.binding.imgUser)
        }
        holder.binding.root.setOnClickListener {
            onListItemClickListener.onItemClick(userModel = userModel)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<UserModel>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }
}

class MainViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)