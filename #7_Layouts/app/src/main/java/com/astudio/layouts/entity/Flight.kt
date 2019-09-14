package com.astudio.layouts.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data [class] with flight information.
 *
 * @author Anna Zholud
 */
@Parcelize
data class Flight(
    val flightType: FlightType,
    val date: String,
    val cost: String,
    val freeSeats: String,
    val fromCity: String,
    val toCity: String,
    val startTime: String,
    val endTime: String,
    val flightTime: String
) : Parcelable