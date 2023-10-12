import {Button, SafeAreaView, Text, TextInput} from "react-native";
import {useState} from "react";
import {useNavigation} from "@react-navigation/native";

export default function LoginScreen()
{
    const[password,setPassword]=useState("")
    const[email,setEmail]=useState("")
    const {navigate}=useNavigation();
    const handleLoginBtn=()=>
    {
        navigate("MenuScreen");
    }
    const handleRegisterBtn=()=>
    {
        navigate("MenuScreen");
    }
    return(
        <SafeAreaView style={{ flex: 1, alignItems: 'center', padding: 10 }}>
            <Text>Login Screen</Text>
            <TextInput placeholder={"Email"} onChangeText={setEmail}/>
            <TextInput secureTextEntry={true} placeholder={"Password"} onChangeText={setPassword}/>
            <Button style={{width: "50%", margin: 10 }} mode="contained" title={"Login"} onPress={handleLoginBtn}/>
            <Button style={{width: "50%" }} mode="outlined" title={"Register"} onPress={handleRegisterBtn}/>
        </SafeAreaView>
    )
}