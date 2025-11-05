package id.antasari.uts_mp_230104040057.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.antasari.uts_mp_230104040057.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenList(
    onCreateNew: () -> Unit,
    onViewSaved: () -> Unit
) {
    // 🎨 Warna utama biru muda lembut
    val lightBlue = Color(0xFF64B5F6)

    // 🔹 Atur warna status bar agar menyatu dengan app bar
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = lightBlue,  // warna biru muda di status bar
            darkIcons = false   // ikon putih agar kontras
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lavelo",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White // teks putih
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = lightBlue, // background biru muda
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔹 Icon Vector
            Icon(
                painter = painterResource(id = R.drawable.ic_note),
                contentDescription = "Lavelo Icon",
                tint = lightBlue,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 24.dp)
            )

            // 🔹 Judul
            Text(
                text = "Selamat Datang di Lavelo",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = lightBlue
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Catat momen, ide, dan inspirasi terbaikmu di satu tempat.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Tombol utama (biru muda)
            Button(
                onClick = onCreateNew,
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightBlue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "📝 Buat Catatan Baru",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 🔹 Tombol kedua (outline biru muda)
            OutlinedButton(
                onClick = onViewSaved,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = lightBlue
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp,
                    brush = androidx.compose.ui.graphics.SolidColor(lightBlue)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "📂 Lihat Catatan Tersimpan",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
