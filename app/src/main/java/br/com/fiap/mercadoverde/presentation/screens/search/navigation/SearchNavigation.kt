package br.com.fiap.mercadoverde.presentation.screens.search.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.search.OrdersScreen

object OrdersDestination : INavigationDestination {
    override val route = "orders_route"
    override val destination = "orders_destination"
}

fun NavGraphBuilder.ordersGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
) {
    composable(route = OrdersDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        OrdersScreen()
    }
}