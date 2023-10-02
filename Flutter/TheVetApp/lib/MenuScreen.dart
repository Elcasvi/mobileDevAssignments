import 'package:flutter/material.dart';

import 'CreateAnimalScreen.dart';
import 'DetailScreen.dart';// Import the Fluttertoast package
class Menu extends StatelessWidget{
  const Menu({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const AnimalMenu(title: 'The vet app'),
    );
  }
}

class AnimalMenu extends StatefulWidget{
  const AnimalMenu({super.key,required this.title});
  final String title;
  @override
  State<AnimalMenu> createState()=>_AnimalMenu();
}

class Animal {
  Animal({required this.name, required this.species, required this.age});
  String name;
  String species;
  int age;
}

class _AnimalMenu extends State<AnimalMenu> {
  // Define a list of Animal objects
  final List<Animal> animals = [
    Animal(name: 'Buddy', species: 'Dog', age: 3),
    Animal(name: 'Whiskers', species: 'Cat', age: 2),
    Animal(name: 'Dumbo', species: 'Elephant', age: 10),
    // Add more Animal objects as needed
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView.builder(
        itemCount: animals.length,
        itemBuilder: (BuildContext context, int index) {
          return GestureDetector(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => DetailScreen(animal: animals[index]),
                ),
              );
            },
            child: ListTile(
              title: Text(animals[index].name),
              subtitle: Text('Species: ${animals[index].species}, Age: ${animals[index].age} years'),
              // Add any other properties or actions you want for each list item
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Navigate to the create animal screen when the button is pressed
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CreateAnimalScreen(),
            ),
          );
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}