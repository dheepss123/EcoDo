package com.bangkit.ecodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trash::class], version = 2)
abstract class EcodoDatabase : RoomDatabase() {

    abstract fun trashDao(): TrashDao
}