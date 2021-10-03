package it.matteocrippa.sensorsmultiplatform.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import it.matteocrippa.sensorsmultiplatform.Sensors


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensors = Sensors(this)
        sensors.start()

        val tv: TextView = findViewById(R.id.text_view)

        sensors.data.watch { sensors ->
            sensors?.let {
                tv.text = it.toString()
            }
        }

    }
}
