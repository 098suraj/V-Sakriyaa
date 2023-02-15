package com.example.template.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.template.model.CarDetailsResponse
import com.example.template.model.CarResponce
import com.example.template.viewModel.CarDetailsViewModel
import com.example.template.viewModel.CarsDetailsState
import com.example.template.viewModel.StolenCarState


@Composable
fun AllCarScreen(navController: NavController, paddingValues: PaddingValues) {
    val viewModel = hiltViewModel<CarDetailsViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getCarDetailsApi()
    }
    val dataStolenCar = viewModel.stolenCarDetailsApi.collectAsStateWithLifecycle().value

    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Results",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        when (dataStolenCar) {
            StolenCarState.Empty -> {}
            StolenCarState.Error -> {}
            StolenCarState.Loading -> {}
            is StolenCarState.Success -> {
                val carResponce = dataStolenCar.policeData as CarResponce
                LazyColumn(
                    content = {
                        itemsIndexed(carResponce.data.subList(0, list.size)) { index, it ->
                            CarTiles(index, it)
                        }
                    })
            }
        }
    }
}

val list = listOf<String>(
    "Maruti Swift",
    "Honda City ",
    "Hyundai Grand i10",
    "Mahindra Scorpio",
    "Tata Tiago",
    "Toyota Innova",
)
val imageList = listOf<String>(
    "https://i.postimg.cc/sf9VG0Hk/swift-Image.jpg",
    "https://i.postimg.cc/fT9FG4cp/hondacity.jpg",
    "https://i.postimg.cc/qRDST4ZV/i10.jpg",
    "https://i.postimg.cc/2yPt1TD0/scorpio1.jpg",
    "https://i.postimg.cc/PJLcf9BL/tiago.jpg",
    "https://i.postimg.cc/1RT27RqD/toyota.jpg"
)


@Composable
fun CarTiles(index: Int, dataCar: CarResponce.Data) {
    val viewModel = hiltViewModel<CarDetailsViewModel>()
    LaunchedEffect(key1 = Unit) {
        viewModel.getCarDetailsNew(dataCar.id)
    }
    val data = viewModel.getDetailsNew.collectAsStateWithLifecycle().value
    val imageUrl = viewModel.imageUrl.value

    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .height(80.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            when (data) {
                CarsDetailsState.Empty -> {}
                CarsDetailsState.Error -> {}
                CarsDetailsState.Loading -> {}
                is CarsDetailsState.Success -> {
                    val carDetails = data.policeData as CarDetailsResponse
                    Row(modifier = Modifier.fillMaxSize()) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .height(70.dp)
                                .width(80.dp),
                            contentScale = ContentScale.FillBounds,
                            model = imageList[index],
                            loading = {
                                Box(modifier = Modifier.width(70.dp)) {
                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                                }

                            },
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(modifier = Modifier.wrapContentHeight()) {
                                Text(
                                    text = list[index],
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
                                text = dataCar.missingDetails.carNumber,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                        }
                    }
                }
            }

        }
    )
}