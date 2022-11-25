package com.example.lugares_j.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.lugares_j.data.LugarDAO
import com.example.lugares_j.model.Lugar
import com.example.lugares_j.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LugarViewModel(application : Application) : AndroidViewModel(application)
{

    private val lugarRepository : LugarRepository = LugarRepository(LugarDAO())
    val getLugares:  MutableLiveData<List<Lugar>> = lugarRepository.getLugares


    fun saveLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            lugarRepository.saveLugar(lugar)
        }
    }


    fun deleteLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            lugarRepository.deleteLugar(lugar)
        }
    }


}