package com.example.moodmarker.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.example.moodmarker.db.entities.User

@Composable
fun ProfilePage(
    users: List<User>,
    nav: NavHostController) {

    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.height(150.dp))
        }

        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
        ){
                Text(text = "Name", modifier = Modifier.width(100.dp))
                TextField(
                value = name.value,
                onValueChange = { name.value = it }
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "UserName", modifier = Modifier.width(100.dp))
            TextField(
                value = userName.value,
                onValueChange = { userName.value = it }
            )
        }



        Row {
            Spacer(modifier = Modifier.height(100.dp))
        }

    }





}