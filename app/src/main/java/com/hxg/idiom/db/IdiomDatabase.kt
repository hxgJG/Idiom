package com.hxg.idiom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hxg.idiom.db.dao.IdiomDao
import com.hxg.idiom.db.entity.IdiomEntity

@Database(entities = [IdiomEntity::class], version = 1, exportSchema = false)
abstract class IdiomDatabase : RoomDatabase() {
    abstract fun idiomDao(): IdiomDao

    companion object {
        private val MATCHER_1 = "拼音："
        private val MATCHER_2 = "释义："
        private val MATCHER_3 = "出处："
        private val MATCHER_4 = "示例："

        @Volatile
        private var INSTANCE: IdiomDatabase? = null

        fun getIdiomDatabase(context: Context): IdiomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    IdiomDatabase::class.java,
                    "idiom_data"
                ).build()
            }

            return INSTANCE!!
        }
    }
}