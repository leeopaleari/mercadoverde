package br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.navigation.SignInDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.SignUpScreen

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
//        val signUpViewModel = it.sharedViewModel<SignUpViewModel>(navController)

        SignUpScreen(
            onPopBackStack = {
                navController.navigateUp();
            },
//            viewModel = signUpViewModel
        )
    }


}