package com.screening.app.featureSmsScreening.presentation.conversations.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.screening.app.ui.spacing

@Composable
fun ConversationsTopBar(
    onSelected: () -> Unit,
    onSettingTapped: () -> Unit
) {

    val backgroundColor = Color.Blue.copy(alpha = 0.06f)

    Box(
        modifier = Modifier
            .padding(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(MaterialTheme.spacing.x5Dp)
            )
            .clickable {
                onSelected()
            }
            .background(backgroundColor)
            .padding(start = MaterialTheme.spacing.xDp, end = MaterialTheme.spacing.xDp),
        contentAlignment = Alignment.CenterEnd
    ) {

        Box(
            modifier = Modifier
                .width(MaterialTheme.spacing.x4Dp)
                .height(MaterialTheme.spacing.x4Dp)
                .clip(shape = RoundedCornerShape(MaterialTheme.spacing.x4Dp))
                .clickable { onSettingTapped() }, contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                modifier = Modifier.size(MaterialTheme.spacing.mediumLarge),
                contentDescription = "conversations_settings"
            )
        }

        Row(
            Modifier
                .fillMaxWidth(1f)
                .height(BarHeight),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = Modifier.size(MaterialTheme.spacing.mediumLarge),
                contentDescription = "search_conversations_messages"
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                text = "Search conversations and messages",
                style = MaterialTheme.typography.h5
            )
        }
    }
}

private val BarHeight = 45.dp
