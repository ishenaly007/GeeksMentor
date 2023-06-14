package com.abit8.geeksmentor.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abit8.geeksmentor.databinding.List1ItemBinding
import com.abit8.geeksmentor.domain.model.Mentor

class MentorsOfMonthAdapter(
    private val onClick: (Mentor) -> Unit
) : ListAdapter<Mentor, MentorsOfMonthAdapter.MentorViewHolder>(DiffUtilItemCallback()) {


    inner class MentorViewHolder(
        private val binding: List1ItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mentor: Mentor) {
            binding.rv1Img.load(mentor.image)
            binding.rv1Name.text = mentor.firstName
            binding.rv1PersonNum.text = mentor.age.toString()
            binding.rv1LikeNum.text = mentor.height.toString()
            binding.rv1DislikeNum.text = mentor.weight.toString()
            itemView.setOnClickListener {
                onClick(mentor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        return MentorViewHolder(
            List1ItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MentorsOfMonthAdapter.MentorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    @SuppressLint("DiffUtilEquals")
    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Mentor>() {
        override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean {
            return oldItem == newItem
        }
    }
}