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
          MaterialPageRoute(builder: (context) => Menu()),
        );
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
            TextField(
              controller: nameController,
              decoration: InputDecoration(
                labelText: 'Name',
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10.0),
                ),
              ),
            ),
            SizedBox(height: 10),
            TextField(
              controller: speciesController,
              decoration: InputDecoration(
                labelText: 'Species',
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10.0),
                ),
              ),
            ),
            SizedBox(height: 10),
            TextField(
              controller: breedController,
              decoration: InputDecoration(
                labelText: 'Breed',
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10.0),
                ),
              ),
            ),
            SizedBox(height: 10),
            TextField(
              controller: ageController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(
                labelText: 'Age',
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10.0),
                ),
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                addAnimalToFirestore();
              },
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.blue),
                padding: MaterialStateProperty.all(EdgeInsets.symmetric(horizontal: 20.0, vertical: 12.0)),
                shape: MaterialStateProperty.all(RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10.0),
                )),
              ),
              child: Text(
                'Add Animal',
                style: TextStyle(
                  fontSize: 18.0,
                  color: Colors.white,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

}
