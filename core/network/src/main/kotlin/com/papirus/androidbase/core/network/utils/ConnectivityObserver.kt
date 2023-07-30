package com.papirus.androidbase.core.network.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<ConnectionStatus>

    enum class ConnectionStatus {
        Available, Unavailable, Losing, Lost
    }
}