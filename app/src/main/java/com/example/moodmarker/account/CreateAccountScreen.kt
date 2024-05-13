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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.em
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.navigation.Routes
import com.example.moodmarker.ui.theme.components.LoginFields
import kotlin.reflect.KSuspendFunction1

/** Create Account screen
 * Current version does add user to databse, but doesn't verify whether username exists or not
 */
@Composable
fun CreateAccount(
    users: List<User>,
    nav: NavHostController,
    vmAccounts: AccountsViewModel,
    enteredUser: User,
) {
    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (userName, onUserNameChange) = rememberSaveable { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    var arePasswordsSame by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }

    /** Checks for allowing login navigation based on whether all fields are filled or not **/
    val areFieldsEmpty = firstName.isNotEmpty() && password.isNotEmpty() && lastName.isNotEmpty() && confirmPassword.isNotEmpty() && email.isNotEmpty() && userName.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /** Shows a red notification at the top of the screen if the entered password does not match confirm password entered **/
        AnimatedVisibility(arePasswordsSame) {
            Text("Passwords Are Not the Same", color = MaterialTheme.colorScheme.error)
        }

        /** Shows a red notification at the top of the screen if the entered username does not match the one stored **/
        AnimatedVisibility(userNameExists) {
            Text("Username Already Exists", color = MaterialTheme.colorScheme.error)
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


        /** Username Create Account Field **/
        LoginFields(
            value = userName,
            onValueChange = onUserNameChange,
            labelText = "Username",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))


        /** Password Create Account Field with password visual transformation keyboard **/
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
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
        Spacer(Modifier.height(10.dp))


        /** Confirm Password Create Account Field with password visual transformation keyboard **/
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            singleLine = true,
            label = { Text(text = "Confirm Password") },
            shape = RoundedCornerShape(50),
            visualTransformation = if (confirmPasswordVisibility) { VisualTransformation.None } else { PasswordVisualTransformation() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                /** checks whether the user has clicked the show password characters or chosen to show . in their place **/
                if (confirmPasswordVisibility) {
                    IconButton(onClick = { confirmPasswordVisibility = false }) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = "") }
                } else {
                    IconButton(
                        onClick = { confirmPasswordVisibility = true }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = "") }
                }
            }
        )
        Spacer(Modifier.height(20.dp))


        /** Submit Button to take user to login screen if field verification is successful**/
        Button(onClick = {
            arePasswordsSame = password != confirmPassword
            /** TODO Fix Signup Verification **/
//            val a = vmAccounts.userExists(userName)

//            userNameExists = users.any{ it.userName == user.value.userName }
            /** Current verification doesn't work with database, but checks to see if the current user
             * for this current start up of the app has entered information into all the fields
             * and updates their values stored in state
             */
            if(!arePasswordsSame) {
                enteredUser.firstName = firstName
                enteredUser.lastName = lastName
                enteredUser.password = password
                enteredUser.userName = userName
                enteredUser.email = email

                // Currently does add the user to the database
                vmAccounts.addUser(enteredUser)

                // Navigate to the previous page
//               nav.popBackStack()
                // Navigate to login page
                nav.navigate(Routes.LoginPage.route)
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
            TextButton(onClick = { nav.navigate("loginPage") }) {
                Text("Login")
            }
        }
    }
}