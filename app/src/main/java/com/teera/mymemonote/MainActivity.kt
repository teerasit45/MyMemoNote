package com.teera.mymemonote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.navArgument
import com.teera.mymemonote.ui.note_list_screen.NoteListScreen
import com.teera.mymemonote.ui.open_note_screen.open_note_screen
import com.teera.mymemonote.ui.theme.MyMemoNoteTheme
import com.teera.mymemonote.utill.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMemoNoteTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.note_list,
                ) {
                    composable(
                        Routes.note_list
                    ) {
                        NoteListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }

                    composable(
                        route = Routes.open_note + "?noteId={noteId}",
                        arguments = listOf(
                            navArgument(name = "noteId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        open_note_screen(onPopBackStack = {navController.popBackStack()})
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyMemoNoteTheme {
        Greeting("Android")
    }
}