package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import components.GalaxyWrapper

@Composable
fun ProfileScreen() {
    GalaxyWrapper {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("👨‍🚀 Arrauf Setiawan M. J.", color = Color.White)
            Text("NIM: 123140032", color = Color.Cyan)
        }
    }
}