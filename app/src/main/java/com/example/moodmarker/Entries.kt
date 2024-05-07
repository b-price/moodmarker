package com.example.moodmarker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@Composable
fun Entries(
    entries: List<MoodMarker>,
    showDialog: Boolean = false,
    onDelete: () -> Unit,
    onPrepareDelete: (MoodMarker) -> Unit,
    dismissDialog: () -> Unit,
    onToggleFavorite: (MoodMarker) -> Unit,
    onEditMoodMarker: KFunction1<MoodMarker, Unit>,
    nav: NavHostController,
    setEdit: KFunction0<Unit>,
    ){

    Box(contentAlignment = Alignment.Center) {
        if (showDialog) {
            ConfirmDialog(onDelete = onDelete, onDismiss = dismissDialog )
        }
        Column {
            //SearchBar(onFilter = onFilter)
            val content: @Composable () -> Unit = {
                LazyColumn{
                    items(entries) { entry ->
                        MoodMarkerRow(entry, onPrepareDelete, onToggleFavorite, onEditMoodMarker, nav, setEdit)
                    }
                }
            }
            content()
        }
    }
}

@Composable
fun ConfirmDialog(
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onDelete) { Text("Delete") }
        },
        title = {
            Text("Delete?")
        },
        text = {
            Text("Are you sure you want to delete?")
        }
    )
}