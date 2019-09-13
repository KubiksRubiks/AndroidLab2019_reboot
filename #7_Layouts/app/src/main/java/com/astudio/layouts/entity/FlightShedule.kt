package com.astudio.layouts.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class FlightShedule {
    /**
     * Data class with information about flight schedule.
     *
     * @author Vlad Kornev
     */
    @Parcelize
    data class FlightShedule(val departFlight: Flight, val returnFlight: Flight) : Parcelable
}