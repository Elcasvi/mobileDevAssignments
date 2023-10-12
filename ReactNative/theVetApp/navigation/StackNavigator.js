import {createNativeStackNavigator} from "@react-navigation/native-stack";
import LoginScreen from "../screens/LoginScreen";
import MenuScreen from "../screens/MenuScreen";
import AnimalDetailsScreen from "../screens/AnimalDetailsScreen";
import CreateAnimalScreen from "../screens/CreateAnimalScreen";

const Stack = createNativeStackNavigator();
function StackNavigation()
{

    return(
        <Stack.Navigator>
            <Stack.Screen name="LoginScreen" component={LoginScreen}
                                    options={{headerTitle:"Login"}}/>
            <Stack.Screen name="MenuScreen" component={MenuScreen}
                          options={{headerTitle:"Menu"}}/>
            <Stack.Screen name="AnimalDetailScreen" component={AnimalDetailsScreen}
                          options={{headerTitle:"Details"}}/>
            <Stack.Screen name="CreateAnimalScreen" component={CreateAnimalScreen}
                          options={{headerTitle:"Create"}}/>
        </Stack.Navigator>
    )
}

export default function StackNavigator() {
    return (
        <StackNavigation/>
    );
}