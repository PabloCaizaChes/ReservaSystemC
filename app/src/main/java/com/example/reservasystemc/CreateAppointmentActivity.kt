package com.example.reservasystemc

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_appointment.*
import java.util.*

class CreateAppointmentActivity : AppCompatActivity() {
    private val selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)


        BtnNext.setOnClickListener {
            if(etDescription.text.toString().length<3){
                etDescription.error = getString(R.string.validate_appointment_description)

            }else {
            // continuamos al paso 2
                cvStep1.visibility= View.GONE
                cvStep2.visibility=View.VISIBLE
            }

        }
        BtnConfirmAppointment.setOnClickListener {
            Toast.makeText(this,"Cita Registrada Correctamente",Toast.LENGTH_SHORT).show()
            finish()
        }

        val options = arrayOf("Specialty A","Specialty B","Specialty C")
        spinnerSpecialties.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        val optionsdoc = arrayOf("Doctor A","Doctor B","Doctor C")
        spinnerDoctor.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsdoc)



    }
    fun onClickScheduledDate(v: View?){

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
            //Toast.makeText(this,"$y-$m-$d", Toast.LENGTH_SHORT).show()
            selectedCalendar.set(y, m, d)
            etScheduleDate.setText(
                resources.getString
                    (R.string.date_format,
                    y,
                    m.twoDigits(),
                    d.twoDigits()
                )
            )
            displayRadioButtons()
        }
        //fecha minima
        //fecha maxima

        val  datePickerDialog=DatePickerDialog(this, listener, year, month,dayOfMonth)
        val datePicker = datePickerDialog.datePicker
        //estableciendo los limites del calendario
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate = calendar.timeInMillis//fecha minima un dia mas
        calendar.add(Calendar.DAY_OF_MONTH, 29)
        datePicker.maxDate = calendar.timeInMillis//fecha maxima 30 dias despues
        //mostramos el dialog
        datePickerDialog.show()
    }

    private  fun displayRadioButtons(){
        //radioGroup.clearCheck()
        //radioGroup.removeAllViews()
        //radioGroup.checkedRadioButtonId
        selectedRadioButton = null
        radioGroupLeft.removeAllViews()
        radioGroupRight.removeAllViews()

        val hours = arrayOf("3:00PM","3:30PM","4:00PM","4:30PM")
        var goToLeft = true
        hours.forEach {

            val radioButton = RadioButton(this)
            radioButton.id= View.generateViewId()
            radioButton.text=it

            radioButton.setOnClickListener { view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true
            }
            if(goToLeft)
                radioGroupLeft.addView(radioButton)
            else
                radioGroupRight.addView(radioButton)
            goToLeft = !goToLeft
        }

    }

    private fun Int.twoDigits()
            = if (this>=10 ) this.toString()else "0$this"

    override fun onBackPressed() {
        if(cvStep2.visibility == View.VISIBLE){
            cvStep2.visibility=View.GONE
            cvStep1.visibility= View.VISIBLE

        }else if(cvStep1.visibility == View.VISIBLE){
            val builder=AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.dialog_create_appointment_exit_title))
            builder.setMessage(getString(R.string.dialog_create_appointment_exit_message))
            builder.setPositiveButton(getString(R.string.dialog_create_appointment_exit_positive_btn)) { _, _ ->
                finish()
            }
            builder.setNegativeButton(getString(R.string.dialog_create_appointment_exit_negative_btn)){ dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }



    }
}
