package com.screening.app.featureImportContacts

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureImportContacts.presentation.ImportContactsScreen
import com.screening.app.ui.theme.ScreeningAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImportContactsActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PHONE_NUMBER = "EXTRA_PHONE_NUMBER"
    }
    lateinit var mPhoneNumbers : ArrayList<PhoneNumber>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPhoneNumbers = (intent?.getParcelableArrayListExtra<PhoneNumber>(EXTRA_PHONE_NUMBER) ?: emptyList()) as ArrayList<PhoneNumber>
        setContent {
            ImportContactsApp(mPhoneNumbers)
        }
    }
}

@Composable
fun ImportContactsApp(phoneNumbers : List<PhoneNumber>) {
    val activity = (LocalContext.current as? Activity)
    ScreeningAppTheme {
        ImportContactsScreen(
            onCloseClick = {
                activity?.finish()
            },
            phoneNumbers
        )
    }
}