package com.abit8.geeksmentor.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abit8.geeksmentor.data.model.Mentors
import com.abit8.geeksmentor.databinding.List4ItemBinding

class MentorsFrontendAdapter(private val mentorList: List<Mentors>) : RecyclerView.Adapter<MentorsFrontendAdapter.MentorsFrontendViewHolder>(){

    inner class MentorsFrontendViewHolder(binding: List4ItemBinding) : RecyclerView.ViewHolder(binding.root){
        val mentorsImageView : ImageView = binding.rv4Img
        val mentorsNames : TextView = binding.rv4Name
        val mentorsStudentNumber : TextView = binding.rv4PersonNum
        val mentorsLikes : TextView = binding.rv4LikeNum
        val mentorsDislikes : TextView = binding.rv4DislikeNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsFrontendViewHolder {
        return MentorsFrontendViewHolder(List4ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MentorsFrontendViewHolder, position: Int) {
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