package com.example.boardofmessageapp_kt2.ui.compose

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
import com.example.boardofmessageapp_kt2.ui.theme.BoardOfMessageApp_kt2Theme

@Composable
fun MessageCard(
    position: Int = 0,
    from: String = "Person 1",
    to: String = "Person 2",
    text: String = "Hi, how are you doing?",
    timestamp: String = "2024-05-08"
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
                modifier = Modifier.width(30.dp)
            ) {
                Text(
                    text = "#" + position,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = "From: " + from,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "To: " + to)
                Text(
                    text = ">> " + text,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "- " + timestamp,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    BoardOfMessageApp_kt2Theme {
        MessageCard()
    }
}