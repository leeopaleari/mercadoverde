package br.com.fiap.mercadoverde.presentation.screens.profile.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.profile.ProfileScreen

object ProfileDestination : INavigationDestination {
    override val route = "profile_route"
    override val destination = "profile_destination"
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
) {
    composable(route = ProfileDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        ProfileScreen()
    }
}