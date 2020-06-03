package com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.cleanarchitecturegituserapp.databinding.ItemGitUserBinding
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter.GitUserListAdapter.GitUserViewHolder
import com.bumptech.glide.Glide
import javax.inject.Inject

class GitUserListAdapter @Inject constructor() : RecyclerView.Adapter<GitUserViewHolder>() {

    private val gitUserList = mutableListOf<GitUserEntity>()
    private var listener: ((GitUserEntity) -> Unit)? = null

    fun setData(list: List<GitUserEntity>) {
        gitUserList.clear()
        gitUserList.addAll(list)
        notifyDataSetChanged()
    }

    fun onAdapterClick(listener: (GitUserEntity) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserViewHolder {
        val binding =
            ItemGitUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gitUserList.size
    }

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        listener?.let { holder.bindData(gitUserList[position], it) }
    }

    inner class GitUserViewHolder(private val binding: ItemGitUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(gitUserEntity: GitUserEntity, listener: (GitUserEntity) -> Unit) {

            binding.tvUsername.text = gitUserEntity.login
            Glide.with(binding.view).load(gitUserEntity.avatarUrl).into(binding.imgGitUser)
            binding.root.setOnClickListener {
                listener(gitUserEntity)
            }
        }
    }

}