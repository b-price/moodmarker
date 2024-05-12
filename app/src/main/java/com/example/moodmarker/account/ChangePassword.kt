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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.ui.theme.components.LoginFields



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

    var correctPassword by remember { mutableStateOf(false) }
    var arePasswordsSame by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }
    val areFieldsEmpty = password.isNotEmpty() && confirmPassword.isNotEmpty()

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
        AnimatedVisibility(userNameExists) {
            Text("That username does not exist", color = MaterialTheme.colorScheme.error)
        }

        /** Username Login Field **/
        LoginFields(
            value = userName,
            onValueChange = setUserName,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))


        /** Password Change Password Field **/
        LoginFields(
            value = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(10.dp))


        /** Confirm Password Change Password Field **/
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

            /** TODO Fix Change Password w/ Database **/
            userNameExists = (enteredUser.userName == "" || userName != enteredUser.userName)
            arePasswordsSame = password != confirmPassword

            if(!userNameExists && !arePasswordsSame) {

                enteredUser.password = password

                vmAccounts.updateUser(enteredUser)
                nav.popBackStack()

            }
        }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
            Text("Submit", fontSize = 5.em)
        }

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