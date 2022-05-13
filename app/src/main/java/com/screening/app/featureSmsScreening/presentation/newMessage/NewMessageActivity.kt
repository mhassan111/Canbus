package com.screening.app.featureSmsScreening.presentation.newMessage

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.presentation.newMessage.message.MessageScreen
import com.screening.app.featureSmsScreening.presentation.newMessage.message.NewMessageScreen
import com.screening.app.featureSmsScreening.presentation.newMessage.selectContact.SelectNewContactScreen
import com.screening.app.ui.theme.ScreeningAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewMessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewConversationApp()
        }
    }
}

@Composable
fun NewConversationApp() {
    val activity = (LocalContext.current) as Activity
    ScreeningAppTheme {
        val allScreens = MessageScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MessageScreen.fromRoute(backStackEntry.value?.destination?.route)

        Scaffold { innerPadding ->
            NewMessageNavHost(
                activity = activity,
                navHostController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun NewMessageNavHost(
    activity: Activity,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = MessageScreen.SelectNewContact.name,
        modifier = modifier
    ) {
        composable(MessageScreen.SelectNewContact.name) {
            SelectNewContactScreen(
                onContactSelected = { contact ->
                    navigateToNewMessageScreen(navHostController, contact)
                }, onNavigateBack = {
                    activity.finish()
                })
        }

        composable(
            route = "${MessageScreen.NewMessage.name}/{name}/{id}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "screening://${MessageScreen.NewMessage.name}/{name}/{id}"
                }
            )
        ) { entry ->
            val name = entry.arguments?.getString("name") ?: "Unknown"
            val id = entry.arguments?.getString("id") ?: "0"
            NewMessageScreen(
                name = name,
                id = id,
                onNavigateBack = {
                    activity.finish()
                })
        }
    }
}

private fun navigateToNewMessageScreen(navHostController: NavHostController, contact: Contact) {
    navHostController.navigate("${MessageScreen.NewMessage.name}/${contact.contactFirstName}/${contact.contactId}")
}