package com.screening.app.featureCallScreening.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    phoneNumberOrder: PhoneNumberOrder = PhoneNumberOrder.Number(OrderType.Descending),
    onOrderChange: (PhoneNumberOrder) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Number",
                selected = phoneNumberOrder is PhoneNumberOrder.Number,
                onSelect = { onOrderChange(PhoneNumberOrder.Number(phoneNumberOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = phoneNumberOrder is PhoneNumberOrder.Date,
                onSelect = { onOrderChange(PhoneNumberOrder.Date(phoneNumberOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = phoneNumberOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(phoneNumberOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = phoneNumberOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(phoneNumberOrder.copy(OrderType.Descending))
                }
            )
        }
    }

}