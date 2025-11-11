package com.example.newsfeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsfeedapp.screens.CreatePostScreen
import com.example.newsfeedapp.screens.PostDetailsScreen
import com.example.newsfeedapp.screens.PostListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            NewsFeedAppTheme {
//            }

            MaterialTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "post_list"
                ) {
                    composable("post_list") {
                        PostListScreen(
                            onCreatePostClick = { navController.navigate("create_post") },
                            onPostClick = { postId -> navController.navigate("post_details/$postId") }
                        )
                    }

                    composable("create_post") {
                        CreatePostScreen(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable("post_details/{postId}") { backStackEntry ->
                        val postId = backStackEntry.arguments?.getString("postId") ?: ""
                        PostDetailsScreen(
                            postId = postId,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}