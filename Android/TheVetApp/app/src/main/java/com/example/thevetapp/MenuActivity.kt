package com.example.thevetapp

import android.content.ContentValues
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animalList:MutableList<AnimalItem> = arrayListOf()
        val animalListDummy = listOf(
            AnimalItem("Dog", "Buddy", "3 years", "15 kg"),
            AnimalItem("Cat", "Whiskers", "2 years", "5 kg"),
            AnimalItem("Elephant", "Dumbo", "10 years", "5000 kg")
        )

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
                for(animal in animalList)
                {
                    Log.e("Inside MenuActivity",animal.name)
                }
            }

        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //ListOfAnimals(animalListDummy)
                    if(animalList.size==0)
                        Toast.makeText(this,"Empty list",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this,"Populated list size: ${animalList.size}",Toast.LENGTH_LONG).show()
                    MyColumnWithList(animalList)
                }
            }
        }
    }

    @Composable
    fun AnimalRow(animal: AnimalItem) {
        Row {
            Text(text = animal.name)
            Button(onClick = {
                val intent= Intent(this@MenuActivity,DetailActivity::class.java)
                intent.putExtra("name",animal.name)
                startActivity(intent)
            }) {
                Text(text = "Edit")
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







