package com.abit8.geeksmentor.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.data.model.Mentors
import com.abit8.geeksmentor.databinding.List1ItemBinding
import com.abit8.geeksmentor.databinding.List2ItemBinding

class MentorsUxUiAdapter(private val mentorUxUiList: List<Mentors>) : RecyclerView.Adapter<MentorsUxUiAdapter.MentorsUxUiViewHolder>(){

    inner class MentorsUxUiViewHolder(binding: List2ItemBinding) : RecyclerView.ViewHolder(binding.root){
        val mentorsImageView : ImageView = binding.rv2Img
        val mentorsNames : TextView = binding.rv2Name
        val mentorsStudentNumber : TextView = binding.rv2PersonNum
        val mentorsLikes : TextView = binding.rv2LikeNum
        val mentorsDislikes : TextView = binding.rv2DislikeNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsUxUiViewHolder {
        return MentorsUxUiViewHolder(List2ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MentorsUxUiViewHolder, position: Int) {
        val mentors = mentorUxUiList[position]
        holder.mentorsImageView.setImageResource(mentors.image)
        holder.mentorsNames.text = mentors.name
        holder.mentorsStudentNumber.text = mentors.studentNumber.toString()
        holder.mentorsLikes.text = mentors.likes.toString()
        holder.mentorsDislikes.text = mentors.dislikes.toString()
    }

    override fun getItemCount(): Int {
        return mentorUxUiList.size
    }
}