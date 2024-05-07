package com.example.moodmarker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController

@Composable
fun LoginPage(nav: NavHostController) {
    //TODO: Implement login w/credentials
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

//    val emojiList = listOf("😁", "😡", "🙁", "🙂", "😐")
//    val randomEmojiOne = emojiList.shuffled().take(1)[0]
//    val randomEmojiTwo = emojiList.shuffled().take(1)[0]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.height(25.dp))
        }

//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//        ){
//            Text(text = "M", fontSize = 18.em)
//            Text(text = randomEmojiOne, fontSize = 10.em)
//            Text(text = randomEmojiTwo, fontSize = 10.em)
//            Text(text = "d", fontSize = 18.em)
//        }
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//        ){
//            Text(text = "Marker", fontSize = 18.em)
//        }

        Row {
            Spacer(modifier = Modifier.height(50.dp))
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

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Password", modifier = Modifier.width(100.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it }
            )
        }

        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
            Button(onClick = { nav.navigate(Routes.MainPage.route) }, modifier = Modifier.padding(10.dp) ) {
                Text("Login", fontSize = 5.em, modifier = Modifier.padding(5.dp))
            }
            Button(onClick = { nav.navigate(Routes.CreateAccount.route) }) {
                Text("Signup", fontSize = 5.em, modifier = Modifier.padding(5.dp))
            }
        }


    }




    Row {
        Spacer(modifier = Modifier.height(100.dp))
    }
}