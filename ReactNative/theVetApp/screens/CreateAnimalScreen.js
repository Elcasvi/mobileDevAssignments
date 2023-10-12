import {Button, SafeAreaView, Text, TextInput} from "react-native";
import {useState} from "react";
import {useNavigation} from "@react-navigation/native";

export default function CreateAnimalScreen()
{
    const navigation=useNavigation()
    const[name,setName]=useState("")
    const[specie,setSpecie]=useState("")
    const[breed,setBreed]=useState("")
    const[age,setAge]=useState(0)
    const handleAddAnimalBtn=()=>{
        navigation.goBack()
    }
    return(
        <SafeAreaView style={{ flex: 1, alignItems: 'center', padding: 10 }}>
            <Text>Create Animal</Text>
            <TextInput placeholder={"Name"} onChangeText={setName}/>
            <TextInput placeholder={"Specie"} onChangeText={setSpecie}/>
            <TextInput placeholder={"Breed"} onChangeText={setBreed}/>
            <TextInput placeholder={"Age"} onChangeText={setAge}/>
            <Button style={{width: "50%", margin: 10 }} mode="contained" title={"Add"} onPress={handleAddAnimalBtn}/>
        </SafeAreaView>
    );
}