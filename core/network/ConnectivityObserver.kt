package com.papirus.androidbase.platform

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<ConnectionStatus>

    enum class ConnectionStatus {
        Available, Unavailable, Losing, Lost
    }
}