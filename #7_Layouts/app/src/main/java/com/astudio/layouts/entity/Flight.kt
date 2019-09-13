package com.astudio.layouts.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * Simple [data class] for flight information
 * with parcelable implementation.
 *
 * @author Anna Zholud
 */
@Parcelize
data class Flight(
    val flightType: FlightType,
    val date: String,
    val freeSeats: Int,
    val price: Int,
    val takeOffCity: String,
    val landCity: String,
    val takeOffTime: String,
    val landTime: String,
    val duration: String
) : Parcelable