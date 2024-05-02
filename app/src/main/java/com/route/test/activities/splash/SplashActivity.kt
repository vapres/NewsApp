package com.route.test.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.route.test.R
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.navigationBarsPadding
import com.route.test.activities.HomeActivity



@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {

                SplashScreen()

                viewModel.delegate = OnSplashNavigation {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }, 2000)
                }

                viewModel.navigateToHomeActivity()


            }


        }
    }
}

@Composable
fun SplashScreen() {
    ConstraintLayout(
        modifier = Modifier.paint(painter = painterResource(R.drawable.pattern))
    ) {
        val (logo, signature) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )

        Image(
            painter = painterResource(R.drawable.signature),
            contentDescription = "signature",
            modifier = Modifier
                .constrainAs(signature) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.padding(bottom = 16.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
