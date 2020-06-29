package com.example.reservasystemc

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.reservasystemc.PreferenceHelper.get
import com.example.reservasystemc.PreferenceHelper.set
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        BtnCreateAppointment.setOnClickListener{
             val intent = Intent(this,CreateAppointmentActivity::class.java)
            startActivity(intent)
    }
        BtnMyAppointment.setOnClickListener{
            val intent = Intent(this,AppointmentsActivity::class.java)
            startActivity(intent)
        }

        BtnLogout.setOnClickListener{
           clearSessionPreferences()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun clearSessionPreferences(){
        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE )
        val editor = preferences.edit()
        editor.putBoolean("session",true)
        editor.apply()
        */
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }
}


