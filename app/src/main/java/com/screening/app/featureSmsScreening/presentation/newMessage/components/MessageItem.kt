package com.screening.app.featureSmsScreening.presentation.newMessage.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.screening.app.featureSmsScreening.domain.model.Message
import com.screening.app.featureSmsScreening.presentation.conversations.componets.InitialImage
import com.screening.app.ui.spacing

@Composable
fun MessageItem(
    modifier: Modifier,
    message: Message,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 50.dp
) {

    val isIncomingMessage = message.type == "Inbox"
    val contentAlignment = if (isIncomingMessage) Alignment.CenterStart
    else Alignment.CenterEnd
    val backgroundColor = if (isIncomingMessage) Color.DarkGray.copy(alpha = 0.1f) else
        Color.Blue.copy(alpha = 0.1f)

    Box(modifier = modifier, contentAlignment = contentAlignment) {
        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isIncomingMessage) {
                InitialImage(
                    text = "U",
                    size = MaterialTheme.spacing.x3Dp,
                    backgroundColor = Color.Blue,
                    textStyle = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Column(
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .clip(
                        RoundedCornerShape(
                            topStart = MaterialTheme.spacing.x2Dp,
                            topEnd = MaterialTheme.spacing.x2Dp,
                            bottomStart = if (!isIncomingMessage) MaterialTheme.spacing.x2Dp
                            else MaterialTheme.spacing.default,
                            bottomEnd = if (isIncomingMessage) MaterialTheme.spacing.x2Dp
                            else MaterialTheme.spacing.default
                        )
                    )
                    .background(backgroundColor)
                    .padding(
                        top = MaterialTheme.spacing.xDp,
                        bottom = MaterialTheme.spacing.xDp,
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium
                    )
            ) {
                Text(
                    text = message.body,
                    style = MaterialTheme.typography.h5,
                    color = Color.DarkGray
                )
            }
        }
    }

}