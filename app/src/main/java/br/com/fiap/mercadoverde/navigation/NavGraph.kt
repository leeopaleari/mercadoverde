package br.com.fiap.mercadoverde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.screens.Cart.CartScreen
import br.com.fiap.mercadoverde.presentation.screens.Home.HomeScreen
import br.com.fiap.mercadoverde.presentation.screens.Profile.ProfileScreen
import br.com.fiap.mercadoverde.presentation.screens.Search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.CART_SCREEN) {
        composable(
            route = Route.HOME_SCREEN,
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Route.SEARCH_SCREEN,
        ) {
            SearchScreen()
        }

        composable(
            route = Route.CART_SCREEN,
        ) {
            CartScreen(navController)
        }

        composable(
            route = Route.PROFILE_SCREEN,
        ) {
            ProfileScreen()
        }
    }
}

object Route {
    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"
    const val CART_SCREEN = "cart"
    const val PROFILE_SCREEN = "profile"
}