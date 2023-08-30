package com.example.thevetapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var auth:FirebaseAuth
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlexCols()
                }
            }
        }
        auth= Firebase.auth
    }

    @Composable
    fun RegisterUser() {

            Button(onClick = {
                auth?.createUserWithEmailAndPassword("carlos@gmail.com","Developer09+")
                    ?.addOnCompleteListener{task->
                        if(task.isSuccessful)
                            Toast.makeText(this@MainActivity,"Succesful",Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(this@MainActivity,"Not Succesful: ${task.exception}",Toast.LENGTH_LONG).show()

                    }
            })
            {
                    Text(text = "Register user")
            }
    }

    @Composable
    fun PostDb()
    {
        Button(onClick = {
            // Create a new animal with a name, age and weight
            val animal = hashMapOf(
                "race" to "Cat",
                "name" to "Gromy",
                "age" to 1,
                "weight" to 3
            )

            // Add a new document with a generated ID
            db.collection("animals")
                .add(animal)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        })
        {
            Text(text = "Db post")
        }
    }
    @Composable
    fun GetDb()
    {
        Button(onClick = {
            //Get
            db.collection("animals")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }) {
            Text(text = "Db Get")
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
    @Composable
    fun FlexCols()
    {
        Row(horizontalArrangement = Arrangement.Center) {
            Column {
                RegisterUser()
                PostDb()
                GetDb()
                ToMenuAct()
            }
        }
    }
}
