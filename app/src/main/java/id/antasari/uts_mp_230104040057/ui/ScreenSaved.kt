package id.antasari.uts_mp_230104040057.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSaved(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val savedNote = loadSavedNote(context)

    val lightBlue = Color(0xFF64B5F6) // Warna utama Lavelo ✨

    // 🌈 Warna status bar
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = lightBlue,
            darkIcons = false
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lavelo - Catatan Tersimpan",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = lightBlue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (savedNote != null) {
                // 🗒️ Kartu Catatan
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White // Card putih
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = savedNote.first, // Judul
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black // Teks hitam
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = savedNote.second, // Isi catatan
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                color = Color.Black // Teks hitam
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 🔙 Tombol kembali
                Button(
                    onClick = onBack,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = "Kembali ke Beranda",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // 📭 Jika belum ada catatan
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = "Belum ada catatan yang disimpan",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 🔙 Tombol kembali
                OutlinedButton(
                    onClick = onBack,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = lightBlue
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 1.dp,
                        brush = androidx.compose.ui.graphics.SolidColor(lightBlue)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = "Kembali",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// 💾 Fungsi membaca catatan dari SharedPreferences
fun loadSavedNote(context: Context): Pair<String, String>? {
    val sharedPref = context.getSharedPreferences("lavelo_notes", Context.MODE_PRIVATE)
    val savedJson = sharedPref.getString("saved_note", null)
    return if (savedJson != null) {
        val obj = JSONObject(savedJson)
        val title = obj.getString("title")
        val content = obj.getString("content")
        title to content
    } else null
}
