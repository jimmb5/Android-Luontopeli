package com.example.luontopeli.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import com.example.luontopeli.location.LocationManager
import org.osmdroid.util.GeoPoint
import kotlinx.coroutines.flow.StateFlow

// AndroidViewModel saa Application-kontekstin — tarvitaan LocationManagerille
class MapViewModel(application: Application) : AndroidViewModel(application) {

    // LocationManager-instanssi — käyttää Applicationin kontekstia
    private val locationManager = LocationManager(application)

    // Delegoidaan StateFlowt suoraan LocationManagerilta
    val routePoints: StateFlow<List<GeoPoint>> = locationManager.routePoints
    val currentLocation: StateFlow<Location?> = locationManager.currentLocation

    fun startTracking() = locationManager.startTracking()
    fun stopTracking() = locationManager.stopTracking()
    fun resetRoute() = locationManager.resetRoute()

    override fun onCleared() {
        super.onCleared()
        // Pysäytä sijainnin seuranta kun ViewModel tuhotaan
        locationManager.stopTracking()
    }
}

// Apufunktio: muuntaa Long-aikaleiman luettavaksi merkkijonoksi
fun Long.toFormattedDate(): String {
    val sdf = java.text.SimpleDateFormat("dd.MM.yyyy HH:mm", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}
