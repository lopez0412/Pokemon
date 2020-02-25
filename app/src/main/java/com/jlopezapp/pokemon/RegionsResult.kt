package com.jlopezapp.pokemon

import com.google.gson.annotations.SerializedName
import com.jlopezapp.pokemon.Objetos.RegionesObject

data class RegionsResult (@SerializedName("count") var count:Int, @SerializedName("results") var regiones: List<RegionesObject>)