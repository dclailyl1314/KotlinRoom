package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Word")
data class Word @JvmOverloads constructor(
    @ColumnInfo(name = "english_word") var word: String,
    @ColumnInfo(name = "chinese_mearning") var mearn: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) {

    override fun toString(): String {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", mearning='" + mearn + '\'' +
                '}'
    }

}