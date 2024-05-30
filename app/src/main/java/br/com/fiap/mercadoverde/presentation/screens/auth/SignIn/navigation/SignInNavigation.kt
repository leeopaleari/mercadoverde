package br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.SignInScreen
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.navigation.SignUpDestination
import br.com.fiap.mercadoverde.presentation.screens.home.navigation.HomeDestination

object SignInDestination : INavigationDestination {
    override val destination = "signin_destination"
    override val route = "signin_route"
}

fun NavGraphBuilder.signInGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState
) {
    composable(
        SignInDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(durationMillis = 300)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(durationMillis = 300)
            )
        }
    ) {
        LaunchedEffect(null) {
            bottomBarVisibility.value = false
        }

        SignInScreen(
            snackbarHostState = snackbarHostState,
            onNavigateToSignUpScreen = {
                navController.navigate(SignUpDestination.route)
            },
            onNavigateToHomeScreen = {
                navController.navigate(HomeDestination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
    }
}
