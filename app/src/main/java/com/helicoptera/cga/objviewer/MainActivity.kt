package com.helicoptera.cga.objviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helicoptera.cga.objviewer.view.ObjView
import com.helicoptera.cga.parser.ObjParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assets = assets
        val inputStream =  assets.open("gourd.obj")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        val source = String(buffer)
        val parser = ObjParser()
        val obj = parser.parseObj(source)

        val objView = findViewById<ObjView>(R.id.obj_view)
        objView.obj = obj
    }
}