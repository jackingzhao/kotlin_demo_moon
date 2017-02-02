package com.space.moon

import com.space.common.CarMoveInfo
import com.space.net.Network
import java.lang.Math.sqrt

/**
 * moon car
 */
class MoonCar {

    /**
     * moon car index
     */
    var id = 0

    /**
     * moon car index
     */
    var path: String? = null


    /**
     * car report info
     */
    var moveInfo = CarMoveInfo()

    /**
     * car tgt info
     */
    var tgtInfo = CarMoveInfo()

    /**
     * moon car index
     */
    var speed = 0f

    /**
     * moon car index
     */
    var pos = 0

    /**
     * moon car index
     */
    var lastTime: Long = 0

    /**
     * intial moon car
     */
    fun init(index: Int, line: String) {
        id = index
        path = line

        // init birth position; and targt
        speed = MoonMap.getSpeed(index)

        pos = 0

        setPos(id, pos)

        lastTime = System.currentTimeMillis()
    }

    fun setPos(index: Int, pos: Int) {
        //println("setPos index = $index pos = $pos")
        var x = MoonMap.getX(index, pos)
        var y = MoonMap.getY(index, pos)
        if (x < 0 || y < 0) {
            return
        }
        moveInfo.x = x
        moveInfo.y = y
        moveInfo.speed = speed

        // init target postion
        var tx = MoonMap.getX(index, pos + 1)
        var ty = MoonMap.getY(index, pos + 1)
        if (tx < 0 || ty < 0) {
            return
        }
        tgtInfo.x = tx
        tgtInfo.y = ty
        tgtInfo.speed = speed

        var temp = (tx - x) * (tx - x) + (ty - y) * (ty - y)
        var d = sqrt(temp.toDouble())
        moveInfo.vx = ((tx - x) / d).toFloat()
        moveInfo.vy = ((ty - y) / d).toFloat()
    }

    fun report() {
        //println("car $id : report new pos")
        Network.sndCarInfo(this)
    }

    fun tick() {
        // move to target
        // emit ray to target, if intersect, then move to obstacle, around th circle to other intersect point,
        // final move to target
        // if point in obstacle, the path is wrong.

        var now = System.currentTimeMillis()
        var deltaTime = (now - lastTime) / 1000f

        lastTime = now

        // if reach, need change target
        if (isReach()) {
            if (pos >= MoonMap.pathList.size) {
                moveInfo.x = tgtInfo.x
                moveInfo.y = tgtInfo.y
                moveInfo.speed = tgtInfo.speed
                moveInfo.isReach = true
                tgtInfo.isReach = true
            } else {
                pos += 1
                setPos(id, pos)
            }

        } else {
            moveInfo.x = moveInfo.x + moveInfo.vx * speed * deltaTime
            moveInfo.y = moveInfo.y + moveInfo.vy * speed * deltaTime
            //println("tick ${moveInfo.vx} : $speed  $deltaTime")
        }
    }

    fun isReach(): Boolean {
        var x = moveInfo.x
        var y = moveInfo.y

        var tx = tgtInfo.x
        var ty = tgtInfo.y

        var temp = (tx - x) * (tx - x) + (ty - y) * (ty - y)

        //println("isReach $temp")
        if (temp < 10) {
            return true
        }

        return false
    }


}