package com.example.template.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.template.model.CarResponce
import com.example.template.screens.components.bottomNav.Screen
import com.example.template.utils.constants.Constants
import com.example.template.viewModel.StolenCarDetailsViewModel
import com.example.template.viewModel.StolenCarState
import java.text.SimpleDateFormat

@Composable
fun StolenCarUpdatesScreen(navController: NavController, paddingValues: PaddingValues) {
    var viewModel = hiltViewModel<StolenCarDetailsViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCarDetailsApi()
    }

    val data = viewModel.stolenCarDetailsApi.collectAsStateWithLifecycle().value
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Stolen Car Updates",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(10.dp))
        when (data) {
            StolenCarState.Empty -> {}
            StolenCarState.Error -> {}
            StolenCarState.Loading -> {}
            is StolenCarState.Success -> {
                val carResponce = data.policeData as CarResponce

                LazyColumn(
                    content = {
                        items(carResponce.data) {
                            CarDetailsList(it,navController)
                        }
                    })
            }
        }
    }


}


@Composable
fun CarDetailsList(policeData: CarResponce.Data, navController: NavController) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .clickable {
                Constants.carNumber=policeData.id
                navController.navigate(Screen.CarDetailsScreen.route) }
            .padding(5.dp)
            .wrapContentHeight()
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Text(
                    text = policeData.missingDetails.carNumber, style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Spotted car at 📌 ${policeData.missingDetails.place} ${getData(policeData.missingDetails.time)} registered by ${policeData.missingDetails.policeStation} ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    )

}

fun getData(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val formatter = SimpleDateFormat("YYYY-MM-DD HH:mm")
    return formatter.format(parser.parse(date))
}