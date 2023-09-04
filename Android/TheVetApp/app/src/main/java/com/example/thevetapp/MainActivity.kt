package com.example.thevetapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.*


class MainActivity : ComponentActivity() {
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleLoginForm()
                }
            }
        }
        auth= Firebase.auth
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleLoginForm(
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            RegisterUser(email,password)
            LoginUser(email,password)
        }
    }

    @Composable
    fun RegisterUser(email:String,password:String) {
            Button(onClick = {
                auth?.createUserWithEmailAndPassword(email,password)
                    ?.addOnCompleteListener{task->
                        if(task.isSuccessful) {
                            val navigate = Intent(this, MenuActivity::class.java)
                            startActivity(navigate)
                        }
                        else
                            Toast.makeText(this@MainActivity,"Not Succesful: ${task.exception}",Toast.LENGTH_SHORT).show()
                        Log.d("Tag","Not Succesful: ${task.exception}")
                    }
            })
            {
                    Text(text = "Register user")
            }
    }
    @Composable
    fun LoginUser(email:String,password:String)
    {
        Button(onClick = {
            auth?.signInWithEmailAndPassword(email,password)
                ?.addOnCompleteListener{task->
                    if(task.isSuccessful) {
                        val navigate = Intent(this, MenuActivity::class.java)
                        startActivity(navigate)
                    }
                    else
                        Toast.makeText(this@MainActivity,"Not Succesful: ${task.exception}",Toast.LENGTH_LONG).show()
                }
        }) {
            Text(text = "Log in")
        }
    }


    @Composable
    fun ToMenuAct()
    {
        Button(onClick = {
            val navigate= Intent(this,MenuActivity::class.java)
            startActivity(navigate)
        }) {
            Text(text = "Go to menu")
        }

    }

}

