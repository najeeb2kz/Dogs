package com.chaudhry.najeeb.dogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chaudhry.najeeb.dogs.ui.NavGraph
import com.chaudhry.najeeb.dogs.ui.theme.DogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsTheme {
                NavGraph()
            }
        }
    }
}