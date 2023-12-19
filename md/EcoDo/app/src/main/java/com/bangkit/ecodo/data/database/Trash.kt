package com.bangkit.ecodo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_trash")
data class Trash(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "predictedClass")
    val predictedClass: String,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imageData: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trash

        if (id != other.id) return false
        if (predictedClass != other.predictedClass) return false
        return imageData.contentEquals(other.imageData)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + predictedClass.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}