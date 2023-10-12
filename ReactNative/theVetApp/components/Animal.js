import {Pressable} from "react-native";
import { Card, Text } from "react-native-paper";
import {useNavigation} from "@react-navigation/native";

export default function Animal({animal}){
    const {navigate}=useNavigation();
    return(
        <Pressable onPress={()=>{navigate("AnimalDetailScreen",{ animal });}}>
            <Card style={{ marginHorizontal: 8, marginVertical: 2 }} mode="outlined">
                <Card.Title titleStyle={{ fontWeight: "500" }} titleVariant="headlineMedium" title={animal.name} />
                <Card.Content>
                    <Text variant="bodyMedium">{animal.specie}</Text>
                    <Text variant="bodyMedium">{animal.breed}</Text>
                    <Text variant="bodyMedium">{animal.age}</Text>
                </Card.Content>
            </Card>
        </Pressable>
    );
};