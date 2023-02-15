package com.example.template.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.template.R
import com.example.template.screens.components.bottomNav.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingScree(navController: NavHostController, paddingValues: PaddingValues) {
    var userName by remember { mutableStateOf("") }
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Track a Car ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(painter = painterResource(id = R.drawable.map) , modifier =Modifier.height(350.dp).fillMaxWidth(), contentScale = ContentScale.FillBounds, alignment = Alignment.Center, contentDescription = null)
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = {
                userName = it
            },
            label = {
                Text(
                    text = "Enter Car Number",
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme
                    .surfaceVariant, textColor = MaterialTheme.colorScheme.onSurface
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface
            ), onClick = {
                navController.navigate(Screen.TrackingDetailsScreen.route)
            }) {
            Text(
                text = "Track Car",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }

}