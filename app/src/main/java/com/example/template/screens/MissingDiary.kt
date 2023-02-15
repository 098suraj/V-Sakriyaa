package com.example.template.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.template.model.MissingCarRequest
import com.example.template.viewModel.MissingDiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissingDiaryScreen(paddingValues: PaddingValues) {
    val viewmodel= hiltViewModel<MissingDiaryViewModel>()
    var officerName by remember { mutableStateOf("") }
    var carNumber by remember { mutableStateOf("") }
    var carModel by remember { mutableStateOf("") }
    var carColor by remember { mutableStateOf("") }
    var engineNumber by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var owneraddress by remember { mutableStateOf("") }
    var ownerphone by remember { mutableStateOf("") }
    var missingCarNumber by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var policeStation by remember { mutableStateOf("") }

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Officer's Name",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = officerName,
            onValueChange = {
                officerName = it
            },
            label = {
                Text(
                    text = "Name",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Car Details",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = carNumber,
            onValueChange = {
                carNumber = it
            },
            label = {
                Text(
                    text = "Car Number",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = carModel,
            onValueChange = {
                carModel = it
            },
            label = {
                Text(
                    text = "Car Model",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = engineNumber,
            onValueChange = {
                engineNumber = it
            },
            label = {
                Text(
                    text = "Engine Number",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = carColor,
            onValueChange = {
                carColor = it
            },
            label = {
                Text(
                    text = "Car Color",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Text(
            text = "Owner's Details",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ownerName,
            onValueChange = {
                ownerName = it
            },
            label = {
                Text(
                    text = "Owner's Name",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = owneraddress,
            onValueChange = {
                owneraddress = it
            },
            label = {
                Text(
                    text = "Owner's Address",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ownerphone,
            onValueChange = {
                ownerphone = it
            },
            label = {
                Text(
                    text = "Owner's Phone",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Text(
            text = "Missing Details",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = missingCarNumber,
            onValueChange = {
                missingCarNumber = it
            },
            label = {
                Text(
                    text = "Missing CarNumber",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = place,
            onValueChange = {
                place = it
            },
            label = {
                Text(
                    text = "Missing Place",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = policeStation,
            onValueChange = {
                policeStation = it
            },
            label = {
                Text(
                    text = "Missing Police Station",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurface
            ),

            )
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface
            ), onClick = {
                val carDetails=MissingCarRequest.CarDetails(carModel, carNumber,carColor,engineNumber)
                val ownerDetails=MissingCarRequest.Owner(owneraddress,ownerName,ownerphone)
                val missingCarDetials= MissingCarRequest.MissingDetails(missingCarNumber,place,policeStation)
                 val missingDiary= MissingCarRequest(carDetails,missingCarDetials,ownerDetails)
                viewmodel.saveData(missingDiary)
            }) {
            Text(
                text = "Save Data",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}