package com.example.uts_5_pppb_muhammadnajwanfadlillah

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uts_5_pppb_muhammadnajwanfadlillah.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
//            Get Array
            val monthList = resources.getStringArray(R.array.month)
            val kehadiranList = resources.getStringArray(R.array.kehadiran)

//            Initiate
            var selectedTime ="${timePicker.hour}:${timePicker.minute}"
            val _tempCalendar : Calendar = Calendar.getInstance()
            _tempCalendar.timeInMillis = System.currentTimeMillis()
            var selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"

//            Kehadiran Dropdown=======================================
            val adapterKehadiran = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                kehadiranList
            )
            adapterKehadiran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kehadiranSpinner.adapter = adapterKehadiran

            datepicker.init(
                datepicker.year,
                datepicker.month,
                datepicker.dayOfMonth
            ){
                    _,year,montOfYear,dayOfMonth ->
                selectedDate = "${dayOfMonth} ${monthList[montOfYear]} ${year}"
            }

            timePicker.setOnTimeChangedListener{
                    view,hourOfDay,minute ->
                selectedTime = "${hourOfDay}:${minute}"
            }

            keteranganEdittext.visibility = View.GONE
//            Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if(position !== 0){
                            keteranganEdittext.visibility = View.VISIBLE
                        } else {
                            keteranganEdittext.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            submitButton.setOnClickListener {
                Toast.makeText(this@MainActivity, "Presensi berhasil $selectedDate jam $selectedTime", Toast.LENGTH_SHORT).show()
            }


        }

    }
}