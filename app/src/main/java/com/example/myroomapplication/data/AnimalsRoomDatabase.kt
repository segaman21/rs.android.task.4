package com.example.myroomapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Animal::class], version = 5)
abstract class AnimalsRoomDatabase : RoomDatabase() {

    abstract fun animalsDao(): AnimalsDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalsRoomDatabase? = null

        fun getDatabase(context: Context): AnimalsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalsRoomDatabase::class.java,
                    "animals_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}