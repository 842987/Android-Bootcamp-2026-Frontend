package ru.innovationcampus.android.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import ru.innovationcampus.android.data.source.AuthLocalDataSource
import ru.innovationcampus.android.ui.screen.auth.AuthScreen
import ru.innovationcampus.android.ui.screen.list.ListScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // TODO: надо написать UseCase
    val currentToken = runBlocking { AuthLocalDataSource.getToken() }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (currentToken == null) AuthRoute else ListRoute
    ) {
        composable<AuthRoute> {
            AuthScreen(
                navController = navController
            )
        }
        composable<ListRoute> {
            ListScreen(
                navController = navController
            )
        }
    }
}