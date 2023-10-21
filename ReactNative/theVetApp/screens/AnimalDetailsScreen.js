import {SafeAreaView, Text} from "react-native";
import {useRoute} from "@react-navigation/native";

export default function AnimalDetailsScreen()
{
    const route=useRoute();
    const{params}=route;
    const animal=params.animal;
    return(
        <SafeAreaView style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
            <Text style={{ fontSize: 24, fontWeight: 'bold', marginBottom: 20 }}>Animal Detail Screen</Text>
            <Text style={{ fontSize: 18 }}>Name: {animal.name}</Text>
            <Text style={{ fontSize: 18 }}>Specie: {animal.specie}</Text>
            <Text style={{ fontSize: 18 }}>Breed: {animal.breed}</Text>
            <Text style={{ fontSize: 18 }}>Age: {animal.age}</Text>
        </SafeAreaView>

    )
}