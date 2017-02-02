package com.space.moon

import java.util.*
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.BufferedReader
import java.io.FileReader


/**
 * moon map info
 * flie desc is :
 * oblist: format x,y,r;x1,y1,r1;...
 * pathlist: path speed;x,y;x1,y1;...
 */
object MoonMap {
    var obList = ArrayList<MoonMapObList>()
    var pathList = ArrayList<MoonMapPath>()

    fun init(fileName: String) {
        val reader = FileReader(fileName)
        val br = BufferedReader(reader)

        // line 1 : is obstacle info
        var str = br.readLine()
        println(str)
        if (str != null) {
            var ob = MoonMapObList()
            ob.init(str)
            obList.add(ob)
        }

        // load path
        str = br.readLine()
        while (str != null) {
            var path = MoonMapPath()
            path.init(str)
            pathList.add(path)
            println(str)

            str = br.readLine()
        }

        br.close()
        reader.close()
    }

    fun getSpeed(id: Int): Float {
        if (pathList.size <= id) {
            return 1f
        } else {
            return pathList[id].speed
        }
    }

    fun getX(id: Int, pos: Int): Float {
        if (pathList.size <= id) {
            return -9999f
        } else {
            if (pathList[id].pointList.size <= pos) {
                return -9999f
            }
            return pathList[id].pointList[pos].x
        }
    }

    fun getY(id: Int, pos: Int): Float {
        if (pathList.size <= id) {
            return -9999f
        } else {
            if (pathList[id].pointList.size <= pos) {
                return -9999f
            }
            return pathList[id].pointList[pos].y
        }
    }
}



