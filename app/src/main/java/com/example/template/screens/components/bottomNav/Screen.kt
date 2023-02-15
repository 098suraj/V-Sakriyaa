package com.example.template.screens.components.bottomNav

sealed class Screen(val route:String){
    object LoginScreen:Screen("LoginScreen")
    object OtpScreen:Screen("OtpScreen")
    object HomeScreen:Screen("HomeScreen")
    object StolenCarScreen:Screen("StolenCarScreen")
    object TrackingDetailsScreen:Screen("TrackingDetailsScreen")
    object CarDetailsScreen:Screen("CarDetailsScreen")
    object CarHistoryScreen:Screen("CarHistoryScreen")
    object TrackerScreen:Screen("TrackerScreen")
    object MissingDiaryScreen:Screen("MissingDiaryScreen")
    object AllCarScreen:Screen("AllCarScreen")

}
