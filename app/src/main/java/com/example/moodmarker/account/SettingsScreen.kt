package com.example.moodmarker.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.moodmarker.navigation.Routes

/** Settings screen
 * Current version doesn't logout user from database, just redirects to login screen
 * Also the App settings screen doesn't currently exist
 */
@Composable
fun Settings(nav: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.height(75.dp))
        }

        /** Button to navigate to profile screen **/
        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
            Button(onClick = { nav.navigate(Routes.ProfilePage.route) }) {
                Text("Profile Settings", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }

        //TODO Create App Settings Screen
        /** Button to navigate to app settings screen **/
//        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
//            Button(onClick = { nav.navigate(Routes.AppSettings.route) }) {
//                Text("App Settings", fontSize = 7.em, modifier = Modifier.padding(10.dp))
//            }
//        }

        //TODO Fix logout w/ database
        /** Button to navigate to login screen **/
        Row (modifier = Modifier.padding(50.dp), verticalAlignment = Alignment.CenterVertically){
            Button(onClick = { nav.navigate(Routes.LoginPage.route) }) {
                Text("Logout", fontSize = 7.em, modifier = Modifier.padding(10.dp))
            }
        }

    }
    Row {
        Spacer(modifier = Modifier.height(100.dp))
    }
}