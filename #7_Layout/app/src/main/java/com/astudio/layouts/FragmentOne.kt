package com.astudio.layouts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.astudio.layouts.entity.FlightSchedule

import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_one.view.*

/**
 * Fragment class. The fragment layout uses only ConstraintLayout.
 * Floating action button can change day/night theme.
 *
 * @author Anna Zholud
 */
class FragmentOne : Fragment(){

    private var checkModeListener: OnCheckModeListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCheckModeListener) {
            checkModeListener = context
        }
        else {
            throw ClassCastException(context.toString() + " must implement OnCheckModeListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        arguments?.get(MainActivity.FLIGHT_AGRS_KEY)?.let { setViewData(it as FlightSchedule, view) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener { checkModeListener?.onCheckMode() }
    }

    private fun setViewData(flightShedule: FlightSchedule, view: View) {
        view.tv_depart_title.text = flightShedule.departFlight.flightType.toString()
        view.tv_depart_date.text = flightShedule.departFlight.date
        view.tv_depart_number_seats.text = flightShedule.departFlight.freeSeats
        view.tv_depart_from.text = flightShedule.departFlight.fromCity
        view.tv_depart_to.text = flightShedule.departFlight.toCity
        view.tv_depart_cost.text = flightShedule.departFlight.cost
        view.tv_depart_time_takeoff.text = flightShedule.departFlight.startTime
        view.tv_depart_time_land.text = flightShedule.departFlight.endTime
        view.tv_depart_flight_time.text = flightShedule.departFlight.flightTime

        view.tv_return_title.text = flightShedule.returnFlight.flightType.toString()
        view.tv_return_date.text = flightShedule.returnFlight.date
        view.tv_return_number_seats.text = flightShedule.returnFlight.freeSeats
        view.tv_return_from.text = flightShedule.returnFlight.fromCity
        view.tv_return_to.text = flightShedule.returnFlight.toCity
        view.tv_return_cost.text = flightShedule.returnFlight.cost
        view.tv_return_time_takeoff.text = flightShedule.returnFlight.startTime
        view.tv_return_time_land.text = flightShedule.returnFlight.endTime
        view.tv_return_flight_time.text = flightShedule.returnFlight.flightTime
    }

    companion object {
        fun newInstance(flightSchedule: FlightSchedule) : FragmentOne {
            val args = Bundle().apply { putParcelable(MainActivity.FLIGHT_AGRS_KEY, flightSchedule) }
            val fragment = FragmentOne().apply { arguments = args }
            return fragment
        }

    }
}