package com.example.thevetapp
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thevetapp.ui.theme.TheVetAppTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuActivity : ComponentActivity() {
    data class AnimalItem(
        var id:String="",
        var specie: String = "",
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
                    Navigation()
                }
            }
        }
    }


    @Composable
    private fun fetchListFromFireBase(): MutableState<List<AnimalItem>> {
         val animalList = remember { mutableStateOf<List<AnimalItem>>(emptyList()) }
        val tempAnimalList = mutableListOf<AnimalItem>()
        //Get
        db.collection("animals")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val animalItem = AnimalItem()
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    animalItem.id=document.id
                    animalItem.specie = document.data.get("specie").toString()
                    animalItem.name = document.data.get("name").toString()
                    animalItem.age = document.data.get("age").toString()
                    animalItem.weight = document.data.get("weight").toString()
                    tempAnimalList.add(animalItem)
                }
                 animalList.value=tempAnimalList
            }
        return animalList
    }

    @Composable
    fun AnimalItemCard(animal: AnimalItem,navController: NavHostController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Id: ${animal.id}"
                )
                Text(
                    text = "Specie: ${animal.specie}",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(text = "Name: ${animal.name}")
                Text(text = "Age: ${animal.age}")
                Text(text = "Weight: ${animal.weight}")

                Button(onClick = {
                    navController.navigate("editAnimalInterface/${animal}")
                }) {
                    Text(text = "Edit")
                }
            }
        }
    }

    @Composable
    fun AnimalList(list: MutableState<List<AnimalItem>>, navController: NavHostController) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(list.value) { item ->
                AnimalItemCard(animal = item,navController)
            }
        }
    }



    @Composable
    fun Navigation() {
        // this will be the main manager for our views
        // first we need to declare a controller
        // the controller is the object in charge of changing the views
        // and data exchange
        //Get
        val animalList=fetchListFromFireBase()
        val navController = rememberNavController()

        // a host is a structure in which several interfaces live
        NavHost(
            navController = navController,
            startDestination = "mainMenu"
        ) {
            // within the navhost we are going to declare several composables to navigate
            // using the composable macro
            composable("mainMenu") {
                MainMenu(
                    animalList,
                    navController,
                    addAnimalInterfaceLogic = {
                        navController.navigate("addAnimalInterface")
                    },
                )
            }

            composable("editAnimalInterface/{animal}",
                arguments = listOf(
                    navArgument("animal") { type = NavType.StringType}
                )
            ) {backStackEntry->
                EditAnimalInterface(
                    goBack = {
                        navController.popBackStack()
                    },
                    animal= backStackEntry.arguments?.getString("animal")
                )
            }

            composable("addAnimalInterface") {
                AddAnimalInterface(
                    goBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }


    @Composable
    fun MainMenu(
        animalList: MutableState<List<AnimalItem>>,
        navController: NavHostController,
        addAnimalInterfaceLogic: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = addAnimalInterfaceLogic
            ) {
                Text("Add new animal")
            }
            AnimalList(list = animalList,navController)
            }
        }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditAnimalInterface(
        goBack: () -> Unit,
        animal: String?
    ) {
        val db = Firebase.firestore
        val idRegex=Regex("id=([^,]+)")
        val specieRegex = Regex("specie=([^,]+)")
        val nameRegex = Regex("name=([^,]+)")
        val ageRegex = Regex("age=([^,]+)")
        val weightRegex = Regex("weight=([^\\)]+)")

        val idMatch = idRegex.find(animal.toString())
        val raceMatch = specieRegex.find(animal.toString())
        val nameMatch = nameRegex.find(animal.toString())
        val ageMatch = ageRegex.find(animal.toString())
        val weightMatch = weightRegex.find(animal.toString())

        val id= idMatch?.groupValues?.get(1)?:""

        var specie by remember{ mutableStateOf(raceMatch?.groupValues?.get(1) ?: "") }
        var name by remember{ mutableStateOf( nameMatch?.groupValues?.get(1) ?: "")}
        var age by remember{ mutableStateOf( ageMatch?.groupValues?.get(1) ?: "")}
        var weight by remember{ mutableStateOf( weightMatch?.groupValues?.get(1) ?: "")}



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "id:$id")
            TextField(
                value = specie,
                onValueChange = { specie = it },
                placeholder = { Text("Race") }
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name") }
            )
            TextField(
                value = age,
                onValueChange = { age = it },
                placeholder = { Text("Age") }
            )

            TextField(
                value = weight,
                onValueChange = { weight = it },
                placeholder = { Text("Weight") }
            )

            Button(onClick = {
                // Create a new animal with a name, age and weight
                try {
                    val documentReference = db.collection("animals").document(id)

                    // Use FieldValue to only update specific fields you want to change
                    val animal = mapOf(
                        "race" to specie,
                        "name" to name,
                        "age" to age,
                        "weight" to weight
                    )

                    // Update the document
                    documentReference.update(animal)
                    val navigate = Intent(this@MenuActivity,MenuActivity::class.java)
                    startActivity(navigate)
                    finish()
                } catch (e: Exception) {
                    // Handle any exceptions here
                }

            })
            {
                Text(text = "Update")
            }
            Button(
                onClick = goBack
            ) {
                Text("go back")
            }
        }

    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddAnimalInterface(
        goBack: () -> Unit
    ) {
        val db = Firebase.firestore


        var specie by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = specie,
                onValueChange = { specie = it },
                placeholder = { Text("race") }
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("name") }
            )
            TextField(
                value = age,
                onValueChange = { age = it },
                placeholder = { Text("age") }
            )

            TextField(
                value = weight,
                onValueChange = { weight = it },
                placeholder = { Text("weight") }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }



        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                // Create a new animal with a name, age and weight
                val animal = hashMapOf(
                    "specie" to specie,
                    "name" to name,
                    "age" to age,
                    "weight" to weight
                )

                // Add a new document with a generated ID
                db.collection("animals")
                    .add(animal)
                    .addOnSuccessListener { documentReference ->
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        val navigate = Intent(this@MenuActivity,MenuActivity::class.java)
                        startActivity(navigate)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }

            })
            {
                Text(text = "Register")
            }
            Button(
                onClick = goBack
            ) {
                Text("go back")
            }
        }
    }

}
