import {Button, FlatList, Text, View} from "react-native";
import Animal from "../components/Animal";
import {useNavigation} from "@react-navigation/native";

export default function MenuScreen()
{
    const {navigate}=useNavigation();
    const animals=[
        {id:1,name:"gromy",specie:"cat",breed:"angora",age:2},
        {id:2,name:"buddy",sepecie:"dog",breed:"pitbull",age:3}
    ]
    const goTo=()=>{
        navigate("CreateAnimalScreen")
    }
    return(
        <View>
            <Text>Menu Screen</Text>
            <Button title={"Add a new animal"} onPress={goTo}/>
            <FlatList
                style={{ width: "100%", marginTop: 6 }}
                data={animals}
                renderItem={({item}) => <Animal animal={item}/>}
                keyExtractor={item => item.id}
            />
        </View>
    );
}