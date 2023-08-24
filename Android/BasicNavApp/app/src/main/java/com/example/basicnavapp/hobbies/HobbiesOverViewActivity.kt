package com.example.basicnavapp.hobbies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basicnavapp.MainActivity
import com.example.basicnavapp.hobbies.ui.theme.BasicNavAppTheme

class HobbiesOverViewActivity : ComponentActivity() {
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
            Text(text = "Quit")

        }

    }

    @Composable
    fun Carlos(){
        Button(onClick = {
            val navigate = Intent(this@HobbiesOverViewActivity, CarlosHobbiesAcivity::class.java)
            startActivity(navigate)
            Toast.makeText(this,"Carlos", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Carlos")

        }
    }

    @Composable
    fun Levi(){
        Button(onClick = {
            val navigate = Intent(this@HobbiesOverViewActivity, LevisHobbiesActivity::class.java)
            startActivity(navigate)
            Toast.makeText(this,"Levi", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Levi")

        }
    }

    @Composable
    fun Ronny(){
        Button(onClick = {
            val navigate = Intent(this@HobbiesOverViewActivity, RonnysHobbiesActivity::class.java)
            startActivity(navigate)
            Toast.makeText(this,"Ronny", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Ronny")

        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Who's Hobbies do you want to see?",
                modifier = modifier
            )

            Carlos()
            Levi()
            Ronny()



        }

        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            QuitButton()

        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        com.example.basicnavapp.friends.ui.theme.BasicNavAppTheme {
            Greeting("Android")
        }
    }
}

