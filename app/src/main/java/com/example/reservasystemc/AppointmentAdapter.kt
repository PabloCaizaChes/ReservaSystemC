package com.example.reservasystemc

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reservasystemc.model.Appointment
import kotlinx.android.synthetic.main.item_appointment.view.*

class AppointmentAdapter (private val appointments: ArrayList<Appointment>)
    : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

        //representa la referencia q va estar dentro de nuestra vista
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

            fun bind(appointment:Appointment) = with(itemView) {

               itemView.tvAppointmentId.text=context.getString(R.string.item_appointment_id,appointment.id)
               itemView.tvDoctorName.text=appointment.doctorName
               itemView.tvScheduleDate.text=context.getString(R.string.item_appointment_date,appointment.scheduledDate)
               itemView.tvScheduleTime.text=context.getString(R.string.item_appointment_time,appointment.scheduledTime)
           }



    }
    //llenamos o inflamos el xml y se lo va hacer cada vez que se llame al metodo oncreateviewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appointment,parent,false)

        )

    }
    //se encarga de enlazar o asociar la data con la vista del oncreateviewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment= appointments[position]
        holder.bind(appointment)


    }
    //retorna al activity appointments la cantidad de elementos que se desea inyectar en la vista padre
    //se puede definir como una simple expretion lo cual significa que solo tendra un valor definido
    override fun getItemCount()= appointments.size


}


