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
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('Name: ${animal.name}'),
            Text('Species: ${animal.species}'),
            Text('Breed: ${animal.breed}'),
            Text('Age: ${animal.age} years'),
            // Add more details as needed
          ],
        ),
      ),
    );
  }
}
