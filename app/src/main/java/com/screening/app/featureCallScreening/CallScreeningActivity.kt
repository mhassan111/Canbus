package com.screening.app.featureCallScreening

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.AddEditPhoneNumberScreen
import com.screening.app.featureCallScreening.presentation.phoneNumbers.PhoneNumbersScreen
import com.screening.app.featureCallScreening.util.CallScreen
import com.screening.app.featureImportContacts.ImportContactsActivity
import com.screening.app.ui.theme.ScreeningAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class CallScreeningActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CallScreeningApp()
        }
    }
}

@Composable
fun CallScreeningApp() {
    ScreeningAppTheme {
        val context = LocalContext.current
        val allScreens = CallScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
//        val currentScreen = PhoneNumberScreen.fromRoute(backStackEntry.value?.destination?.route)
        Scaffold() { innerPadding ->
            PhoneNumberNavHost(
                context = context,
                navController = navController,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PhoneNumberNavHost(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CallScreen.PhoneNumber.name,
        modifier = modifier
    ) {

        val addEditPhoneNumber = CallScreen.AddEditPhoneNumber.name
        composable(route = CallScreen.PhoneNumber.name) {
            PhoneNumbersScreen(
                onAddEditPhoneNumber = { phoneNumber ->
                    navController.navigate("$addEditPhoneNumber?$phoneNumber")
                },
                onImportContacts = { phoneNumbers ->
                    context.startActivity(
                        Intent(
                            context,
                            ImportContactsActivity::class.java
                        ).putParcelableArrayListExtra(
                            ImportContactsActivity.EXTRA_PHONE_NUMBER,
                            ArrayList(phoneNumbers)
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                }
            )
        }

        composable(
            route = "$addEditPhoneNumber?phoneNumber={phoneNumber}",
            arguments = listOf(
                navArgument("phoneNumber") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
        ) { entry ->
            val phoneNumber = entry.arguments?.getString("phoneNumber") ?: ""
            AddEditPhoneNumberScreen(
                phoneNumber = phoneNumber,
                savedPhoneNumber = {
                    navController.navigate(CallScreen.PhoneNumber.name)
                })
        }
    }
}