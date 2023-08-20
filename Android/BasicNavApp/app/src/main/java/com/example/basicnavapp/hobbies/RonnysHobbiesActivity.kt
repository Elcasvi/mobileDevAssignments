package com.example.basicnavapp.hobbies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.basicnavapp.R
import com.example.basicnavapp.hobbies.ui.theme.BasicNavAppTheme

class RonnysHobbiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "My hobbies are:",
                modifier = modifier
            )
            Text(
                text = "- Reading",

                modifier = modifier
            )
            Text(

                text = "- Chess",

                modifier = modifier
            )

            Text(
                text = "- Working out",

                modifier = modifier
            )

            Text(

                text = "- Combat Sports",
                modifier = modifier
            )

            Image(
                painter = painterResource(R.drawable.schweiz),
                contentDescription = "Switzerland is GOAT"
            )

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BasicNavAppTheme {
            Greeting("Android")
        }

    }
}

