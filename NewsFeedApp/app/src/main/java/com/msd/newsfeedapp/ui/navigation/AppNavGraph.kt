package com.msd.newsfeedapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msd.posts_list.PostListScreen
import com.msd.newsfeedapp.ui.screens.posts.create.CreatePostScreen
import com.msd.newsfeedapp.ui.screens.posts.detail.PostDetailScreen

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
            PostDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
