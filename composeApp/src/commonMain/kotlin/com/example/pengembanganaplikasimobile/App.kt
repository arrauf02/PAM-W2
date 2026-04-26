import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import navigation.AppNavigation

@Composable

fun App() {
    // Menentukan skema warna gelap untuk tema Galaxy
    val galaxyColorScheme = darkColorScheme(
        primary = Color(0xFFBB86FC),
        secondary = Color(0xFF03DAC6),
        background = Color(0xFF0B0B1E),
        surface = Color(0xFF1A1A2E),
        onPrimary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
    )

    MaterialTheme(
        colorScheme = galaxyColorScheme
    ) {
        // Memanggil fungsi navigasi utama
        AppNavigation()
    }
}