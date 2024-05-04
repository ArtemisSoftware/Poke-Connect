package com.artemissoftware.pokeconnect

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.pokeconnect.features.navigation.HOME_GRAPH
import com.artemissoftware.pokeconnect.home.HomeScreen

const val ROOT_GRAPH = "root_graph"

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = startDestination,
    ) {

        composable(route = HOME_GRAPH) {
            HomeScreen()
        }
    }
}