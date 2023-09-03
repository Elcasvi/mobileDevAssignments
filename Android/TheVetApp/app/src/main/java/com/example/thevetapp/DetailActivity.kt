package com.example.thevetapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailActivity : ComponentActivity() {
    data class AnimalItem(
        var race: String = "",
        var name: String = "",
        var age: String = "",
        var weight: String = ""
    )

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
                    myAnimal()
                }
            }
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
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

        })
        {
            Text(text = "Db post")
        }
    }
    
    @Composable
    fun myAnimal()
    {
        Text(text="")
    }

    @Composable
    fun Greeting2(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

