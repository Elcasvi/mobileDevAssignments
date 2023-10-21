import 'package:flutter/material.dart';

import 'MenuScreen.dart';

class DetailScreen extends StatelessWidget {
  final Animal animal;

  DetailScreen({super.key, required this.animal});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Animal Details'),
      ),
      body: Center(
        child: Container(
          width: 300, // Set the desired width
          height: 200, // Set the desired height
          child: Card(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    'Name: ${animal.name}',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 8),
                  Text('Species: ${animal.species}'),
                  Text('Breed: ${animal.breed}'),
                  Text('Age: ${animal.age} years'),
                  // Add more details as needed
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }



}
