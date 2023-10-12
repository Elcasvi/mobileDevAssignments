import {SafeAreaView, Text} from "react-native";
import {useRoute} from "@react-navigation/native";

export default function AnimalDetailsScreen()
{
    const route=useRoute();
    const{params}=route;
    const animal=params.animal;
    return(
        <SafeAreaView>
            <Text>Animal Detail Screen</Text>
            <Text>{animal.name}</Text>
        </SafeAreaView>
    )
}