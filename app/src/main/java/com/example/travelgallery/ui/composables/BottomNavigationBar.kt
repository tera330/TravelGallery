package com.example.travelgallery.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelgallery.ui.data.BottomNavigationBarItem
import com.example.travelgallery.ui.data.bottomNavItems

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    tabItems: List<BottomNavigationBarItem>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = modifier) {
        tabItems.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                       Icon(
                           imageVector = screen.icon,
                           contentDescription = stringResource(id = screen.resourceId))
                       },
                label = {
                    Text(stringResource(id = screen.resourceId))
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()
    BottomNavigationBar(
        navController = navController,
        tabItems = bottomNavItems
    )
}
