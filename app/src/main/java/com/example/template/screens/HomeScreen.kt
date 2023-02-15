package com.example.template.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.template.Navigation.NavigationGraph
import com.example.template.model.PoliceData
import com.example.template.screens.components.bottomNav.BottomNavigationBar
import com.example.template.screens.components.bottomNav.Screen
import com.example.template.viewModel.HomeScreenState
import com.example.template.viewModel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(key1 = Unit) {
        viewModel.getPoliceData()
    }
    val navController = rememberNavController()
    var policeData = viewModel.policeDataResponse.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            if(currentRoute(navController = navController) == Screen.LoginScreen.route || currentRoute(
                navController = navController
            ) == Screen.OtpScreen.route)TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colorScheme.background,
                content = {})
            else
            TopAppBar(
                modifier = Modifier.height(80.dp),
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (policeData) {
                        HomeScreenState.Empty -> {}
                        HomeScreenState.Error -> {}
                        HomeScreenState.Loading -> {}
                        is HomeScreenState.Success -> {
                            val policeData = policeData.policeData as PoliceData
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.tertiary)

                            ) {
                                SubcomposeAsyncImage(
                                    modifier = Modifier
                                        .height(57.dp)
                                        .width(57.dp)
                                        .align(Alignment.Center)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.FillHeight,
                                    model = policeData.image,
                                    loading = {
                                        CircularProgressIndicator()
                                    },
                                    contentDescription = null
                                )

                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {

                                policeData.name?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                                policeData.policeStation?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }

                            }
                        }


                    }

                }
            }
        },
        content = {
            NavigationGraph(
                navController = navController,
                startDestination =
                Screen.MissingDiaryScreen.route,
                paddingValues = it
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}