package com.example.xhvy.data.repositories

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


internal val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE exercises ADD COLUMN 'deletable' INTEGER  DEFAULT 0 NOT NULL")
    }
}

internal val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Create tables for ExerciseSetEntity
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `exercise-sets` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `reps` INTEGER,
                `weight` REAL,
                `completed` INTEGER NOT NULL,
                `workoutExerciseId` INTEGER NOT NULL,
                FOREIGN KEY(`workoutExerciseId`) REFERENCES `workout-exercises`(`id`) ON DELETE CASCADE
            )
        """
        )

        // Create tables for WorkoutEntity
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `workouts` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `startTime` INTEGER NOT NULL,
                `endTime` INTEGER
            )
        """
        )

        // Create tables for WorkoutExerciseEntity
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `workout-exercises` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `completed` INTEGER NOT NULL,
                `exerciseId` INTEGER NOT NULL,
                `workoutId` INTEGER NOT NULL,
                FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON DELETE CASCADE,
                FOREIGN KEY(`workoutId`) REFERENCES `workouts`(`id`) ON DELETE CASCADE
            )
        """
        )
    }
}

internal val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE new_workouts (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `startTime` INTEGER NOT NULL,
                endTime INTEGER NOT NULL 
            )
        """)

        // Step 2: Copy data from old table to new table
        db.execSQL("""
            INSERT INTO new_workouts (id, name, startTime, endTime)
            SELECT id, name, startTime, COALESCE(endTime, 0)
            FROM workouts
        """)

        // Step 3: Drop the old table
        db.execSQL("DROP TABLE workouts")

        // Step 4: Rename the new table to the original table name
        db.execSQL("ALTER TABLE new_workouts RENAME TO workouts")
    }
}

internal val MIGRATION_4_5 = object : Migration(4,5){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE workouts ADD COLUMN active INTEGER NOT NULL DEFAULT 0")
    }
}

internal val MIGRATION_5_6 = object : Migration(5,6){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE workouts ADD COLUMN isTemplate INTEGER NOT NULL DEFAULT 0")
    }
}

internal val MIGRATION_6_7 = object : Migration(6,7){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE workouts ADD COLUMN archived INTEGER NOT NULL DEFAULT 0")
    }
}

internal val MIGRATION_7_8 = object : Migration(7,8) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE `workout-exercises` ADD COLUMN previousWorkoutExerciseId INTEGER")
        db.execSQL("ALTER TABLE `exercise-sets` ADD COLUMN previousSetId INTEGER")
    }
}