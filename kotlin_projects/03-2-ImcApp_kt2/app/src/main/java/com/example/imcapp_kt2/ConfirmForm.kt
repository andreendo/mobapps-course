package com.example.imcapp_kt2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imcapp_kt2.ui.theme.ImcApp_kt2Theme

@Composable
fun ConfirmForm(
    name: String = "",
    age: String = "",
    height: String = "",
    weight: String = "",
    onCalculateButtonClicked: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.confirm_form),
            style = MaterialTheme.typography.headlineMedium
        )
        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.size(5.dp))
        Row {
            Text(
                text = stringResource(id = R.string.name) + ":",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row {
            Text(
                text = stringResource(id = R.string.age) + ":",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = age,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row {
            Text(
                text = stringResource(id = R.string.height) + ":",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = height,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = stringResource(id = R.string.height_m),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row {
            Text(
                text = stringResource(id = R.string.weight) + ":",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = weight,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = stringResource(id = R.string.weight_kg),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = onCalculateButtonClicked) {
            Text(text = stringResource(id = R.string.calculate))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmFormPreview() {
    ImcApp_kt2Theme {
        ConfirmForm()
    }
}