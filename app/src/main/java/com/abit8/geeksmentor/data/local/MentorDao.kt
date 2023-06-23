package com.abit8.geeksmentor.data.local

import androidx.room.Dao
import androidx.room.Query
import com.abit8.geeksmentor.data.model.MentorDto

@Dao
interface MentorDao {
    @Query("SELECT * FROM mentors")
    fun getMentors(): List<MentorDto>
}