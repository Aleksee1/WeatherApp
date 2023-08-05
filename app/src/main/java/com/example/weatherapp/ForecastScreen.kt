package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreenView(viewModel: ForecastViewModel = hiltViewModel()) {

    val forecastData = viewModel.forecastConditionsData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }

    val forecastItems = mutableListOf<DayForecast>()

    val currentTime = currentTimeMillis()

    for (i in 0..15) {
        forecastItems.add(
            DayForecast(
                currentTime + (i * 86400000).toLong(),
                1688907645000 + (i * 60000).toLong(),
                1688954445000 - (i * 60000).toLong(),
                ForecastTemp(
                    72f + i,
                    65f + i,
                    80f + i,
                ),
                1023f + i,
                100 - i,
            )
        )
    }

    Scaffold (
        topBar = {
            TopAppBar (
                title = {
                    Text (
                        text = stringResource(R.string.forecast_title)
                    ) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
            )
        }
    )
    { contentPadding ->
        forecastData.let { forecastConditions ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            )
            {
                itemsIndexed(forecastItems) { index, item ->

                    val date = forecastConditions.value?.listOfForecasts?.get(index)?.date
                    val sunrise = forecastConditions.value?.listOfForecasts?.get(index)?.sunrise
                    val sunset = forecastConditions.value?.listOfForecasts?.get(index)?.sunset

                    var dateString = date.toString()
                    var sunriseString = sunrise.toString()
                    var sunsetString = sunset.toString()

                    if (date != null) {
                        dateString = SimpleDateFormat("MMM d").format(Date(date * 1000))
                    }
                    if (sunrise != null) {
                        sunriseString= SimpleDateFormat("hh:mma").format(Date(sunrise * 1000))
                    }
                    if (sunset != null) {
                        sunsetString= SimpleDateFormat("hh:mma").format(Date(sunset * 1000))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    )
                    {
                        AsyncImage(
                            model = "https://openweathermap.org/img/wn/${forecastConditions.value?.listOfForecasts?.get(index)?.weatherData?.get(0)?.iconName}@2x.png",
                            contentDescription = "${forecastConditions.value?.listOfForecasts?.get(index)?.weatherData?.get(0)?.description}@2x.png",
                            modifier = Modifier.size(60.dp),
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(60.dp)
                                .padding(end = 10.dp),
                        )
                        {
                            Text(
                                text = dateString,
                                fontSize = 16.sp,
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .width(130.dp)
                                .padding(start = 10.dp),
                        )
                        {
                            Text(
                                text = "Temp: " + forecastConditions.value?.listOfForecasts?.get(index)?.temp?.day?.toInt() + "°",
                                fontSize = 12.sp,
                            )
                            Row {
                                Text(
                                    text = "High: " + forecastConditions.value?.listOfForecasts?.get(index)?.temp?.max?.toInt() + "°",
                                    modifier = Modifier.padding(end = 5.dp),
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "Low: " + forecastConditions.value?.listOfForecasts?.get(index)?.temp?.min?.toInt() + "°",
                                    fontSize = 12.sp,
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .width(110.dp)
                                .padding(start = 10.dp),
                        )
                        {
                            Text(
                                text = "Sunrise: $sunriseString",
                                fontSize = 12.sp,
                            )
                            Text(
                                text = "Sunset: $sunsetString",
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}