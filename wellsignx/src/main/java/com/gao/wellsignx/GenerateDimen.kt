package com.gao.wellsignx

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

/**
 * @author：gao
 * @date：2022/11/19
 * @desc：
 **/

object GenerateDimen {
    @JvmStatic
    fun main(args: Array<String>) {
        val builder = StringBuilder()
        //添加xml开始的标签
        val xmlStart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n <resources>\n"
        builder.append(xmlStart)
        for (i in 0..1000) {
            val dimenName = "    <dimen name=\"dp$i\">"
            val end = "dp</dimen>"
            builder.append(dimenName).append(i).append(end).append("\n")
        }
        //添加sp
        builder.append("\n\n\n<!--sp-->\n")
        for (i in 1..50) {
            val dimenName = "    <dimen name=\"sp$i\">"
            val end = "sp</dimen>"
            builder.append(dimenName).append(i).append(end).append("\n")
        }
        //添加xml的尾标签
        builder.append("</resources>")
        val dimensFile = "./wellsignx/src/main/res/values/dimens.xml"
        var out: PrintWriter? = null
        try {
            out = PrintWriter(BufferedWriter(FileWriter(dimensFile)))
            out.println(builder)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        out?.close()
    }
}