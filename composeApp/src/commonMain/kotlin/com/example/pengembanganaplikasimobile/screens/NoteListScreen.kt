package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.GalaxyWrapper
import navigation.NoteItem
import com.example.pengembanganaplikasimobile.screens.AddNoteDialog

@Composable
fun NoteListScreen(
    notes: List<NoteItem>,
    onNoteClick: (Int) -> Unit,
    onSaveNote: (String, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    GalaxyWrapper {
        Scaffold(
            containerColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showDialog = true },
                    containerColor = Color(0xFFBB86FC)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
                }
            }
        ) { padding ->
            Column(modifier = Modifier.padding(16.dp).padding(padding)) {
                Text("🌌 Ups Diary", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(notes) { note ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF3D2C5E)),
                            modifier = Modifier.fillMaxWidth().clickable { onNoteClick(note.id) }
                        ) {
                            Row(modifier = Modifier.padding(16.dp)) {
                                Text("🚀", modifier = Modifier.padding(end = 8.dp))
                                Column {
                                    Text(note.title, color = Color.Cyan)
                                    Text(note.content, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AddNoteDialog(
                onDismiss = { showDialog = false },
                onSave = { t, c ->
                    onSaveNote(t, c)
                    showDialog = false
                }
            )
        }
    }
}