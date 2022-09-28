package com.hxg.idiom.db

import com.hxg.idiom.db.dao.IdiomDao
import com.hxg.idiom.db.entity.IdiomEntity

class IdiomRepository(private val dao: IdiomDao) {
    val allIdiom = dao.getAllIdiom()

    suspend fun add(idiomEntity: IdiomEntity) {
        dao.addIdiom(idiomEntity)
    }
}