package com.example.lugares_j.repository

import androidx.lifecycle.LiveData
import com.example.lugares_j.data.LugarDAO
import com.example.lugares_j.model.Lugar

class LugarRepository(private val lugarDao : LugarDAO) {


    suspend fun saveLugar(lugar: Lugar){
        if(lugar.id ==0) // es un lugar nuevo
        {
            lugarDao.addLugar(lugar)
        }else{ // esu un lugar ya registrado
            lugarDao.updatedLugar(lugar)
        }
    }

    suspend fun deleteLugar(lugar: Lugar){
        if(lugar.id !=0) //si el id tiene el valor..se intenta eliminar
        {
            lugarDao.deleteLugar(lugar)
        }
    }
    val getLugares : LiveData<List<Lugar>> = lugarDao.getLugares()

}