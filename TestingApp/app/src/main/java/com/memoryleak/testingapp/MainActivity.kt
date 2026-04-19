package com.memoryleak.testingapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.memoryleak.testingapp.ui.theme.TestingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

        val image = painterResource(R.drawable.ic_menu_add)
        Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            alpha = 0.5f,
        )
            Column (
                modifier = modifier.padding(4.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Hello $name!",
                    modifier = modifier,
                    fontSize = 100.sp,
                    lineHeight = 100.sp,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "ssss $name!",
                    modifier = modifier.padding(16.dp).align (Alignment.End),
                    fontSize = 36.sp,
                )
            }

    }

}
@Preview(showBackground = true,
//    showSystemUi = true,
    name = "Testing App")
@Composable
fun GreetingPreview() {
    TestingAppTheme {
        Greeting("Android")
    }
}