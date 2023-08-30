package com.example.thevetapp

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import androidx.compose.ui.Modifier
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuActivity : ComponentActivity() {

    data class AnimalInfo(
        var animalList:ArrayList<AnimalItem> = arrayListOf()
    )
    data class AnimalItem(
        var race: String = "",
        var name: String = "",
        var age: Int = 0,
        var weight: String = ""
    )

    private val db = Firebase.firestore
    lateinit var animalInfo:AnimalInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ListOfAnimals()
                }
            }

            }
        //Get
        db.collection("animals")
            .get()
            .addOnSuccessListener { document  ->
                try {
                    if (document != null) {
                        //animalInfo = document.toObject(AnimalInfo::class.java) ?: AnimalInfo()
                        Toast.makeText(
                            this@MenuActivity,
                            "DocumentSnapshot read successfully!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MenuActivity,
                            "No such document!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (ex: Exception) {
                    ex.message?.let { Log.e(TAG, it) }
                }


            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }


    @Composable
    fun ListOfAnimals() {

        Column {
            animalInfo.animalList.forEach { animal ->
                AnimalRow(animal)
            }
        }
    }

    @Composable
    fun AnimalRow(animal: MenuActivity.AnimalItem) {
        Text(animal.name)
    }

    @Composable
    fun FlexCols()
    {
        Row(horizontalArrangement = Arrangement.Center) {
            Column {


            }
        }
    }

}




