package com.example.lugares_j.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lugares_j.model.Lugar


@Dao
interface LugarDAO {

    //las funciones de bajo nivel para hacer un CRUD

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar (lugar: Lugar)



    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updatedLugar (lugar: Lugar)


    @Delete
    suspend fun deleteLugar (lugar: Lugar)


    @Query("SELECT * FROM LUGAR")
    fun getLugares() : LiveData<List<Lugar>>

}