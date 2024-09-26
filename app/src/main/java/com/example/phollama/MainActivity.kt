package com.example.myandroidapp

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

object AIModel {

    private lateinit var interpreter: Interpreter

    fun loadModel(context: Context) {
        val modelFile = loadModelFile(context)
        interpreter = Interpreter(modelFile)
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun runModel(input: FloatArray): FloatArray {
        val output = FloatArray(OUTPUT_SIZE)
        interpreter.run(input, output)
        return output
    }

    private const val OUTPUT_SIZE = 10 // Example output size
}
