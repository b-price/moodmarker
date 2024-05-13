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

/** Edit Profile screen
 * Current version doesn't update user with database, just with current state
 */
@Composable
fun EditProfile(
    users: List<User>,
    nav: NavHostController,
    vmAccounts: AccountsViewModel,
    enteredUser: User,

) {

    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf(enteredUser.firstName) }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf(enteredUser.lastName) }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf(enteredUser.email) }
    val (userName, onUserNameChange) = rememberSaveable { mutableStateOf(enteredUser.userName) }

    var arePasswordsSame by remember { mutableStateOf(false) }
    var userNameExists by remember { mutableStateOf(false) }

    /** Checks for allowing login navigation based on whether all fields are filled or not **/
    val areFieldsEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && userName.isNotEmpty()

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
        Spacer(Modifier.height(2.dp))


        /** Last Name Edit Profile Field **/
        LoginFields(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(2.dp))

        /** Last Name Edit Profile Field **/
        LoginFields(
            value = userName,
            onValueChange = onUserNameChange,
            labelText = "Username",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(2.dp))


        /** Email Edit Profile Field **/
        LoginFields(
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(2.dp))


        //TODO Fix Update User Information w/ Database
        /** Submit Button navigate to back to previous page and update state values of the current user**/
        Button(onClick = {
            //Change state values of current user
            enteredUser.firstName = firstName
            enteredUser.lastName = lastName
            enteredUser.userName = userName
            enteredUser.email = email

            // Not currently working with database
            vmAccounts.updateUser(enteredUser)

            //Navigate to previous page
            nav.popBackStack()

        }, modifier = Modifier.fillMaxWidth(), enabled = areFieldsEmpty ) {
            Text("Submit", fontSize = 5.em)
        }
        Spacer(modifier = Modifier.height(20.dp))

        /** Cancel Text Button to navigate to login screen if user doesn't want to edit their profile information **/
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