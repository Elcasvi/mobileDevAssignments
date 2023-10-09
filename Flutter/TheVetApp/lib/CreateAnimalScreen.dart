import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:the_vet_app/MenuScreen.dart';
import 'package:the_vet_app/main.dart';

import 'DetailScreen.dart';

class CreateAnimalScreen extends StatelessWidget {
  const CreateAnimalScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final TextEditingController nameController = TextEditingController();
    final TextEditingController speciesController = TextEditingController();
    final TextEditingController breedController = TextEditingController();
    final TextEditingController ageController = TextEditingController();

    Future<void> addAnimalToFirestore() async {
      final FirebaseFirestore _firestore = FirebaseFirestore.instance;
      try {
        await _firestore.collection('animals').add({
          'name': nameController.text,
          'species': speciesController.text,
          'breed': breedController.text,
          'age': int.tryParse(ageController.text) ?? 0,
        });

        Navigator.of(context).push(
            MaterialPageRoute(
                builder: (context) => Menu()));
        // Optionally, you can navigate back to the previous screen or perform other actions after adding the animal.
      } catch (e) {
        print('Error adding animal to Firestore: $e');
      }
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Create Animal'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            TextFormField(
              controller: nameController,
              decoration: InputDecoration(labelText: 'Name'),
            ),
            TextFormField(
              controller: speciesController,
              decoration: InputDecoration(labelText: 'Species'),
            ),
            TextFormField(
              controller: breedController,
              decoration: InputDecoration(labelText: 'Breed'),
            ),
            TextFormField(
              controller: ageController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(labelText: 'Age'),
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: () {
                addAnimalToFirestore();
              },
              child: Text('Add Animal'),
            ),
          ],
        ),
      ),
    );
  }
}
