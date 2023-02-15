package com.example.template.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.template.model.CarDetailsResponse
import com.example.template.screens.components.bottomNav.Screen
import com.example.template.viewModel.CarDetailsViewModel
import com.example.template.viewModel.CarsDetailsState

@Composable
fun CarDetailsScreen(navController: NavHostController, paddingValues: PaddingValues) {
    val viewModel = hiltViewModel<CarDetailsViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getCarDetails()

    }
    val data = viewModel.getDetails.collectAsState().value
    val imageUrl = viewModel.imageUrl.value

    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()

            .padding(20.dp)
    ) {
        when (data) {
            CarsDetailsState.Empty -> {}
            CarsDetailsState.Error -> {}
            CarsDetailsState.Loading -> {}
            is CarsDetailsState.Success -> {
                val carDetails = data.policeData as CarDetailsResponse
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds,
                        model = imageUrl,
                        loading = {
                            Box(modifier = Modifier.height(200.dp)) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }

                        },
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CarDetailsTile(carDetails)
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        modifier = Modifier
                            .width(250.dp)
                            .height(50.dp)
                            .padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                        ),
                        onClick = { navController.navigate(Screen.TrackingDetailsScreen.route) }) {
                        Text(
                            text = "View Tracking Details",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Lost Diary Details",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    LostDiaryDetailsTile(carDetails = carDetails)
                    Text(
                        text = "Car Owner Details",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CarOwnerDetails(carDetails = carDetails)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ), onClick = {
                            Toast.makeText(context, "Car Marked as Found", Toast.LENGTH_SHORT)
                                .show()
                        }) {
                        Text(
                            text = "Mark Car as Found",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun LostDiaryDetailsTile(carDetails: CarDetailsResponse) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .height(80.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Text(
                    text = carDetails.data.carDetails.carModel,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Filled on: ${carDetails.data.missingDetails.time}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Filled at: ${carDetails.data.missingDetails.policeStation}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Filled by Officer: Suraj Shukla",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }

        }
    )
}


@Composable
fun CarOwnerDetails(carDetails: CarDetailsResponse) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .height(80.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Text(
                    text = "Name : ${carDetails.data.owner.name}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Phone No: ${carDetails.data.owner.phone}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Address : KIET GROUP OF INSTITUTIONS , Ghaziabad",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    )
}

@Composable
fun CarDetailsTile(carDetails: CarDetailsResponse) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .height(70.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Row(modifier = Modifier.wrapContentHeight(), verticalAlignment = Alignment.Top) {
                    Text(
                        text = carDetails.data.carDetails.carModel,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "- ${carDetails.data.carDetails.color}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = carDetails.data.carDetails.carNumber,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    )
}
