package com.jlopezapp.pokemon.Interfaces

import com.jlopezapp.pokemon.Objetos.RegionesObject
import com.jlopezapp.pokemon.RegionsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServices {
    @GET
    fun getCharacterByName(@Url url:String): Call<RegionsResult>
}