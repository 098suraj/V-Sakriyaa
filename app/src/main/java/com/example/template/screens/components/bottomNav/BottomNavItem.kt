package com.example.template.screens.components.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, var icon:ImageVector, val screen: String){
    object CarHistory: BottomNavItem("CarHistory",  icon =  Icons.Filled.CarCrash,Screen.CarHistoryScreen.route)
    object TrackerCar: BottomNavItem("My Network",Icons.Filled.TrackChanges,Screen.TrackerScreen.route,)
    object Home: BottomNavItem("home",Icons.Filled.Home,Screen.StolenCarScreen.route)
    object Missing: BottomNavItem("Notification",Icons.Filled.Book,Screen.MissingDiaryScreen.route)
}
