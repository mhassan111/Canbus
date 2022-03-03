package com.os.system.featureInstallation.presentation.activation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.os.system.ui.spacing
import com.os.system.ui.theme.WhiteColor

@Composable
fun HintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .onFocusChanged {
                    onFocusChange(it)
                },
            shape = RoundedCornerShape(MaterialTheme.spacing.large),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = WhiteColor,
                textColor = WhiteColor
            )
        )
        if (isHintVisible) {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.xDp),
                text = hint,
                style = textStyle,
                color = WhiteColor
            )
        }
    }
}