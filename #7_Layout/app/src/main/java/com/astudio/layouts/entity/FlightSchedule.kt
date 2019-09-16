package com.astudio.layouts.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data [class] with information about flight schedule.
 *
 * @author Anna Zholud
 */
@Parcelize
data class FlightSchedule(val departFlight: Flight, val returnFlight: Flight) : Parcelable

