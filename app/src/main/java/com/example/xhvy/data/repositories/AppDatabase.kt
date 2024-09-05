package com.example.xhvy.data.repositories

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.domain.daos.ExerciseDAO
import kotlin.concurrent.Volatile


@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                Log.d("DB", "Creating DB")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "exercise-db"
                )
                    .createFromAsset("exercises.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

