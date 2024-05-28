package br.com.fiap.mercadoverde.presentation.screens.cart.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.cart.CartScreen

object CartDestination : INavigationDestination {
    override val route = "cart_route"
    override val destination = "cart_destination"
}
fun NavGraphBuilder.cartGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
) {
    composable(route = CartDestination.route) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = true
        }
        CartScreen()
    }
}