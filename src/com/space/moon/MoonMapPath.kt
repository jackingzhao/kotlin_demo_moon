package com.space.moon

import java.util.*

/**
 * use circle description : obstacle
 */
class MoonMapPath {
    var speed: Float = 1f
    var pointList = ArrayList<MoonMapPoint>()

    /**
     * init from path string, like : speed-x,y;x1,y1;
     */
    fun init(nodes: String) {
        var speedNode = nodes.split("-")
        if (speedNode.size != 2) {
            return
        }

        speed = speedNode[0].toFloat()

        var nodeList = speedNode[1].split(";")
        nodeList
                .map { it.split(",") }
                .filter { it.size == 2 }
                .map { MoonMapPoint(it[0].toFloat(), it[1].toFloat()) }
                .forEach { pointList.add(it) }
    }
}