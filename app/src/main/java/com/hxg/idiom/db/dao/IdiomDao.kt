package com.hxg.idiom.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hxg.idiom.db.entity.IdiomEntity

@Dao
interface IdiomDao {
    @Query("SELECT * FROM idiom_data ORDER BY pinyin ASC")
    fun getAllIdiom(): List<IdiomEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIdiom(idiom: IdiomEntity)

    @Query("SELECT * FROM idiom_data WHERE id=:id")
    fun getIdiomById(id: Int): IdiomEntity

    @Query("SELECT * FROM idiom_data WHERE word=:name")
    fun getIdiomByName(name: String): IdiomEntity

    @Query("DELETE FROM idiom_data")
    fun deleteAll()
}