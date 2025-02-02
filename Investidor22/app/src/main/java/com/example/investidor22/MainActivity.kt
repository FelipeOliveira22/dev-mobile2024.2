package com.example.investidor22

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.investidor22.ui.theme.Investidor22Theme
import com.example.investidor22.ui.view.InvestidorScreen
import com.example.investidor22.viewmodel.InvestimentosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }
        setContent {
            Investidor22Theme {
                val viewModel: InvestimentosViewModel = viewModel()
                InvestidorScreen(viewModel = viewModel)
            }
        }
    }
}
