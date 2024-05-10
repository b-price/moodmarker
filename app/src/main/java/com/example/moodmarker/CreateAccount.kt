package com.example.moodmarker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.em
import com.example.moodmarker.ui.theme.components.LoginFields

@Composable
fun CreateAccount(nav: NavHostController) {


    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (userName, onUserNameChange) = rememberSaveable { mutableStateOf("") }

    var arePasswordsSame by remember { mutableStateOf(false) }
    val areFieldsEmpty = firstName.isNotEmpty() && password.isNotEmpty() && lastName.isNotEmpty() && confirmPassword.isNotEmpty() && email.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(arePasswordsSame) {
            Text("Passwords Are Not the Same", color = MaterialTheme.colorScheme.error)
        }

        /** Create Account Header Text **/
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )


//        /** Username Create Account Field **/
//        LoginFields(
//            value = firstName,
//            onValueChange = onFirstNameChange,
//            labelText = "First Name",
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(8.dp))


        /** First Name Create Account Field **/
        LoginFields(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Last Name Create Account Field **/
        LoginFields(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Email Create Account Field **/
        LoginFields(
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Password Create Account Field **/
        LoginFields(
            value = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(10.dp))


        /** Confirm Password Create Account Field **/
        LoginFields(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            labelText = "Confirm Password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(20.dp))


        /** Submit Button **/
        Button(onClick = {
            arePasswordsSame = password != confirmPassword
            if(!arePasswordsSame) {
                nav.navigate(Routes.MainPage.route)
            }
            }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
            Text("Submit", fontSize = 5.em)
        }
//        Spacer(modifier = Modifier.height(20.dp))


        /** Return to Login Page Button **/
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Text("Already Have an Account?")
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { nav.navigate(Routes.LoginPage.route) }) {
                Text("Login")
            }
        }
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 4.dp, end = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(text = "Name", modifier = Modifier.width(100.dp))
//            TextField(
//                value = name.value,
//                onValueChange = { name.value = it }
//            )
//        }
//
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 4.dp, end = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(text = "UserName", modifier = Modifier.width(100.dp))
//            TextField(
//                value = userName.value,
//                onValueChange = { userName.value = it }
//            )
//        }
//
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 4.dp, end = 4.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(text = "Password", modifier = Modifier.width(100.dp))
//            TextField(
//                value = password.value,
//                onValueChange = { password.value = it }
//            )
//        }
//
//        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
//            Button(onClick = { nav.navigate(Routes.MainPage.route) }, modifier = Modifier.padding(10.dp) ) {
//                Text("Submit", fontSize = 5.em, modifier = Modifier.padding(8.dp))
//            }
//            Button(onClick = { nav.navigate(Routes.LoginPage.route) }) {
//                Text("Cancel", fontSize = 5.em, modifier = Modifier.padding(8.dp))
//            }
//        }
//
//


    }





}