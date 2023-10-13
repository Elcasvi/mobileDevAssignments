import React, { useEffect, useState } from "react";
import { Button, FlatList, Text, View } from "react-native";
import Animal from "../components/Animal";
import { useNavigation } from "@react-navigation/native";
import { app } from "../firebase-config";
import { getDatabase, ref, get } from "firebase/database";

export default function MenuScreen() {
    const db = getDatabase(app);
    const { navigate } = useNavigation();
    const [animals, setAnimals] = useState([]);

    const goTo = () => {
        navigate("CreateAnimalScreen");
    };

    useEffect(() => {
        // Fetch data from Firebase Realtime Database
        const animalsRef = ref(db, "animals"); // Change "animals" to your database path
        get(animalsRef).then((snapshot) => {
            if (snapshot.exists()) {
                setAnimals(Object.values(snapshot.val()));
            } else {
                console.log("No data available");
            }
        });
    }, []);

    return (
        <View>
            <Text>Menu Screen</Text>
            <Button title={"Add a new animal"} onPress={goTo} />
            <FlatList
                style={{ width: "100%", marginTop: 6 }}
                data={animals}
                renderItem={({ item }) => <Animal animal={item} />}
                keyExtractor={(item) => item.id.toString()} // Make sure to convert the key to a string
            />
        </View>
    );
}
