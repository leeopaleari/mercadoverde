package br.com.fiap.mercadoverde.presentation.screens.auth.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.ui.SignUpScreen
import br.com.fiap.mercadoverde.presentation.screens.home.navigation.HomeDestination

object SignUpDestination : INavigationDestination {
    override val route = "signup_route"
    override val destination = "signup_destination"
}

fun NavGraphBuilder.SignUpGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>
) {
    composable(
        SignUpDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(durationMillis = 300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(durationMillis = 300)
            )
        }
    ) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
        }

        SignUpScreen(
            onPopBackStack = {
                navController.navigateUp();
            },
            onNavigateToHomeScreen = {
                navController.navigate(HomeDestination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
    }


}