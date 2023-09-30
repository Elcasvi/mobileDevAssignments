import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:the_vet_app/Register.dart';

import 'firebase_options.dart';

Future<void>main() async{
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const LoginPage(title: 'The vet app'),
    );
  }
}


class LoginPage extends StatefulWidget {
  const LoginPage({super.key, required this.title});
  final String title;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController email = TextEditingController();
  TextEditingController password = TextEditingController();

  void handleLogin()
  {
    print("inside the login btn");
  }
  void handleRegister()
  {
    print("inside the register btn");
    Navigator.of(context).push(
      MaterialPageRoute(builder: (context)=>const Register()),
    );
  }
  @override
  void initState() {
    super.initState();
    // Initialize the text controllers here if needed.
    email.text = "";
    password.text = "";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              padding: const EdgeInsets.all(10.0),
              child: TextField(
                decoration: InputDecoration(
                  labelText: "email",
                  border: OutlineInputBorder( // Customize the border
                    borderRadius: BorderRadius.circular(10.0), // Border radius
                  ),
                  labelStyle: TextStyle(
                    color: Colors.blue, // Label text color
                  ),
                  hintText: "Enter your email", // Hint text
                  hintStyle: TextStyle(
                    color: Colors.grey, // Hint text color
                  ),
                  focusedBorder: OutlineInputBorder( // Border when focused
                    borderSide: BorderSide(color: Colors.blue),
                  ),
                ),
                controller: email,
              ),
            ),
            Container(
              padding: const EdgeInsets.all(10.0),
              child: TextField(
                decoration: InputDecoration(
                  labelText: "password",
                  border: OutlineInputBorder( // Customize the border
                    borderRadius: BorderRadius.circular(10.0), // Border radius
                  ),
                  labelStyle: TextStyle(
                    color: Colors.blue, // Label text color
                  ),
                  hintText: "Enter your password", // Hint text
                  hintStyle: TextStyle(
                    color: Colors.grey, // Hint text color
                  ),
                  focusedBorder: const OutlineInputBorder( // Border when focused
                    borderSide: BorderSide(color: Colors.blue),
                  ),
                ),
                controller: password,
              ),
            ),
            Container(
              padding: const EdgeInsets.all(10.0),
              child: TextButton(
                onPressed: () {
                  handleLogin();
                },
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.blue),
                  foregroundColor: MaterialStateProperty.all(Colors.white),
                  padding: MaterialStateProperty.all(
                    EdgeInsets.symmetric(horizontal: 20.0, vertical: 12.0),
                  ),
                  shape: MaterialStateProperty.all(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                child: const Text(
                  "Login",
                  style: TextStyle(
                    fontSize: 18.0,
                  ),
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.all(10.0),
              child: TextButton(
                onPressed: () {
                  // Add your login logic here
                  handleRegister();
                },
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.blue),
                  foregroundColor: MaterialStateProperty.all(Colors.white),
                  padding: MaterialStateProperty.all(
                    EdgeInsets.symmetric(horizontal: 20.0, vertical: 12.0),
                  ),
                  shape: MaterialStateProperty.all(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                  ),
                ),
                child: const Text(
                  "Register",
                  style: TextStyle(
                    fontSize: 18.0,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

}
