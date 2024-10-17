package org.gongclouds.bluescreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gongclouds.bluescreen.ui.theme.BlueScreenTheme
import java.lang.Thread.sleep

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            BlueScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

private val i = 0

@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var fail by remember { mutableStateOf(false) }

    var time by remember { mutableIntStateOf(0) }
    var input by remember { mutableStateOf("_") }
    var click by remember { mutableIntStateOf(0) }

    val imageBackGround = ImageBitmap.imageResource(id = R.drawable.win98)

    val procssing = ArrayList<ImageBitmap>()

    var procssingIndex by remember { mutableIntStateOf(0) }

    procssing.add(ImageBitmap.imageResource(id = R.drawable.p2))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p3))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p4))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p5))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p6))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p7))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p8))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p9))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p10))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p11))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p12))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p13))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p14))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p15))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p16))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p17))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p18))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p19))
    procssing.add(ImageBitmap.imageResource(id = R.drawable.p20))

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    val intent = Intent(context, AppListActivity::class.java)

    LaunchedEffect(key1 = time) {
        sleep(10)

        time++

        if (time % 8 == 0) {
            procssingIndex++
            if (procssingIndex == 19) {
                procssingIndex = 0
            }
        }

        if (time % 20 == 0 && fail) {
            input = if (input == " ") {
                "_"
            } else {
                " "
            }
        }

        if (time > 100) {
            time = 0
        }
    }

    if (fail) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp, 60.dp, 20.dp, 0.dp)
                .pointerInput(Unit) {
                    while (true) {
                        val pointer =
                            awaitPointerEventScope { awaitPointerEvent(PointerEventPass.Main) }
                        if (pointer.type == PointerEventType.Press) {
                            click++
                            if (click == 9) {
                                intent.let {
                                    launcher.launch(intent)
                                }
                            }
                        }
                    }
                },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Windows",
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Black),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(800),
                    fontSize = 60.sp,
                    color = Color.White,
                )
            }
            Text(
                text = "An error has occurred. To continue:",
                fontSize = 40.sp
            )
            Text(
                text = "Press Enter to return to Windows, or",
                fontSize = 40.sp
            )
            Text(
                text = "Press CTRL+ALT+DEL to restart your",
                fontSize = 40.sp
            )
            Text(
                text = "computer.",
                fontSize = 40.sp
            )
            Text(
                text = "Error:${click}E:016F:BFF9B3D5",
                fontSize = 40.sp
            )
            Text(
                text = "Press any key to continue $input",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                fontSize = 40.sp,
            )
        }
    } else {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    while (true) {
                        val pointer =
                            awaitPointerEventScope { awaitPointerEvent(PointerEventPass.Main) }
                        if (pointer.type == PointerEventType.Press) {
                            click++
                            if (click == 2) {
                                fail = true
                            }
                        }
                    }
                }
            ,
            onDraw = {
                drawImage(
                    imageBackGround,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()), // Resizes the image
                    dstOffset = IntOffset(0, 0) // Positions the image
                )
                drawImage(
                    procssing[procssingIndex],
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()), // Resizes the image
                    dstOffset = IntOffset(0, 0) // Positions the image
                )
            }
        )
    }
}