package com.abit8.geeksmentor.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abit8.geeksmentor.data.model.Mentors
import com.abit8.geeksmentor.databinding.List1ItemBinding

class MentorsMonthAdapter(private val mentorList: List<Mentors>) : RecyclerView.Adapter<MentorsMonthAdapter.MentorsViewHolder>(){

    inner class MentorsViewHolder(binding: List1ItemBinding) : RecyclerView.ViewHolder(binding.root){
        val mentorsImageView : ImageView = binding.rv1Img
        val mentorsNames : TextView = binding.rv1Name
        val mentorsStudentNumber : TextView = binding.rv1PersonNum
        val mentorsLikes : TextView = binding.rv1LikeNum
        val mentorsDislikes : TextView = binding.rv1DislikeNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsViewHolder {
        return MentorsViewHolder(List1ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MentorsViewHolder, position: Int) {
        val mentors = mentorList[position]
        holder.mentorsImageView.setImageResource(mentors.image)
        holder.mentorsNames.text = mentors.name
        holder.mentorsStudentNumber.text = mentors.studentNumber.toString()
        holder.mentorsLikes.text = mentors.likes.toString()
        holder.mentorsDislikes.text = mentors.dislikes.toString()
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }
}