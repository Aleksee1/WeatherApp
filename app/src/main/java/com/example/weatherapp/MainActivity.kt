package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        this.composable("MainScreen") {
                            MainScreenView(navController)
                        }
                        composable("ForecastScreen") {
                            ForecastScreenView()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    ) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
            )
        }
    )
    { contentPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
        {
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text (
                    text = stringResource(R.string.city_name),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
            }
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Row {
                    Column {
                        Text(
                            text = stringResource(R.string.real_temp) + "°",
                            fontWeight = FontWeight.Bold,
                            fontSize = 90.sp
                        )
                        Text(
                            text = "Feels like " + stringResource(R.string.feel_temp) + "°",
                            fontSize = 20.sp
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.clear_sky_day),
                        contentDescription = stringResource(R.string.picture),
                        Modifier.size(160.dp)
                    )
                }
            }
            Spacer (
                modifier = Modifier.size(20.dp)
            )
            Text (
                text = "Low " + stringResource(R.string.low_temp) + "°",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text (
                text = "High " + stringResource(R.string.high_temp) + "°",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text (
                text = "Humidity " + stringResource(R.string.humidity) + "%",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text (
                text = "Pressure " + stringResource(R.string.pressure) + " hPa",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp),
            )
            Spacer (
                modifier = Modifier.size(20.dp)
            )
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Button(
                    onClick = { navController.navigate("ForecastScreen") },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                )
                {
                    Text(
                        text = stringResource(R.string.forecast_title),
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}