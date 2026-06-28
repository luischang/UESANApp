package com.example.uesanapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uesanapp.presentation.apifootball.ApiFootballScreen
import com.example.uesanapp.presentation.auth.LoginScreen
import com.example.uesanapp.presentation.auth.RegisterScreen
import com.example.uesanapp.presentation.chat.GeminiChatScreen
import com.example.uesanapp.presentation.home.HomeScreen
import com.example.uesanapp.presentation.permissions.GalleryPermissionsScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = "home"){
        composable("register"){ RegisterScreen(navController) }
        composable("login"){ LoginScreen(navController) }
        composable("home"){
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }
        composable("permissions"){
            DrawerScaffold(navController) {
                GalleryPermissionsScreen()
            }
        }
        composable("football"){
            DrawerScaffold(navController) {
                ApiFootballScreen()
            }
        }
        //Chat
        composable("chat"){
            DrawerScaffold(navController) {
                GeminiChatScreen("AIzaSyAWEay0xtYfipqTBvfgkoNyyldHSUt2uqQ")
            }
        }
    }
}