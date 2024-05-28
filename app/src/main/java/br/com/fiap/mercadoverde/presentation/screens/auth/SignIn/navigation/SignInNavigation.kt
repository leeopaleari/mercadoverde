package br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.SignInScreen
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.navigation.SignUpDestination

object SignInDestination : INavigationDestination {
    override val destination = "signin_destination"
    override val route = "signin_route"
}

fun NavGraphBuilder.SignInGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
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

            onNavigateToSignUpScreen = {
                navController.navigate(SignUpDestination.route)
            },
//            onNavigateToAppScaffold = {
//                navController.navigate(HomeDestination.route) {
//                    popUpTo(navController.graph.findStartDestination().id) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
        )
    }
}
