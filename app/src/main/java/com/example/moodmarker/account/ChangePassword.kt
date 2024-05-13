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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.ui.theme.components.LoginFields


/** Change password screen
 * Current version doesn't update password in database and only verifys the current user's username in state exists
 */
@Composable
fun ChangePassword(
    users: List<User>,
    nav: NavHostController,
    vmAccounts: AccountsViewModel,
    enteredUser: User,
) {

    val (userName, setUserName) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    var correctPassword by remember { mutableStateOf(false) }
    var arePasswordsSame by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }

    /** Checks for allowing login navigation based on whether all fields are filled or not **/
    val areFieldsEmpty = password.isNotEmpty() && confirmPassword.isNotEmpty()

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
            Text("That username does not exist", color = MaterialTheme.colorScheme.error)
        }


        /** Username Change Password Field **/
        LoginFields(
            value = userName,
            onValueChange = setUserName,
            labelText = "Username",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))


        /** Password Change Password Field with password visual transformation keyboard **/
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


        /** Confirm Password Change Password Field with password visual transformation keyboard **/
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


        /** Submit Button to navigate to back to previous page if verification is successful **/
        Button(onClick = {

            //TODO Fix Change Password w/ Database
            /** Current verification doesn't work with database, but checks to see if the current user
             * for this current start up of the app has create a username and that username entered matches the one stored in state
             *  and the confirm password and password fields are matching
             */
            userNameExists = (enteredUser.userName == "" || userName != enteredUser.userName)
            arePasswordsSame = password != confirmPassword
            if(!userNameExists && !arePasswordsSame) {
                //Change state value of current user's password
                enteredUser.password = password

                // Not currently working with database
                vmAccounts.updateUser(enteredUser)

                //Navigate to previous page
                nav.popBackStack()
            }

        }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
            Text("Submit", fontSize = 5.em)
        }


        /** Cancel Text Button to navigate to login screen if user doesn't want to change password **/
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { nav.popBackStack()}) {
                Text("Cancel")
            }
        }

    }
}