package com.alfikri.rizky.data.cache

import android.content.Context
import android.util.Log
import java.io.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileManager @Inject constructor() {

    fun writeToFile(file: File, fileContent: String) {
        if (!file.exists()) {
            try {
                val writer = FileWriter(file)
                writer.write(fileContent)
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun readFileContent(file: File): String {
        val fileContentBuilder = StringBuilder()

        if (file.exists()) {
            var stringLine = ""

            try {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                while ((bufferedReader.readLine().also { stringLine = it }) != null) {
                    Log.e("stringLine", stringLine)
                    fileContentBuilder.append(stringLine).append("\n")
                }

                bufferedReader.close()
                fileReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d("Error Cache", e.message.toString())
            }
        }

        return fileContentBuilder.toString()
    }

    fun isFileExists(file: File): Boolean = file.exists()

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun clearDirectory(fileDirectory: File): Boolean {
        var result = false
        if (fileDirectory.exists()) {
            for (file in fileDirectory.listFiles()) {
                result = file.delete()
            }
        }

        return result
    }

    fun writeToPreferences(context: Context, preferenceFileName: String, key: String, value: Long) {
        val sharedPref =
            context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        val prefEditor = sharedPref.edit()
        prefEditor.putLong(key, value)
        prefEditor.apply()
    }

    fun getFromPreference(context: Context, preferenceFileName: String, key: String): Long {
        val sharedPref =
            context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        return sharedPref.getLong(key, 0)
    }
}