package com.chaudhry.najeeb.dogs.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chaudhry.najeeb.dogs.viewmodel.DogViewModel

@Composable
fun NavGraph(startDestination: String = "splash") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("list") {
            val viewModel: DogViewModel = hiltViewModel()
            DogListScreen(viewModel, navController)
        }
        composable("details/{dogId}") { backStackEntry ->
            val dogId = backStackEntry.arguments?.getString("dogId") ?: return@composable
            val viewModel: DogViewModel = hiltViewModel()
            DogDetailsScreen(dogId, viewModel, navController)
        }
    }
}