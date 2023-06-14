package com.abit8.geeksmentor.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("mentors")
class MentorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val name: String,
    val mentoredStudents: Int,
    val likes: Int,
    val dislikes: Int
)