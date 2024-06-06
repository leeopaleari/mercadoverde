package br.com.fiap.mercadoverde.presentation.screens.home.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.home.screens.HomeScreen

object HomeDestination : INavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState
) {
    composable(route = HomeDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        HomeScreen(snackbarHostState = snackbarHostState)
    }
}