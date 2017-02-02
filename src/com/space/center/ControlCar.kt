package com.space.center

import com.space.common.CarMoveInfo
import java.util.*

/**
 * control center 's car
 */
class ControlCar {
    enum class ControlCarStatus {
        NONE, CONNECT
    }

    /**
     * moon car index
     */
    var id: Int? = 0

    /**
     * car report info
     */
    var reportInfo = CarMoveInfo()

    /**
     * car report info
     */
    var tgtInfo = CarMoveInfo()


    /**
     * care forecast info
     */
    var forecastInfo = CarMoveInfo()

    /**
     * interval time : report to controlCenter
     */
    val printIntervalTime: Long = 500

    /**
     * print timer
     */
    var timer: Timer? = Timer()

    /**
     *  is quit control center
     */
    var status: ControlCarStatus? = null

    fun init(index: Int) {
        id = index

        println("control car $id initial : start ...")

        timer = Timer()
        timer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                forecast()
                printInfo()
            }
        }, 1000, printIntervalTime)

        println("control car $id initial : finish ...")

        status = ControlCar.ControlCarStatus.NONE
    }

    /**
     * print moon car's info
     */
    fun printInfo() {
        when (status) {
            ControlCar.ControlCarStatus.NONE -> {
            }
            ControlCar.ControlCarStatus.CONNECT -> {
                println("car$id report pos : (x=${reportInfo.x}, y=${reportInfo.y}, speed=${reportInfo.speed})")
                println("car$id forecast pos :(x=${forecastInfo.x}, y=${forecastInfo.y}, speed=${forecastInfo.speed})")
            }
        }
    }

    /**
     * forecast move path
     */
    fun forecast() {
        // easy to forecast, only compute we know delay time;
        // compute average speed: may better
        if (!reportInfo.isReach) {
            forecastInfo.x = reportInfo.x + reportInfo.vx * reportInfo.speed * 2
            forecastInfo.y = reportInfo.y + reportInfo.vy * reportInfo.speed * 2
            forecastInfo.speed = reportInfo.speed
        } else {
            forecastInfo.x = reportInfo.x
            forecastInfo.y = reportInfo.y
        }
    }

    fun rcvData(newPos: CarMoveInfo, tgtPos: CarMoveInfo) {
        reportInfo = newPos
        tgtInfo = tgtPos
        //println("car $id : rcv new pos info (${newPos.x}, ${tgtPos.x}, ${newPos.isReach})")
        //println("car $id : rcv new pos info (${newPos.y}, ${tgtPos.y}, ${newPos.isReach})")

        status = ControlCar.ControlCarStatus.CONNECT
    }
}