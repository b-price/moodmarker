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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import com.example.moodmarker.db.entities.User
import com.example.moodmarker.navigation.Routes


/** TODO Fix Display Current User Values **/
@Composable
fun ProfilePage(
    users: List<User>,
    nav: NavHostController,
    enteredUsername: MutableState<String>
) {

    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(){
            /** Profile Screen Header Text **/
            Text(
                text = enteredUsername.value,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp)
//                    .align(alignment = Alignment.Start)
                //color = Color.
            )

            Text(
                text = "'s Profile",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 16.dp)
//                    .align(alignment = Alignment.Start)
                //color = Color.
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "First Name: ",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Last Name: ",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Username: ",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Email: ",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(alignment = Alignment.Start)
            //color = Color.
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                nav.navigate("editProfile")
            },
            modifier = Modifier.fillMaxWidth()) {
            Text("Edit Information", fontSize = 5.em)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                nav.navigate("changePassword")
            },
            modifier = Modifier.fillMaxWidth()) {
            Text("Change Password", fontSize = 5.em)
        }


        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { nav.navigate("loginPage") }) {
                Text("Delete Account")
            }
        }
    }


}