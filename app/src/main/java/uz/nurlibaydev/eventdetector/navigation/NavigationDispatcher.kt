package uz.nurlibaydev.eventdetector.navigation

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:21
 */

class NavigationDispatcher @Inject constructor() : Navigator, NavigationHandler {

    override val navStack = MutableSharedFlow<NavArgs>()

    override suspend fun navigateTo(direction: NavDirections) = navigate {
        navigate(direction)
    }

    override suspend fun navigationUp() {}

    private suspend fun navigate(navArgs: NavArgs) {
        navStack.emit(navArgs)
    }
}