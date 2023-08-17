package com.paulo.motionanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.paulo.motionanimation.deepLink.MyDeepLink
import com.paulo.motionanimation.organizeViews.HomeScreenFactory
import com.paulo.motionanimation.organizeViews.HomeScreenFactoryPattern
import com.paulo.motionanimation.organizeViews.Screen
import com.paulo.motionanimation.ui.animations.Notification
import com.paulo.motionanimation.ui.credentials.CredentialExampleScreen
import com.paulo.motionanimation.ui.theme.MotionAnimationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val credentialManager by lazy {
        CredentialManager.create(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionAnimationTheme {

                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   /* Button(onClick = { *//*TODO*//* }) {
                        LaunchedEffect(key1 = Unit) {
                            scope.launch {
                                credentialManager.createCredential(
                                    request = CreatePasswordRequest("paulosoujava", "123123"),
                                    activity = this@MainActivity,
                                )
                            }
                        }

                    }*/
                    //CredentialExampleScreen()
                    //MyDeepLink(context = this)


                 /*   NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
                        HomeScreenFactory().create(navController = navController, navGraphBuilder =  this)
                    }
*/
                    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
                        HomeScreenFactoryPattern(PaddingValues(20.dp)).create(
                            navController = navController,
                            navGraphBuilder = this
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Show(
    visible: Boolean,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(visible = visible) {
        content()
    }

}