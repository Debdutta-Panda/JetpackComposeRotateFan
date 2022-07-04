package com.debduttapanda.jetpackcomposerotatefan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.debduttapanda.jetpackcomposerotatefan.ui.theme.JetpackComposeRotateFanTheme
import java.lang.Math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeRotateFanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(){
                        var dr  by remember { mutableStateOf(0f) }
                        var rotation by remember { mutableStateOf(0f) }
                        val infiniteTransition = rememberInfiniteTransition()
                        val tick by infiniteTransition.animateFloat(
                            initialValue = 0F,
                            targetValue = 1000f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(
                                    1000,
                                    easing = LinearEasing,
                                )
                            )
                        )
                        LaunchedEffect(key1 = tick){//acts like frame tick
                            rotation = (rotation + dr)%360f//update the rotation
                        }
                        Canvas(
                            modifier = Modifier.fillMaxSize()
                        ){
                            withTransform(
                                {
                                    translate(200f,400f)
                                    scale(2f,Offset(300f,300f))
                                    rotate(
                                        rotation,
                                        Offset(300f,300f)
                                    )
                                }
                            ){
                                drawRect(
                                    color = Color.Red,
                                    style = Stroke(
                                        width = 4f
                                    ),
                                    topLeft = Offset(200f,200f),
                                    size = Size(200f,200f)
                                )
                                drawLine(
                                    color = Color.Red,
                                    strokeWidth = 10f,
                                    start = Offset(200f,300f),
                                    end = Offset(400f,300f)
                                )
                                drawLine(
                                    color = Color.Red,
                                    strokeWidth = 10f,
                                    start = Offset(300f,200f),
                                    end = Offset(300f,400f)
                                )
                            }
                        }
                        Slider(
                            modifier = Modifier.padding(24.dp),
                            value = dr,
                            valueRange = 0f..100f,
                            onValueChange = {
                                dr = it
                            }
                        )
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = dr.toString(),
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}