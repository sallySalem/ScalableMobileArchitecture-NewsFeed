package com.example.newsfeedapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsfeedapp.ui.screens.posts.PostListScreen
import com.example.newsfeedapp.ui.screens.posts.CreatePostScreen
import com.example.newsfeedapp.ui.screens.posts.detail.PostDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "post_list"
    ) {
        composable("post_list") {
            PostListScreen(
                onCreatePostClick = { navController.navigate("create_post") },
                onPostClick = { postId -> navController.navigate("detail/$postId") }
            )
        }

        composable("create_post") {
            CreatePostScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            "detail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.LongType })
        ) { backStackEntry ->
//            val postId = backStackEntry.arguments?.getString("postId") ?: ""
//            PostDetailScreen(
//                postId = postId,
//                onBack = { navController.popBackStack() }
//            )
            PostDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
