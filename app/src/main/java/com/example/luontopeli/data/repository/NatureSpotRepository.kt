package com.example.luontopeli.data.repository

import com.example.luontopeli.data.local.dao.NatureSpotDao
import com.example.luontopeli.data.local.entity.NatureSpot
import kotlinx.coroutines.flow.Flow

class NatureSpotRepository(
    private val dao: NatureSpotDao
) {
    val allSpots: Flow<List<NatureSpot>> = dao.getAllSpots()
    val spotsWithLocation: Flow<List<NatureSpot>> = dao.getSpotsWithLocation()

    suspend fun insertSpot(spot: NatureSpot) {
        dao.insert(spot)
    }

    suspend fun deleteSpot(spot: NatureSpot) {
        dao.delete(spot)
    }

    suspend fun getUnsyncedSpots(): List<NatureSpot> = dao.getUnsyncedSpots()
}
