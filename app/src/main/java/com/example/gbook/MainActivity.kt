package com.example.gbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.ui.GBookApp
import com.example.gbook.ui.theme.GBookTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBookTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    GBookApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CompactPreview() {
    GBookTheme {
        GBookApp(windowSize = WindowWidthSizeClass.Compact)
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumPreview() {
    GBookTheme {
        GBookApp(windowSize = WindowWidthSizeClass.Medium)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedPreview() {
    GBookTheme {
        GBookApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}