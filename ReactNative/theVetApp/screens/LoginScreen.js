import {Alert, Button, SafeAreaView, Text, TextInput} from "react-native";
import {useState} from "react";
import {useNavigation} from "@react-navigation/native";
import {getAuth,createUserWithEmailAndPassword,signInWithEmailAndPassword}from 'firebase/auth';
import {app} from "../firebase-config";

export default function LoginScreen()
{
    const auth=getAuth(app)

    const[password,setPassword]=useState("")
    const[email,setEmail]=useState("")
    const {navigate}=useNavigation();
    const handleLoginBtn=()=>
    {
        signInWithEmailAndPassword(auth,email,password)
            .then(()=>
            {
                console.log("Sign in!")
                navigate("MenuScreen");
            })
            .catch(error=>{
                Alert.alert("",error)
                console.log(error)
            })

    }
    const handleRegisterBtn=()=>
    {
        createUserWithEmailAndPassword(auth,email,password)
            .then(()=>
            {
                console.log("Account created")
                navigate("MenuScreen");
            })
            .catch(error=>{
                Alert.alert("",error)
                console.log(error)
            })

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