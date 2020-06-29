package com.example.reservasystemc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import com.example.reservasystemc.model.Appointment
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val appointements= ArrayList<Appointment>()
        appointements.add(
            Appointment(1,"Pablo Caiza","12/02/2019","2:00 PM")
        )
        appointements.add(Appointment(2,"David Caiza","12/03/2019","5:00 PM")
        )
        appointements.add(Appointment(3,"David Caiza","12/01/2019","4:00 PM")
        )

     rvAppointments.layoutManager = LinearLayoutManager(this )  //permite mostrar elmentos por filas
     rvAppointments.adapter =AppointmentAdapter(appointements)

    }


}
