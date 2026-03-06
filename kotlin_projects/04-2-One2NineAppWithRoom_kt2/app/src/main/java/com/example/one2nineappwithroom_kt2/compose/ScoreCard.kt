package com.example.one2nineappwithroom_kt2.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.one2nineappwithroom_kt2.ui.theme.One2NineAppWithRoom_kt2Theme

@Composable
fun ScoreCard(
    position: Int = 0,
    name: String = "Player Name",
    gameTime: Float = 0.00f,
    whenPlayed: String = "29-04-2024 11:23:13"
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp), // Add rounded corners to the card
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.width(50.dp)
            ) {
                Text(
                    text = "#" + position,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = String.format("took %.2f s", gameTime))
                Text(
                    text = "in " + whenPlayed,
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScoreCardPreview() {
    One2NineAppWithRoom_kt2Theme {
        ScoreCard()
    }
}