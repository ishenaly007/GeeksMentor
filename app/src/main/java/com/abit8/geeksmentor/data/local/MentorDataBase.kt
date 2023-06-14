package com.abit8.geeksmentor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abit8.geeksmentor.data.model.MentorEntity

@Database(entities = [MentorEntity::class], version = 1)
abstract class MentorDataBase : RoomDatabase() {
    abstract fun mentorDao(): MentorDao
}