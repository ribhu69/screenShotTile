package com.example.myapplication

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream


@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class ScreenShotTile : TileService() {

    private lateinit var rootView : View


    override fun onTileAdded() {
        super.onTileAdded()
        Log.d(TAG, "onTileAdded")
    }


    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d(TAG, "onTileAdded")
    }


    // on tapping the qstile this is invoked
    override fun onClick() {


        Log.d(TAG, "onClick" + qsTile.state.toString())
        takeScreenshot()

    }

    override fun onStartListening() {

        val tile: Tile = qsTile
        Log.d(TAG, "onStartListening" + tile.label)
    }

    override fun onStopListening() {
        super.onStopListening()
        Log.d(TAG, "onStopListening")
    }



    private fun takeScreenshot() 
    
         // closes the notification panel onclick to proceed with taking the screenshot of the current view 

        val closeIntent = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        sendBroadcast(closeIntent)

        rootView = Activity().window.decorView.rootView as View


        // create bitmap screen capture and saves it to Pictures
        try {


            rootView.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(rootView.drawingCache)
            rootView.isDrawingCacheEnabled = false

            //val mPath: String = Environment.getExternalStorageDirectory().toString() + "/" + "now" + ".jpg"

            val mPath: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + "arka" + ".jpg"
            val imageFile = File(mPath)


            //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()
            Log.d(TAG,"Operation Completed")
            //Toast.makeText(this, "Captured", Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            e.printStackTrace()
        }

    }
}
