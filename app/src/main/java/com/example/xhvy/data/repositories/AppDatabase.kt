package com.example.xhvy.data.repositories

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.domain.daos.ExerciseDAO
import kotlin.concurrent.Volatile


@Database(entities = [ExerciseEntity::class], version = 2)
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
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE exercises ADD COLUMN 'deletable' INTEGER  DEFAULT 0 NOT NULL")
    }
}

