package com.example.template.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.template.screens.AllCarsScreen
import com.example.template.screens.CarDetailsScreen
import com.example.template.screens.HomeScreen
import com.example.template.screens.Login
import com.example.template.screens.MissingDiaryScreen
import com.example.template.screens.PhoneLoginUI
import com.example.template.screens.StolenCarUpdatesScreen
import com.example.template.screens.TrackingDetailsScreen
import com.example.template.screens.TrackingScree
import com.example.template.screens.components.AllCarScreen
import com.example.template.screens.components.bottomNav.Screen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = startDestination) {
        composable(Screen.LoginScreen.route) {
            Login(navController)
        }
        composable(Screen.OtpScreen.route) {
            PhoneLoginUI(popUpScreen = { navController.navigate(Screen.StolenCarScreen.route) }) {
                navController.navigate(Screen.OtpScreen.route)
            }
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.CarDetailsScreen.route) {
            CarDetailsScreen(navController,paddingValues)
        }
        composable(Screen.TrackingDetailsScreen.route) {
            TrackingDetailsScreen(paddingValues)
        }
        composable(Screen.TrackerScreen.route) {
          TrackingScree(navController = navController, paddingValues =paddingValues )
        }
        composable(Screen.MissingDiaryScreen.route) {
         MissingDiaryScreen(paddingValues = paddingValues)
        }

        composable(Screen.CarHistoryScreen.route) {
            AllCarsScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(Screen.StolenCarScreen.route) {
            StolenCarUpdatesScreen(navController = navController,paddingValues)
        }
        composable(Screen.AllCarScreen.route){
            AllCarScreen(navController = navController, paddingValues =paddingValues )
        }
    }
}