import React, { useState } from "react";
import { Button, SafeAreaView, Text, TextInput } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { app } from "../firebase-config";
import { getDatabase, ref, push, set } from "firebase/database";

export default function CreateAnimalScreen() {
    const navigation = useNavigation();
    const [name, setName] = useState("");
    const [specie, setSpecie] = useState("");
    const [breed, setBreed] = useState("");
    const [age, setAge] = useState(0);

    const handleAddAnimalBtn = () => {
        const db = getDatabase(app);

        // Create a new reference in the "animals" node and push the data
        const animalsRef = ref(db, "animals");
        const newAnimalRef = push(animalsRef);
        const newAnimalKey = newAnimalRef.key;

        // Define the animal object to be added to the database
        const newAnimal = {
            id: newAnimalKey,
            name: name,
            specie: specie,
            breed: breed,
            age: age,
        };

        // Set the data at the new reference
        set(ref(db, `animals/${newAnimalKey}`), newAnimal)
            .then(() => {
                // Successfully added the animal, navigate back to the previous screen
                navigation.goBack();
            })
            .catch((error) => {
                console.error("Error adding animal: ", error);
            });
    };

    return (
        <SafeAreaView style={{ flex: 1, alignItems: 'center', padding: 10 }}>
            <Text>Create Animal</Text>
            <TextInput placeholder={"Name"} onChangeText={setName} />
            <TextInput placeholder={"Specie"} onChangeText={setSpecie} />
            <TextInput placeholder={"Breed"} onChangeText={setBreed} />
            <TextInput placeholder={"Age"} onChangeText={setAge} />
            <Button
                style={{ width: "50%", margin: 10 }}
                mode="contained"
                title={"Add"}
                onPress={handleAddAnimalBtn}
            />
        </SafeAreaView>
    );
}
