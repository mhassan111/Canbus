package com.canbus.app.featureUartSerial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.canbus.app.ui.spacing

@Composable
fun BaudRateDialog(
    modifier: Modifier,
    rate : String,
    negativeText: String = "Cancel",
    positiveText: String = "OK",
    onYes: (String) -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {

    var selectedBaudRate by remember { mutableStateOf(rate) }
    val baudRates = listOf("2400", "9600", "19200", "57600", "115200")

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Column(
            modifier = modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Choose BaudRate",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(MaterialTheme.spacing.xDp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

            baudRates.forEach { baudRate ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedBaudRate = baudRate }
                        .padding(
                            start = MaterialTheme.spacing.medium,
                            top = MaterialTheme.spacing.xDp,
                            bottom = MaterialTheme.spacing.xDp
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    DefaultRadioButton(
                        text = baudRate,
                        selected = selectedBaudRate == baudRate,
                        onSelect = { selectedBaudRate = baudRate })
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.xDp)
                    .background(Color.LightGray),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onCancel()
                    }) {
                    Text(
                        negativeText,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )
                }

                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onYes(selectedBaudRate)
                    }) {
                    Text(
                        positiveText,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
