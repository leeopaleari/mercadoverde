package br.com.fiap.mercadoverde.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.mercadoverde.presentation.navigation.MercadoVerdeNavHost
import br.com.fiap.mercadoverde.presentation.navigation.TOP_LEVEL_DESTINATIONS
import br.com.fiap.mercadoverde.presentation.navigation.TopLevelDestination
import br.com.fiap.mercadoverde.presentation.navigation.TopLevelNavigation
import br.com.fiap.mercadoverde.presentation.theme.BgColor
import br.com.fiap.mercadoverde.presentation.theme.MercadoVerdeTheme
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercadoVerdeTheme {
                MercadoVerdeApp()
            }
        }
    }
}

@Composable
fun MercadoVerdeApp() {
    Surface {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }

        val topLevelNavigation = remember(navController) {
            TopLevelNavigation(navController)
        }

        val bottomBarVisibility = rememberSaveable {
            mutableStateOf(true)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
//        val context = LocalContext.current

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            containerColor = BgColor,
            bottomBar = {
                Box {
                    AnimatedVisibility(
                        visible = bottomBarVisibility.value,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it }),
                        content = {
                            MercadoVerdeBottomBar(
                                onNavigateToTopLevelDestination = topLevelNavigation::navigateTo,
                                currentDestination = currentDestination,
                            )
                        }
                    )
                }
            }
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                MercadoVerdeNavHost(
                    navController = navController,
                    bottomBarVisibility = bottomBarVisibility,
                    modifier = Modifier
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .zIndex(1f)
                )
            }
        }
    }
}

@Composable
private fun MercadoVerdeBottomBar(
    currentDestination: NavDestination?,
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        NavigationBar(
            containerColor = BgColor,
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true

                NavigationBarItem(
                    selected = selected,
                    onClick =
                    {
                        onNavigateToTopLevelDestination(destination)
                    },
                    icon = {
                        Icon(
                            if (selected) {
                                destination.selectedIcon
                            } else {
                                destination.unselectedIcon
                            },
                            contentDescription = null
                        )
                    },
                    label = { Text(destination.iconText) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        indicatorColor = PrimaryColor
                    )
                )
            }
        }
    }
}