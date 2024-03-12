package br.com.fiap.mercadoverde.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
        composable(
            route = Route.HOME_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {
            HomeScreen()
        }

        composable(
            route = Route.SEARCH_SCREEN,
            enterTransition = {
                val route =
                    navController.previousBackStackEntry?.destination?.route;

                if (route == Route.HOME_SCREEN) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                    )
                } else {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                    )
                }
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {
            SearchScreen()
        }

        composable(
            route = Route.CART_SCREEN,
            enterTransition = {
                val route =
                    navController.previousBackStackEntry?.destination?.route;

                if (route == Route.PROFILE_SCREEN) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                    )
                } else {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                    )
                }
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {
            CartScreen()
        }

        composable(
            route = Route.PROFILE_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                )
            }
        ) {
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