package com.example.pengembanganaplikasimobile.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pengembanganaplikasimobile.ui.state.UiState
import com.example.pengembanganaplikasimobile.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val state by viewModel.uiState.collectAsState()


    val galaxyBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF000000),
            Color(0xFF0B001E),
            Color(0xFF1B1464)
        )
    )

    Box(modifier = Modifier.fillMaxSize().background(galaxyBackground)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


            Text(
                text = "Portal Berita Hangat",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF00D2FF), // Neon Cyan
                letterSpacing = 2.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Informasi Terhangat Jagat Raya",
                fontSize = 12.sp,
                color = Color(0xFF915AFF), // Light Purple
                modifier = Modifier.padding(bottom = 24.dp)
            )

            when (val s = state) {
                is UiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF00D2FF))
                    }
                }

                is UiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Sinyal Terganggu: ${s.message}", color = Color.Red)
                        Button(
                            onClick = { viewModel.loadNews() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF915AFF))
                        ) {
                            Text("Coba Lagi")
                        }
                    }
                }

                is UiState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        items(s.data) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.White.copy(alpha = 0.05f)) // Transparan tipis
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFF00D2FF).copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clickable { /* Nanti bisa buat buka URL berita */ }
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Text(
                                        text = item.title ?: "Tanpa Judul",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color(0xFF00D2FF),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(Modifier.height(8.dp))

                                    Text(
                                        text = item.description ?: "Sentuh untuk melihat detail berita selengkapnya...",
                                        fontSize = 14.sp,
                                        color = Color(0xFFE0E0E0),
                                        lineHeight = 20.sp,
                                        maxLines = 4,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(Modifier.height(12.dp))

                                    Text(
                                        text = "#NEWS_UPDATE",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF915AFF),
                                        modifier = Modifier
                                            .background(Color(0xFF915AFF).copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}