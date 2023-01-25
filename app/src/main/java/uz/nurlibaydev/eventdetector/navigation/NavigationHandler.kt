package uz.nurlibaydev.eventdetector.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.SharedFlow

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:19
 */

typealias NavArgs = NavController.() -> Unit

interface NavigationHandler {
    val navStack: SharedFlow<NavArgs>
}