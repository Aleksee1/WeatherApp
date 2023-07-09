package com.example.weatherapp

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreenView() {
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
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
        {
            items(forecastItems) { indexDayForecast ->
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                )
                {
                    Image (
                        painter = painterResource(R.drawable.clear_sky_day),
                        contentDescription = stringResource(R.string.picture),
                        Modifier.size(60.dp),
                    )
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 10.dp),
                    )
                    {
                        Text (
                            text = SimpleDateFormat("MMM d").format(Date(indexDayForecast.date)),
                            fontSize = 16.sp,
                        )
                    }
                    Column (
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .width(130.dp)
                            .padding(start = 10.dp),
                        )
                    {
                        Text (
                            text = "Temp: " + indexDayForecast.temp.day.toInt() + "°",
                            fontSize = 12.sp,
                        )
                        Row {
                            Text (
                                text = "High: " + indexDayForecast.temp.max.toInt() + "°",
                                modifier = Modifier.padding(end = 5.dp),
                                fontSize = 12.sp,
                            )
                            Text (
                                text = "Low: " + indexDayForecast.temp.min.toInt() + "°",
                                fontSize = 12.sp,
                            )
                        }
                    }
                    Column (
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(start = 10.dp),
                    )
                    {
                        Text (
                            text = "Sunrise: " + SimpleDateFormat("hh:mma").format(Date(indexDayForecast.sunrise)),
                            fontSize = 12.sp,
                        )
                        Text (
                            text = "Sunset: " + SimpleDateFormat("hh:mma").format(Date(indexDayForecast.sunset)),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}