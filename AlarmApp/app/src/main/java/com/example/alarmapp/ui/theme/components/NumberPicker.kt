package com.example.alarmapp.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alarmapp.R

@Composable
fun NumberPicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    var currentValue by remember { mutableStateOf(value) }

    Row(modifier = Modifier.padding(8.dp)) {
        IconButton(onClick = {
            if (currentValue > range.first) {
                currentValue--
                onValueChange(currentValue)
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_alarm), // Substitua com o ícone apropriado
                contentDescription = "Decrement"
            )
        }

        Text(
            text = currentValue.toString(),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(onClick = {
            if (currentValue < range.last) {
                currentValue++
                onValueChange(currentValue)
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_alarm),
                contentDescription = "Increment"
            )
        }
    }
}
