package br.com.fiap.mercadoverde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.screens.cart.CartScreen
import br.com.fiap.mercadoverde.presentation.screens.home.HomeScreen
import br.com.fiap.mercadoverde.presentation.screens.profile.ProfileScreen
import br.com.fiap.mercadoverde.presentation.screens.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.CART_SCREEN) {
        composable(
            route = Route.HOME_SCREEN,
        ) {entry ->
            HomeScreen(navController)
        }

        composable(
            route = Route.SEARCH_SCREEN,
        ) {
            SearchScreen(navController)
        }

        composable(
            route = Route.CART_SCREEN,
        ) {
            CartScreen(navController)
        }

        composable(
            route = Route.PROFILE_SCREEN,
        ) {
            ProfileScreen(navController)
        }
    }
}

object Route {
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
    const val CART_SCREEN = "cart"
    const val PROFILE_SCREEN = "profile"
}