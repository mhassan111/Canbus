package com.canbus.app.featureUartSerial.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.canbus.app.featureUartSerial.presentation.TextFieldState
import com.canbus.app.featureUartSerial.presentation.UartCommunicationEvent
import com.canbus.app.ui.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendCanDataDialog(
    modifier: Modifier,
    messageState: MutableState<TextFieldState>,
    text: String,
    negativeText: String = "Cancel",
    positiveText: String = "Send",
    onMessageValueChange: (String) -> Unit,
    onMessageFocusStateChange: (FocusState) -> Unit,
    onYes: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = MaterialTheme.spacing.x2Dp, end = MaterialTheme.spacing.x2Dp),
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.x3Dp,
                        end = MaterialTheme.spacing.medium
                    )
            ) {
                Image(
                    imageVector = Icons.Default.Close,
                    modifier = Modifier
                        .size(MaterialTheme.spacing.x5Dp)
                        .clickable {
                            onDismiss()
                        },
                    contentDescription = "Close"
                )
            }

            Column(
                modifier = modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.x2Dp)
                        .size(MaterialTheme.spacing.x5Dp)
                        .clip(RoundedCornerShape(MaterialTheme.spacing.x5Dp))
                        .background(MaterialTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(MaterialTheme.spacing.x3Dp),
                        imageVector = Icons.Default.Send,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Text(
                    text,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(MaterialTheme.spacing.xDp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xDp))

                HintTextField(
                    text = messageState.value.text,
                    hint = "Send Can Data",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onValueChange = {
                        onMessageValueChange(it)
                    },
                    onFocusChange = {
                        onMessageFocusStateChange(it)
                    },
                    isHintVisible = messageState.value.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    textColor = Color.Black,
                    onKeyboardActionDone = {
                        keyboardController?.hide()
                    }
                )

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
                            onYes()
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
}