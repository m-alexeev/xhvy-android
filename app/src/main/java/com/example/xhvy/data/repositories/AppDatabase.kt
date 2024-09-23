package com.example.xhvy.data.repositories

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.domain.daos.ExerciseDAO
import com.example.xhvy.domain.daos.TemplateDAO
import com.example.xhvy.domain.daos.WorkoutDAO
import java.util.Date
import kotlin.concurrent.Volatile

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}


@Database(
    entities = [ExerciseEntity::class, WorkoutEntity::class, WorkoutExerciseEntity::class, ExerciseSetEntity::class],
    version = 8
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDAO
    abstract fun workoutDao(): WorkoutDAO
    abstract fun templateDao(): TemplateDAO

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
                    .addMigrations(
                        MIGRATION_1_2,
                        MIGRATION_2_3,
                        MIGRATION_3_4,
                        MIGRATION_4_5,
                        MIGRATION_5_6,
                        MIGRATION_6_7,
                        MIGRATION_7_8
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
