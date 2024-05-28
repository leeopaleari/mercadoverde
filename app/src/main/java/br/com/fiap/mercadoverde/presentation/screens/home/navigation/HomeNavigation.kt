package br.com.fiap.mercadoverde.presentation.screens.home.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.home.HomeScreen

object HomeDestination : INavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}
fun NavGraphBuilder.homeGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
) {
    composable(route = HomeDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        HomeScreen()
    }
}