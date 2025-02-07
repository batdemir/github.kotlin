package com.batdemir.github.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.data.local.dao.GithubDao

@Database(
    entities = [
        RepositoryModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "BatdemirGithub")
                .fallbackToDestructiveMigration()
                .build()
    }


    abstract fun githubDao(): GithubDao
}