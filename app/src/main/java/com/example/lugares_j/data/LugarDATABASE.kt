package com.example.lugares_j.data

import android.R

import android.provider.CalendarContract.Instances
import android.view.textclassifier.TextClassificationContext
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lugares_j.model.Lugar


@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class LugarDATABASE : RoomDatabase() {

    abstract fun LugarDAO(): LugarDAO

    companion object {
        @Volatile
        private var INSTANCE: LugarDATABASE? = null


        fun getDatabse(context: Context): LugarDATABASE {
            val local = INSTANCE
            if (local != null) {
                return local
            }

            synchronized(this) {
                val instances = Room.databaseBuilder(
                    context.applicationContext,
                    LugarDATABASE::class.java,
                    "Lugar_database"
                ).build()
                INSTANCE = instances
                return instances

            }

        }
    }
}