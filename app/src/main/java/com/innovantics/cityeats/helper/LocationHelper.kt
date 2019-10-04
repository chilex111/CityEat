package com.innovantics.cityeats.helper

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.libraries.places.compat.AutocompleteFilter
import com.google.maps.android.SphericalUtil

object LocationHelper {
    val addressAutocompleteFilter = AutocompleteFilter.Builder()
        // .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
        .setCountry("NG")
        .build()
}

fun LatLng.toBounds(radiusInMeters: Double = 10000.0): LatLngBounds {
    val distanceFromCenterToCorner = radiusInMeters * Math.sqrt(2.0)
    val southwestCorner = SphericalUtil.computeOffset(this, distanceFromCenterToCorner, 225.0)
    val northeastCorner = SphericalUtil.computeOffset(this, distanceFromCenterToCorner, 45.0)
    return LatLngBounds(southwestCorner, northeastCorner)
}
