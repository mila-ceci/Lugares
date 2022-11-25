package com.example.lugares_j.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lugares_j.data.LugarDAO
import com.example.lugares_j.model.Lugar

class LugarRepository(private val lugarDao : LugarDAO) {


    suspend fun saveLugar(lugar: Lugar){
        lugarDao.addLugar(lugar)
    }

    suspend fun deleteLugar(lugar: Lugar){
         lugarDao.deleteLugar(lugar)

    }
    val getLugares : MutableLiveData<List<Lugar>> = lugarDao.getLugares()

}