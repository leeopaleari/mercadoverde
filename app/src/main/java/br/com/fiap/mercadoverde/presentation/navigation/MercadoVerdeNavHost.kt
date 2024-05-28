package br.com.fiap.mercadoverde.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.fiap.mercadoverde.presentation.screens.auth.AuthDestination
import br.com.fiap.mercadoverde.presentation.screens.auth.AuthGraph
import br.com.fiap.mercadoverde.presentation.screens.cart.navigation.cartGraph
import br.com.fiap.mercadoverde.presentation.screens.home.navigation.homeGraph
import br.com.fiap.mercadoverde.presentation.screens.profile.navigation.profileGraph
import br.com.fiap.mercadoverde.presentation.screens.search.navigation.searchGraph

@Composable
fun MercadoVerdeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AuthDestination.route,
    bottomBarVisibility: MutableState<Boolean>,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility
        )
        cartGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility
        )
        searchGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility
        )
        profileGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility
        )

        AuthGraph(
            navController = navController,
            bottomBarVisibility = bottomBarVisibility
        )
    }
}