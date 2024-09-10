package com.example.xhvy.data.repositories

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.domain.daos.ExerciseDAO
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
    version = 3
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDAO
    abstract fun workoutDao(): WorkoutDAO

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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE exercises ADD COLUMN 'deletable' INTEGER  DEFAULT 0 NOT NULL")
    }
}

private val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(db: SupportSQLiteDatabase) {
        // Create tables for ExerciseSetEntity
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `exercise-sets` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `reps` INTEGER,
                `weight` REAL,
                `completed` INTEGER NOT NULL,
                `workoutExerciseId` INTEGER NOT NULL,
                FOREIGN KEY(`workoutExerciseId`) REFERENCES `workout-exercises`(`id`) ON DELETE CASCADE
            )
        """)

        // Create tables for WorkoutEntity
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `workouts` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `startTime` INTEGER NOT NULL,
                `endTime` INTEGER
            )
        """)

        // Create tables for WorkoutExerciseEntity
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `workout-exercises` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `completed` INTEGER NOT NULL,
                `exerciseId` INTEGER NOT NULL,
                `workoutId` INTEGER NOT NULL,
                FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON DELETE CASCADE,
                FOREIGN KEY(`workoutId`) REFERENCES `workouts`(`id`) ON DELETE CASCADE
            )
        """)
    }
}

