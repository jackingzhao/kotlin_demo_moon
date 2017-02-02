package com.space

import com.space.moon.*
import com.space.center.*

/*
    Entry point : main
    moon control center
 */
fun main(args: Array<String>) {
    println("control center welcome you !")

    MoonMap.init("map.txt")

    ControlCenter.init()

    val moonCarMgr = MoonCarMgr()
    moonCarMgr.init()

    while (true) {
        if (ControlCenter.status == ControlCenter.Status.QUIT) {
            break
        }
    }

    println("control center quit !")
}