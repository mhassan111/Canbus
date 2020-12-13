package com.im.bin.appUtils

import org.apache.commons.io.FileUtils
import timber.log.Timber
import java.io.File

object FileUtil {

    fun getListFiles(parentDir: File): List<String> {
        val inFiles = mutableListOf<String>()
        try {
            val files = parentDir.listFiles()
            files?.let {
                for (file in files) {
                    inFiles.add(file.name)
                }
            }
        } catch (e: Exception) {
            Timber.d("Error Retrieving dir files list: %s", e.message)
        }
        return inFiles
    }

    fun copyFile(srcFile: File, destFile: File) {
        FileUtils.copyFile(srcFile, destFile)
    }
}