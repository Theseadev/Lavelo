package id.antasari.uts_mp_230104040057

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.antasari.uts_mp_230104040057.ui.ScreenList
import id.antasari.uts_mp_230104040057.ui.ScreenForm
import id.antasari.uts_mp_230104040057.ui.ScreenSaved
import id.antasari.uts_mp_230104040057.ui.theme.LaveloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaveloTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                ) {
                    val navController = rememberNavController()
                    LaveloNavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun LaveloNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ScreenList(
                onCreateNew = { navController.navigate("form") },
                onViewSaved = { navController.navigate("saved") }
            )
        }
        composable("form") {
            ScreenForm(
                onNoteSaved = { navController.popBackStack() }
            )
        }
        composable("saved") {
            ScreenSaved(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
