import 'package:flutter/material.dart';
import 'CreateAnimalScreen.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'DetailScreen.dart';


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
  Animal({required this.name, required this.species,required this.breed, required this.age});
  String name;
  String species;
  String breed;
  int age;
}

class _AnimalMenu extends State<AnimalMenu> {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Stream<QuerySnapshot> getAnimalsStream() {
    return _firestore.collection('animals').snapshots();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: StreamBuilder<QuerySnapshot>(
        stream: getAnimalsStream(),
        builder: (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          }

          if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          }

          final List<Animal> animals = snapshot.data!.docs.map((DocumentSnapshot doc) {
            final data = doc.data() as Map<String, dynamic>;
            return Animal(
              name: data['name'],
              species: data['species'],
              breed: data['breed'],
              age: data['age'],
            );
          }).toList();

          return ListView.builder(
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
                child: Container(
                  color: Colors.lightBlue, // Add your desired background color here
                  padding: EdgeInsets.all(10),
                  child: ListTile(
                    title: Text(
                      animals[index].name,
                      style: TextStyle(color: Colors.white),
                    ),
                    subtitle: Text(
                      'Species: ${animals[index].species}, Breed: ${animals[index].breed}, Age: ${animals[index].age} years',
                      style: TextStyle(color: Colors.white),
                    ),
                    // Add any other properties or actions you want for each list item
                  ),
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const CreateAnimalScreen(),
            ),
          );
        },
        child: const Icon(Icons.add),
      ),
    );
  }

}