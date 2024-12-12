package com.example.yoginipatelpractical.data

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoginipatelpractical.databinding.ItemRecyclerviewBinding
import com.example.yoginipatelpractical.models.Item
import com.example.yoginipatelpractical.models.UserResponseModel

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>()   {
    private var userList = ArrayList<Item>()

    fun interface OnSaveImageClickListener {
        fun onSaveImageClick(position: Int, user: Item)
    }

    private var listener: OnSaveImageClickListener? = null

    fun setUserList(userList: ArrayList<Item>){
        this.userList = userList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.binding.tvName.text = userList[position].display_name
        holder.binding.tvGoldCount.text = userList[position].badge_counts.gold.toString()
        holder.binding.tvSilverCount.text = userList[position].badge_counts.silver.toString()
        holder.binding.tvBronze.text = userList[position].badge_counts.bronze.toString()
        Glide.with(holder.itemView)
            .load(userList[position].profile_image)
            .into(holder.binding.ivUser)

        holder.binding.ivSave.setOnClickListener {
            listener?.onSaveImageClick(position, userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)  {}
}