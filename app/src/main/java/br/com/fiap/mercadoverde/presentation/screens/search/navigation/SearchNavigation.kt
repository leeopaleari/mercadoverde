package br.com.fiap.mercadoverde.presentation.screens.search.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.search.SearchScreen

object SearchDestination : INavigationDestination {
    override val route = "search_route"
    override val destination = "search_destination"
}

fun NavGraphBuilder.searchGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
) {
    composable(route = SearchDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        SearchScreen()
    }
}