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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.navigation.Routes
import com.example.moodmarker.ui.theme.components.LoginFields

@Composable
fun EditProfile(
    users: List<User>,
    nav: NavHostController,
    vmAccounts: AccountsViewModel,
    emptyUser: User,
) {
    var user = remember { mutableStateOf(emptyUser)}

    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (userName, onUserNameChange) = rememberSaveable { mutableStateOf("") }

    var arePasswordsSame by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }
    val areFieldsEmpty = firstName.isNotEmpty() && password.isNotEmpty() && lastName.isNotEmpty() && confirmPassword.isNotEmpty() && email.isNotEmpty() && userName.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /** Create Account Header Text **/
        Text(
            text = "Edit Profile",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )


        /** First Name Edit Profile Field **/
        LoginFields(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Last Name Edit Profile Field **/
        LoginFields(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Email Edit Profile Field **/
        LoginFields(
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))


        /** Submit Button **/
        Button(onClick = {
                user.value = user.value.copy(firstName = firstName)
                user.value = user.value.copy(lastName = lastName)
                user.value = user.value.copy(userName = userName)
                user.value = user.value.copy(email = email)

                vmAccounts.updateUser(user.value)
                nav.popBackStack()

        }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
            Text("Submit", fontSize = 5.em)
        }
        Spacer(modifier = Modifier.height(20.dp))


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