package com.astudio.layouts

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.astudio.layouts.entity.Flight
import com.astudio.layouts.entity.FlightSchedule
import com.astudio.layouts.entity.FlightType
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main class with container for two fragments.
 * The activity layout contains two buttons which responsible for navigation between fragments.
 *
 * @author Anna Zholud
 **/
class MainActivity : AppCompatActivity(), OnCheckModeListener {

    private val flightData = createData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFragmentToOne.setOnClickListener { replaceFragmentToOne() }
        btnFragmentToTwo.setOnClickListener { replaceFragmentToTwo() }
        if (savedInstanceState == null) {
            replaceFragmentToOne()
        }
    }

    private fun replaceFragmentToTwo() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FragmentTwo.newInstance(flightData))
            .commit()
    }

    private fun replaceFragmentToOne() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentOne.newInstance(flightData), ONE_FRAG_TAG)
            .commit()
    }

    private fun createData(): FlightSchedule {
        val departFlight = Flight(FlightType.DEPART, "16 SEP 2019", "435 BYN", "3", "MSQ", "FLO", "00:20", "09:20", "9:00")
        val returnFlight = Flight(FlightType.RETURN, "17 SEP 2019", "488 BYN", "5", "FLO", "MSQ", "05:10", "09:20", "4:10")
        return FlightSchedule(departFlight, returnFlight)
    }

    override fun onCheckMode() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, getString(R.string.night_theme), Toast.LENGTH_SHORT).show()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, getString(R.string.day_theme), Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val FLIGHT_AGRS_KEY = "FlightArguments"
        private const val ONE_FRAG_TAG = "FragmentOneTag"
    }
}
