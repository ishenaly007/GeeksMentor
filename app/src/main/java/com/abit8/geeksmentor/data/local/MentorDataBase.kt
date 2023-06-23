package com.abit8.geeksmentor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abit8.geeksmentor.data.model.MentorDto

@Database(entities = [MentorDto::class], version = 1)
abstract class MentorDataBase : RoomDatabase() {
    abstract fun mentorDao(): MentorDao
}