package navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import screens.*

// Data class untuk struktur catatan
data class NoteItem(val id: Int, val title: String, val content: String)

sealed class Screen(val route: String) {
    object Notes : Screen("notes")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object NoteDetail : Screen("note_detail/{noteId}") {
        fun createRoute(id: Int) = "note_detail/$id"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // PENYIMPANAN SEMENTARA: Data ini tidak akan hilang saat pindah tab
    val noteList = remember {
        mutableStateListOf(
            NoteItem(123140032, "Note everything to remember", "Selamat datang di Ups Diary.")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1A1A2E)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val menuItems = listOf(
                    Triple(Screen.Notes, "Notes", "Catatan"),
                    Triple(Screen.Favorites, "Favs", "Favorit"),
                    Triple(Screen.Profile, "User", "Pengguna")
                )

                menuItems.forEach { (screen, label, icon) ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Memperbaiki bug "gabisa balik": balik ke awal tanpa hapus stack
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(label, color = Color.White) },
                        icon = { Text(icon) },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFF3D2C5E))
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Notes.route) {
                NoteListScreen(
                    notes = noteList,
                    onNoteClick = { id -> navController.navigate(Screen.NoteDetail.createRoute(id)) },
                    onSaveNote = { t, c ->
                        val newId = if (noteList.isEmpty()) 1 else noteList.maxOf { it.id } + 1
                        noteList.add(NoteItem(newId, t, c))
                    }
                )
            }
            composable(Screen.Favorites.route) { FavoritesScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(noteId = id, onBack = { navController.popBackStack() })
            }
        }
    }
}