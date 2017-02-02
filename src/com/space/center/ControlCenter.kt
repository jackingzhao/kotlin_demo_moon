package com.space.center

/**
 * implement : moon control center
 */
object ControlCenter {

    enum class Status {
        NONE, INIT_OK, QUIT
    }

    /**
     *  is quit control center
     */
    var status: Status? = null

    /**
     * car array
     */
    val carArray = arrayOfNulls<ControlCar>(5)

    fun init() {
        println("Moon Control Center initial : start ...")

        status = Status.NONE

        for (i in 0..4) {
            carArray[i] = ControlCar()
            carArray[i]!!.init(i)
        }

        status = Status.INIT_OK
        println("Moon Control Center initial : finish ...")
    }

}