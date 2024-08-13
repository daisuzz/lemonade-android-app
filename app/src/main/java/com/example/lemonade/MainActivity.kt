package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeWithImageAndButton(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    val (imageId, imageDescription, text) = when (count) {
        0 -> Triple(R.drawable.lemon_tree, "lemon tree", R.string.lemon_tree_text)
        1 -> Triple(R.drawable.lemon_squeeze, "lemon squeeze", R.string.lemon_squeeze_text)
        2 -> Triple(R.drawable.lemon_drink, "lemon drink", R.string.lemon_drink_text)
        else -> Triple(R.drawable.lemon_restart, "lemon restart", R.string.lemon_restart_text)
    }
    var squeezeCount by remember { mutableIntStateOf(0) }
    val squeezeMax = (2..4).random()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFF387))
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(200.dp))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = imageDescription,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color(0xFFBFFFE1))
                .border(2.dp, Color(0xFF69CDD8), RoundedCornerShape(40.dp))
                .clickable {
                    when (count) {
                        // lemon squeeze以外の場合は、次の状態に遷移する
                        0, 2 -> count++
                        1 -> {
                            if (squeezeCount >= squeezeMax) {
                                count++
                                squeezeCount = 0
                            } else squeezeCount++
                        }
                        // lemon restartの場合は、lemon treeに戻る
                        3 -> count = 0
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = text),
            fontSize = 18.sp,
            color = Color(0xFF000000)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeWithImageAndButton(
        modifier = Modifier.fillMaxSize()
    )
}