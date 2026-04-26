package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import components.GalaxyWrapper

@Composable
fun FavoritesScreen() {
    GalaxyWrapper {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("⭐ Belum ada bintang favorit.", color = Color.White)
        }
    }
}