package org.gongclouds.bluescreen

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import org.gongclouds.bluescreen.ui.theme.BlueScreenTheme

class AppListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlueScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun AppList(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val installedApps = remember(packageManager) {
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    fun launchPackage(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.let {
            launcher.launch(intent)
        }
    }

    var x = 0
    val arr = ArrayList<ApplicationInfo>()
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(installedApps) { app ->
            val appName = packageManager.getApplicationLabel(app).toString()
            val icon: Drawable = packageManager.getApplicationIcon(app)
            val img = icon.toBitmap(40, 40).asImageBitmap()

            if(!appName.contains('.')){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                        .height(80.dp)
                ){
                    Image(img,null)
                    Text(packageManager.getApplicationLabel(app).toString())
                    Button(
                        onClick = {
                            launchPackage(app.packageName)
                        }
                    ) {
                        Text("Launch")
                    }
                }
            }
        }
    }

//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//
//        items(installedApps) {
//
//
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp),
//                onClick = {
//                    launchPackage(it.packageName)
//                }
//            ) {
//                val icon: Drawable = packageManager.getApplicationIcon(it)
//                val img = icon.toBitmap(40, 40).asImageBitmap()
//                Canvas(
//                    modifier = Modifier,
//                    onDraw = {
//                        drawImage(
//                            img,
//                            dstSize = IntSize(40, 40), // Resizes the image
//                            dstOffset = IntOffset(0, 0) // Positions the image
//                        )
//                    }
//                )
////                Icon(img,null)
//                Text(
//                    text = packageManager.getApplicationLabel(it).toString()
//                )
//            }
//
//        }
//    }
}