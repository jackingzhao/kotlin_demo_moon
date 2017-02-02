package com.space.moon

import java.util.*

/**
 * use circle description : obstacle
 */
class MoonMapObList {
    var obList = ArrayList<MoonMapObstacle>()

    /**
     * init from path string, like : x,y,r;x1,y1,r1;
     */
    fun init(nodes: String) {
        var nodeList = nodes.split(";")
        nodeList
                .map { it.split(",") }
                .filter { it.size == 3 }
                .map { MoonMapObstacle(it[0].toFloat(), it[1].toFloat(), it[2].toFloat()) }
                .forEach { obList.add(it) }
    }
}