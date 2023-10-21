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
        <SafeAreaView style={{ flex: 1, alignItems: 'center', padding: 20 }}>
            <Text style={{ fontSize: 24, fontWeight: 'bold', marginBottom: 20 }}>Login Screen</Text>
            <TextInput
                style={{
                    width: '80%',
                    marginBottom: 15,
                    padding: 10,
                    borderWidth: 1,
                    borderColor: '#ccc',
                    borderRadius: 5,
                }}
                placeholder="Email"
                onChangeText={setEmail}
            />
            <TextInput
                style={{
                    width: '80%',
                    marginBottom: 15,
                    padding: 10,
                    borderWidth: 1,
                    borderColor: '#ccc',
                    borderRadius: 5,
                }}
                secureTextEntry={true}
                placeholder="Password"
                onChangeText={setPassword}
            />
            <Button
                style={{
                    width: '80%',
                    marginVertical: 10,
                }}
                mode="contained"
                title="Login"
                onPress={handleLoginBtn}
            />
            <Button
                style={{
                    width: '80%',
                    marginVertical: 10,
                }}
                mode="outlined"
                title="Register"
                onPress={handleRegisterBtn}
            />
        </SafeAreaView>

    )
}