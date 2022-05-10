package com.screening.app.featureSmsScreening.presentation.conversations.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.screening.app.featureSmsScreening.domain.model.Conversation
import com.screening.app.ui.spacing

@Composable
fun ConversationItem(
    modifier: Modifier,
    conversation: Conversation,
    onConversationTapped: () -> Unit
) {

    Row(modifier = modifier.clickable {
        onConversationTapped()
    }.padding(
        MaterialTheme.spacing.xDp
    ), verticalAlignment = Alignment.CenterVertically) {
        SignatureImage(text = "B")
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xDp))
        Column(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = conversation.phoneNumber,
                style = MaterialTheme.typography.h4
            )

            Text(
                text = conversation.message.body,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            Text(
                text = conversation.message.date.toString(),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun SignatureImage(text: String, backgroundColor: Color = Color.Black) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(MaterialTheme.spacing.x5Dp)
            .background(backgroundColor, shape = CircleShape)
    ) {

        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .defaultMinSize(
                    MaterialTheme.spacing.mediumLarge
                ),
            style = MaterialTheme.typography.h2
        )
    }
}