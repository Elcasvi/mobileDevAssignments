package com.example.thevetapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MenuActivity : ComponentActivity() {
    data class AnimalItem(
        var race: String = "",
        var name: String = "",
        var age: String = "",
        var weight: String = ""
    )

    private val db = Firebase.firestore

    val animalList:MutableList<AnimalItem> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListOfAnimals()
                    //MyColumnWithList(animalList)
                }
            }
        }
        //Get
        db.collection("animals")
            .get()
            .addOnSuccessListener { result ->
            for (document in result) {
                val animalItem = AnimalItem()
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                val animalRace:String = document.data.get("race").toString()
                val animalName:String = document.data.get("name").toString()
                val animalAge:String = document.data.get("age").toString()
                val animalWeight:String = document.data.get("weight").toString()

                animalItem.race=animalRace
                animalItem.name=animalName
                animalItem.age=animalAge
                animalItem.weight=animalWeight
                animalList.add(animalItem)
            }
                /*
                for (animal in animalList){
                    Log.d("Tag","race: ${animal.race}")
                    Log.d("Tag","name: ${animal.name}")
                    Log.d("Tag","age: ${animal.age}")
                    Log.d("Tag","weight: ${animal.weight}")
                }

                 */

        }
    }

    @Composable
    fun AnimalRow(animal: MenuActivity.AnimalItem) {
        Text(text = animal.name)
    }


    @Composable
    fun ListOfAnimals() {
        Column {
            Button(onClick = {
            for (animal in animalList){
                Log.d("Tag","race: ${animal.race}")
                Log.d("Tag","name: ${animal.name}")
                Log.d("Tag","age: ${animal.age}")
                Log.d("Tag","weight: ${animal.weight}")}
            }) {

            }
        }
    }

    @Composable
    fun MyColumnWithList(items: List<AnimalItem>) {
        LazyColumn {
            items(items) { item ->
                AnimalRow(animal = item)
            }
        }
    }


}







