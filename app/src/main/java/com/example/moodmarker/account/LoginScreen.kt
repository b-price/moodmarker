package com.example.moodmarker.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.MoodMarker
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.navigation.Routes
import com.example.moodmarker.ui.theme.components.LoginFields


/** Login screen
 * Current version doesn't verify user with database, just with current state
 */
@Composable
fun LoginPage(
    users: List<User>,
    nav: NavHostController,
    enteredUsername: MutableState<String>,
    enteredUser: User,
    //getLoginInfo: KFunction2<String, String, Unit>
) {
    val (userName, setUserName) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var correctPassword by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }

    /** Checks for allowing login navigation based on whether all fields are filled or not **/
    val areFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /** Shows a red notification at the top of the screen if the entered password does not match the one stored **/
        AnimatedVisibility(correctPassword) {
            Text("That password is incorrect", color = MaterialTheme.colorScheme.error)
        }

        /** Shows a red notification at the top of the screen if the entered username does not match the one stored **/
        AnimatedVisibility(userNameExists) {
            Text("That username does not exist", color = MaterialTheme.colorScheme.error)
        }

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
            trailingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))


        /** Password Login Field with password visual transformation keyboard **/
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = setPassword,
            singleLine = true,
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(50),
            visualTransformation = if (passwordVisibility) { VisualTransformation.None } else { PasswordVisualTransformation() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                /** checks whether the user has clicked the show password characters or chosen to show . in their place **/
                if (passwordVisibility) {
                    IconButton(onClick = { passwordVisibility = false }) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = "") }
                } else {
                    IconButton(onClick = { passwordVisibility = true }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = "") }
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))


        //TODO Set up Remember Me Checkbox
        /** Remember Me Checkbox to remember the user's username is checked**/
/**
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = setRememberMe)
            Text("Remember Me")
        }
        Spacer(modifier = Modifier.height(10.dp))
**/


        /** Login Button to take user to main screen if login verification is successful **/
        Button(
            onClick = {
                /** TODO Fix Login Verification credentials w/ Database **/
                /** Current verification doesn't work with database, but checks to see if the current user
                 * for this current start up of the app has created a password and that password entered matches,
                 * as well as a username and that username entered matches the one stored in state
                 */
                correctPassword = password != enteredUser.password
                userNameExists = (enteredUser.userName == "" || userName != enteredUser.userName)
                if(!userNameExists && !correctPassword){
                    nav.navigate("mainPage")
                }


            },
            modifier = Modifier.fillMaxWidth(),
            enabled = areFieldsEmpty ) {
                Text("Login", fontSize = 5.em)
        }
        Spacer(modifier = Modifier.height(10.dp))


        /** Forgot Password Button to navigate to change password screen **/
        TextButton(onClick = {
            nav.navigate("changePassword")}
        ) {
            Text("Forgot Password?")
        }
        Spacer(modifier = Modifier.height(10.dp))


        /** Signup Button to navigate to create account screen **/
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
            ) {
            Text("Don't have an account?")
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { nav.navigate("createAccount") }) {
                Text("Sign Up")
            }
        }
    }

}
