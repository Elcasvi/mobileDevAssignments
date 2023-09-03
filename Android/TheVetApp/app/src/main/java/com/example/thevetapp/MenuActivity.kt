package com.example.thevetapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thevetapp.ui.theme.TheVetAppTheme
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
    private val animalList:MutableList<AnimalItem> = arrayListOf()

    val animalListDummy = listOf(
        AnimalItem("Dog", "Buddy", "3 years", "15 kg"),
        AnimalItem("Cat", "Whiskers", "2 years", "5 kg"),
        AnimalItem("Elephant", "Dumbo", "10 years", "5000 kg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchListFromFireBase()
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AnimalList(list = animalList)
                }
            }
        }
    }

    private fun fetchListFromFireBase() {
        //Get
        db.collection("animals")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val animalItem = AnimalItem()
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    animalItem.race=document.data.get("race").toString()
                    animalItem.name=document.data.get("name").toString()
                    animalItem.age=document.data.get("age").toString()
                    animalItem.weight=document.data.get("weight").toString()
                    animalList.add(animalItem)
                }
                setContent{
                    AnimalList(list = animalList)
                }
            }
    }
    @Composable
    fun AnimalItemCard(animal: AnimalItem) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Race: ${animal.race}",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(text = "Name: ${animal.name}")
                Text(text = "Age: ${animal.age}")
                Text(text = "Weight: ${animal.weight}")

                Button(onClick = {
                    val intent= Intent(this@MenuActivity,DetailActivity::class.java)
                    intent.putExtra("name",animal.name)
                    startActivity(intent)
                }) {
                    Text(text = "Edit")
                }

            }
        }
    }

    @Composable
    fun AnimalList(list: List<AnimalItem>) {
        LazyColumn (modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ){
            items(list) { item ->
                AnimalItemCard(animal = item)
            }
        }
    }





}







