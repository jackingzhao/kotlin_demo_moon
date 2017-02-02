package com.space.common


/**
 * car's move info
 */
class CarMoveInfo {
    /**
     * pos x
     */
    var x: Float = 0f

    /**
     * pos y
     */
    var y: Float = 0f

    /**
     * dir : angel
     */
    var dir: Float = 0f

    /**
     * speed
     */
    var speed: Float = 0f

    /**
     * use vector, easy to compute
     */
    var vx: Float = 0f

    /**
     * use vector, easy to compute
     */
    var vy: Float = 0f

    /**
     * is reach target
     */
    var isReach = false
}