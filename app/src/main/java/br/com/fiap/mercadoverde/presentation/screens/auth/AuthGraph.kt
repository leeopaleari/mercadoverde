package br.com.fiap.mercadoverde.presentation.screens.auth

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.fiap.mercadoverde.presentation.navigation.INavigationDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.navigation.SignInDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.navigation.signInGraph
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.navigation.SignUpGraph

object AuthDestination : INavigationDestination {
    override val route = "auth_route"
    override val destination = "auth_destination"
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    bottomBarVisibility: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState
) {
    navigation(route = AuthDestination.route, startDestination = SignInDestination.route) {
        signInGraph(navController = navController, bottomBarVisibility = bottomBarVisibility, snackbarHostState = snackbarHostState)
        SignUpGraph(navController = navController, bottomBarVisibility = bottomBarVisibility)
    }
}