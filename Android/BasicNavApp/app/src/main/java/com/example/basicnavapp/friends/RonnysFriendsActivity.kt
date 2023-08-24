package com.example.basicnavapp.friends

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicnavapp.R
import com.example.basicnavapp.friends.ui.theme.BasicNavAppTheme

class RonnysFriendsActivity : ComponentActivity() {
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
    fun QuitButton(){

        Button(onClick = {
            finish()
        }) {
            Text(text = "Back")

        }

    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Here is a recent picture with my friends ",

                modifier = modifier
            )
            Text(text = "Kinda miss them:(",
                modifier = modifier)

            Image(
                painter = painterResource(R.drawable.friendsronny),
                contentDescription = "Switzerland is GOAT",
                modifier = Modifier
                    .padding(15.dp)




            )

        }

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            QuitButton()

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

