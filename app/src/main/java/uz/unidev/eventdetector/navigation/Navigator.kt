package uz.unidev.eventdetector.navigation

import androidx.navigation.NavDirections

interface Navigator {

    suspend fun navigateTo(direction: NavDirections)

    suspend fun navigationUp()
}