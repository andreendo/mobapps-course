package com.example.one2nineappwithroom_kt2.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one2nineappwithroom_kt2.game.Number
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@Composable
fun GameScreen(
    numbers: List<Number>,
    markers: List<Boolean>,
    onClick: (Int, Int) -> Unit = { i, i1 -> }
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameButton(
                label = numbers[0].label,
                marked = markers[0],
                onClicked = { onClick(0, numbers[0].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[1].label,
                marked = markers[1],
                onClicked = { onClick(1, numbers[1].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[2].label,
                marked = markers[2],
                onClicked = { onClick(2, numbers[2].value) },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameButton(
                label = numbers[3].label,
                marked = markers[3],
                onClicked = { onClick(3, numbers[3].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[4].label,
                marked = markers[4],
                onClicked = { onClick(4, numbers[4].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[5].label,
                marked = markers[5],
                onClicked = { onClick(5, numbers[5].value) },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameButton(
                label = numbers[6].label,
                marked = markers[6],
                onClicked = { onClick(6, numbers[6].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[7].label,
                marked = markers[7],
                onClicked = { onClick(7, numbers[7].value) },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                label = numbers[8].label,
                marked = markers[8],
                onClicked = { onClick(8, numbers[8].value) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun GameButton(
    label: String = "",
    marked: Boolean = false,
    onClicked: () -> Unit = {},
    modifier: Modifier
) {
    val buttonColors = if (marked) ButtonDefaults.buttonColors(containerColor = Color.Green) else ButtonDefaults.buttonColors()

    Button(
        onClick = onClicked,
        shape = RoundedCornerShape(20.dp),
        colors = buttonColors,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(1.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    var numbers = (1..9).map { Number(it, it.toString()) }
    var markers = (1..9).map { false }.toMutableList()

    One2NineAppWithRoom_kt2Theme {
        markers[6] = true
        markers[1] = true
        GameScreen(numbers, markers)
    }
}
