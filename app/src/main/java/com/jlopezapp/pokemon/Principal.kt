package com.jlopezapp.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.jlopezapp.pokemon.Adapters.RegionsAdapter
import com.jlopezapp.pokemon.Interfaces.APIServices
import com.jlopezapp.pokemon.Objetos.RegionesObject
import kotlinx.android.synthetic.main.activity_principal.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Principal : AppCompatActivity() {

    private  var recycler: RecyclerView? = null
    private lateinit var listaregiones:List<RegionesObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)


        regiones()

        val user = FirebaseAuth.getInstance().currentUser
        var nombreuser = FirebaseAuth.getInstance().currentUser!!.displayName

            //Toast.makeText(this,"$nombreuser",Toast.LENGTH_SHORT).show()

        username.text = "Bienvenido $nombreuser"



        closesession.setOnClickListener {
            signOut()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // ...

            }
        // [END auth_fui_signout]
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun regiones() {
        doAsync {
            val call = getRetrofit().create(APIServices::class.java).getCharacterByName("region").execute()
        Log.e("URL","$call")
            val region = call.body() as RegionsResult
            uiThread {
                if(region.count>0) {
                    initCharacter(region)
                }else{
                    showErrorDialog()
                }
                //hideKeyboard()
            }
        }
    }

    private fun initCharacter(regiones: RegionsResult) {

        listaregiones = regiones.regiones

        var regionsadapter = RegionsAdapter(listaregiones)
        recyclerregions.setHasFixedSize(true)
        recyclerregions.layoutManager = LinearLayoutManager(this)
        recyclerregions.adapter = regionsadapter
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }


}
