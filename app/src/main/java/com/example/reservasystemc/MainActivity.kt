package com.example.reservasystemc

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.Preferences
import com.example.reservasystemc.PreferenceHelper.get
import com.example.reservasystemc.PreferenceHelper.set
class MainActivity : AppCompatActivity() {
 //cuando se usa by lazy snackbar no tiene valor y cuando se quiera usar por primera vez hay recien se va a inicializar
    private val snackBar by lazy {
        Snackbar.make(mainLayout,R.string.press_back_again,Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//persistencia de datos de forma local existe alternativas usar base de datos sqlite,guardar archivo con informacion uso de preferencias

       /* val preferences = getSharedPreferences("general", Context.MODE_PRIVATE )
        val session = preferences.getBoolean("session", false )
       */
        val preferences = PreferenceHelper.defaultPrefs(this)


        if(preferences["session",false])
            goToMenuActivity()


        btn_login.setOnClickListener {
            //antes de enviar debemos validar cone l servidor los datos del usuario
            createSessionPreferences()
            goToMenuActivity()
        }
        tvGoToRegister.setOnClickListener {
            Toast.makeText(this,getString(R.string.please_fill_your_register_data),Toast.LENGTH_SHORT).show()

            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createSessionPreferences(){
        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE )
        val editor = preferences.edit()
        editor.putBoolean("session",true)
        editor.apply()
            */
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }


    private fun goToMenuActivity(){
        val intent = Intent(this,MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if(snackBar.isShown)
        snackBar.show()
        else
        super.onBackPressed()

    }
}
