package com.example.lugares_j.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.lugares_j.model.Lugar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase


class LugarDAO {

    //variable usdas oara poder generar la estructura en la nube

    private val coleccion1 = "lugaresApp"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private val collecion2 = "misLugares"

    //contien la connex a la base de datos
    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        //inicia la configuracion de firestore
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }

    fun addLugar (lugar: Lugar){
        //para definir un doc en el nube
        val documento : DocumentReference

        if(lugar.id.isEmpty()){
            //si etsa vacio... esun nuevo documen
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(collecion2)
                .document()
            lugar.id = documento.id
        }else{
            //si el id tiene algo.. entonces se modifica en documento
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(collecion2)
                .document(lugar.id)
        }

        //ahora ... se modifica o crea el documento
        documento.set(lugar) //actualiza el documento en la nube
            .addOnSuccessListener {
                Log.d("addLugar", "lugar creado/actualizado")
            }
            .addOnCanceledListener {
                Log.d("addLugar", "lugar NO creado/actualizado")
            }
    }



    fun deleteLugar (lugar: Lugar){
        //Se valida si el lugar tieneid.. para poder borrarlo
        if(lugar.id.isNotEmpty()){ // si no esta vacio se puede eliminar
            //si etsa vacio... esun nuevo documen
           firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(collecion2)
                .document(lugar.id)
               .delete()
               .addOnSuccessListener {
                   Log.d("deleteLugar", "lugar eliminado")
               }
               .addOnCanceledListener {
                   Log.d("deleteLugar", "lugar no eliminado")
               }

        }
    }
    fun getLugares() : MutableLiveData<List<Lugar>>{
        val listaLuagres = MutableLiveData<List<Lugar>>()
        firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collecion2)
            .addSnapshotListener { instantanea, e ->
                if( e != null) { //se dio un error capturando la imagen de info
                    return@addSnapshotListener
                }
                if (instantanea != null){
                    val lista = ArrayList<Lugar>()
                    //se recorre ls instantanea documento por documento
                    instantanea.documents.forEach{
                        val lugar = it.toObject(Lugar::class.java)
                        if (lugar != null ){ // si se puedo convertir el doc en lugar
                            lista.add(lugar)  //se agrega el lugar a la lista
                        }
                    }
                    listaLuagres.value = lista
                }
            }




        return listaLuagres
    }

}