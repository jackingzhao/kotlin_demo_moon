package com.space.net

import com.space.center.*
import com.space.moon.*

/**
 * network : between moon and control center
 */
object Network {
    fun sndCarInfo(moonCar: MoonCar) {
        var id: Int? = moonCar.id
        if (id != null) {
            var car: ControlCar? = ControlCenter.carArray[id]
            var newPos = moonCar.moveInfo
            var tgtPos = moonCar.tgtInfo
            if (car != null) {
                car.rcvData(newPos, tgtPos)
            }
        }
    }
}