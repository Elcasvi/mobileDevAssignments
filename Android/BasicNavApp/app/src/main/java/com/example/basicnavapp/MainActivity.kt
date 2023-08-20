package com.example.basicnavapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicnavapp.friends.FriendsOverViewActivity
import com.example.basicnavapp.hobbies.HobbiesOverViewActivity
import com.example.basicnavapp.ui.theme.BasicNavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainMenu()
                }
            }
        }
    }



    @Composable
    fun MainMenu()
    {
        Column(modifier = Modifier.fillMaxWidth().padding(25.dp),verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally ) {
            FriendsOverViewButton()
            HobbiesOverViewButton()
        }
    }
    @Composable
    fun FriendsOverViewButton() {
        Button(onClick = {
            val navigate=Intent(this,FriendsOverViewActivity::class.java)
            startActivity(navigate)
        }) {
            Text(text = "Friends overview")
        }
    }
    @Composable
    fun HobbiesOverViewButton()
    {
        Button(onClick = {
            val navigate=Intent(this,HobbiesOverViewActivity::class.java)
            startActivity(navigate)
        }) {
            Text(text = "Hobbies overview")
        }
    }

}

