package br.com.fiap.mercadoverde.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.mercadoverde.navigation.NavGraph
import br.com.fiap.mercadoverde.navigation.Route
import br.com.fiap.mercadoverde.presentation.theme.BgColor
import br.com.fiap.mercadoverde.presentation.theme.MercadoVerdeTheme
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercadoVerdeTheme {
                val navItems = listOf(
                    BottomNavigationItem(
                        title = "Início",
                        route = Route.HOME_SCREEN,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    ),
//                    BottomNavigationItem(
//                        title = "Busca",
//                        route = Route.SEARCH_SCREEN,
//                        selectedIcon = Icons.Filled.Search,
//                        unselectedIcon = Icons.Outlined.Search
//                    ),
                    BottomNavigationItem(
                        title = "Carrinho",
                        route = Route.CART_SCREEN,
                        selectedIcon = Icons.Filled.ShoppingCart,
                        unselectedIcon = Icons.Outlined.ShoppingCart
                    ),
                    BottomNavigationItem(
                        title = "Perfil",
                        route = Route.PROFILE_SCREEN,
                        selectedIcon = Icons.Filled.Person,
                        unselectedIcon = Icons.Outlined.Person
                    ),
                )

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        containerColor = BgColor,
                        bottomBar = {
                            NavigationBar(
                                containerColor = Color.White,
                                tonalElevation = 2.dp,
                                modifier = Modifier.graphicsLayer {
                                    clip = true
                                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                                    shadowElevation = 20f
                                }
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                navItems.forEach { item ->
                                    val isCurrentDestinationEqualRoute =
                                        currentDestination?.hierarchy?.any { it.route == item.route }

                                    NavigationBarItem(
                                        selected = isCurrentDestinationEqualRoute == true,
                                        onClick = {
                                            navController.navigate(route = item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (isCurrentDestinationEqualRoute == true) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.White,
                                            indicatorColor = PrimaryColor
                                        )
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}