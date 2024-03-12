package br.com.fiap.mercadoverde.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.screens.Cart.CartScreen
import br.com.fiap.mercadoverde.presentation.screens.Home.HomeScreen
import br.com.fiap.mercadoverde.presentation.screens.Profile.ProfileScreen
import br.com.fiap.mercadoverde.presentation.screens.Search.SearchScreen
import br.com.fiap.mercadoverde.ui.common.ScreenContainer

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.HOME_SCREEN) {
        composable(Route.HOME_SCREEN) {
            HomeScreen()
        }
        composable(Route.SEARCH_SCREEN) {
            SearchScreen()
        }
        composable(Route.CART_SCREEN) {
            CartScreen()
        }
        composable(Route.PROFILE_SCREEN) {
            ProfileScreen()
        }
    }
}

object Route {
    val HOME_SCREEN = "home"
    val SEARCH_SCREEN = "search"
    val CART_SCREEN = "cart"
    val PROFILE_SCREEN = "profile"
}