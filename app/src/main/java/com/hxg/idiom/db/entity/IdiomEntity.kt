package com.hxg.idiom.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 成语基本信息
 *
 * id：成语的编号
 * word：成语内容
 * pinyin：成语的拼音
 * explain：成语的解释
 * from：成语的出处
 * example：成语的使用例子
 */
@Entity(tableName = "idiom_data")
class IdiomEntity() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var word: String? = null
    var pinyin: String? = null
    var explain: String? = null
    var from: String? = null
    var example: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        word = parcel.readString()
        pinyin = parcel.readString()
        explain = parcel.readString()
        from = parcel.readString()
        example = parcel.readString()
    }

    override fun toString(): String {
        return "IdiomEntity(id=$id, word=$word, pinyin=$pinyin, explain=$explain, from=$from, example=$example)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(word)
        parcel.writeString(pinyin)
        parcel.writeString(explain)
        parcel.writeString(from)
        parcel.writeString(example)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IdiomEntity> {
        override fun createFromParcel(parcel: Parcel): IdiomEntity {
            return IdiomEntity(parcel)
        }

        override fun newArray(size: Int): Array<IdiomEntity?> {
            return arrayOfNulls(size)
        }
    }
}
