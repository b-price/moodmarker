package com.example.moodmarker.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.navigation.Routes
import com.example.moodmarker.ui.theme.components.LoginFields

@Composable
fun LoginPage(nav: NavHostController) {
    //TODO: Implement login w/credentials
    val (userName, setUserName) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (rememberMe, setRememberMe) = rememberSaveable { mutableStateOf(false) }

    val areFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()

//    val emojiList = listOf("😁", "😡", "🙁", "🙂", "😐")
//    val randomEmojiOne = emojiList.shuffled().take(1)[0]
//    val randomEmojiTwo = emojiList.shuffled().take(1)[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        /** Login Header Text **/
        Text(
            text = "Login",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )


        /** Username Login Field **/
        LoginFields(
            value = userName,
            onValueChange = setUserName,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))


        /** Password Login Field **/
        LoginFields(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(10.dp))


        /** Remember Me Checkbox **/
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = setRememberMe)
            Text("Remember Me")
        }
        Spacer(modifier = Modifier.height(10.dp))


//        /** Login and Signup Buttons **/
//        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
//            Button(onClick = { nav.navigate(Routes.MainPage.route) }, modifier = Modifier.padding(10.dp) ) {
//                Text("Login", fontSize = 5.em, modifier = Modifier.padding(5.dp))
//            }
//            Button(onClick = { nav.navigate(Routes.CreateAccount.route) }) {
//                Text("Signup", fontSize = 5.em, modifier = Modifier.padding(5.dp))
//            }
//        }

        /** Login Button **/
        Button(onClick = { nav.navigate(Routes.MainPage.route) }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
                Text("Login", fontSize = 5.em)
        }
        Spacer(modifier = Modifier.height(10.dp))


        /** Forgot Password Button **/
        TextButton(onClick = {}) {
            Text("Forgot Password?")
        }
        Spacer(modifier = Modifier.height(10.dp))


        /** Signup Button **/
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
            ) {
            Text("Don't have an account?")
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { nav.navigate(Routes.CreateAccount.route) }) {
                Text("Sign Up")
            }
        }






    }

}

