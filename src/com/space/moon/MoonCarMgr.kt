package com.space.moon

import java.util.*

/**
 * moon car manager
 */
class MoonCarMgr {
    /**
     * car array
     */
    val carArray = arrayOfNulls<MoonCar>(5)

    /**
     * interaval time : report to controlCenter
     */
    val reportIntervalTime: Long = 1000

    /**
     * report timer
     */
    var reportTimer: Timer? = Timer()

    /**
     * interaval time : report to controlCenter
     */
    val delayIntervalTime: Long = 2000

    /**
     * delay timer
     */
    var delayTimer: Timer? = Timer()

    /**
     * initial : simulate cars
     */
    fun init() {
        println("MoonCar simulate : start ...")
        println("simulate moon car ...")
        for (i in 0..4) {
            carArray[i] = MoonCar()
            carArray[i]!!.init(i, "")
        }

        reportTimer = Timer()
        delayTimer = Timer()

        reportTimer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                reportControlCenter()
            }
        }, 0, reportIntervalTime)

        println("MoonCar simulate : finish ...")
    }

    /**
     * report to control center : simulate delay
     */
    fun reportControlCenter() {
        for (car in carArray) {
            car!!.tick()
        }

        delayTimer!!.schedule(object : TimerTask() {
            override fun run() {
                report()
            }
        }, delayIntervalTime)
    }

    /**
     * report
     */
    fun report() {
        for (car in carArray) {
            car!!.report()
        }
    }
}