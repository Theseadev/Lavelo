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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenForm(
    onNoteSaved: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var content by remember { mutableStateOf(TextFieldValue("")) }
    var showMessage by remember { mutableStateOf(false) }

    // 🎨 Warna utama biru muda
    val lightBlue = Color(0xFF64B5F6)

    // 🌈 Atur warna status bar
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = lightBlue, // status bar biru muda
            darkIcons = false  // ikon putih
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lavelo - Tambah Catatan",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = lightBlue, // AppBar biru muda
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🟦 Input Judul
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Catatan") },
                placeholder = { Text("Misal: Ide Aplikasi Lavelo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lightBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = lightBlue,
                    focusedLabelColor = lightBlue
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🟦 Input Isi Catatan
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Isi Catatan") },
                placeholder = { Text("Tuliskan isi catatan di sini...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp),
                maxLines = 8,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lightBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = lightBlue,
                    focusedLabelColor = lightBlue
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 🔘 Tombol Simpan
            Button(
                onClick = {
                    if (title.text.isNotBlank() && content.text.isNotBlank()) {
                        saveNoteToPrefs(context, title.text, content.text)
                        showMessage = true
                        onNoteSaved()

                        coroutineScope.launch {
                            delay(2000)
                            showMessage = false
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightBlue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Simpan Catatan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // 🟩 Pesan sukses
            if (showMessage) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = lightBlue.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "✅ Catatan berhasil disimpan!",
                        color = lightBlue,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

// 💾 Fungsi menyimpan catatan ke SharedPreferences
fun saveNoteToPrefs(context: Context, title: String, content: String) {
    val sharedPref = context.getSharedPreferences("lavelo_notes", Context.MODE_PRIVATE)
    val json = JSONObject()
    json.put("title", title)
    json.put("content", content)
    sharedPref.edit().putString("saved_note", json.toString()).apply()
}
