package com.example.basicnavapp.hobbies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.basicnavapp.hobbies.ui.theme.BasicNavAppTheme

class CarlosHobbiesAcivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListHobbies()
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
    fun ListHobbies(){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Here is an overview of my hobbies")
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                item {
                    ListRow(id = R.drawable.bookcarlos, text = "Reading")
                    ListRow(id = R.drawable.workout, text = "Working out")
                    ListRow(id = R.drawable.coockingcarlos, text = "Cooking")

                }
            }

        }
        Column(verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            QuitButton()

        }


    }

    @Composable
    fun ListRow(id : Int, text : String){
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            //Text(text = "Here is an overview of my hobbies")
            Text(text = text,
                modifier = Modifier
                    .padding(all=15.dp)
            )
            Image(painter = painterResource(id = id), contentDescription = "A row" )
        }

    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BasicNavAppTheme {
           ListHobbies()
        }

    }
}

