package it.matteocrippa.sensorsmultiplatform

import kotlinx.cinterop.CValue
import kotlinx.cinterop.useContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import platform.CoreMotion.CMAcceleration
import platform.CoreMotion.CMAttitudeReferenceFrameXMagneticNorthZVertical
import platform.CoreMotion.CMDeviceMotion
import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSOperationQueue

actual class Sensors {
    private var _data = Channel<SensorData?>(Channel.BUFFERED)

    actual val data: CommonFlow<SensorData?>
        get() = _data.consumeAsFlow().asCommonFlow()
    private var _isEnabled = false
    actual val isEnabled: Boolean
        get() = _isEnabled

    private val scope = CoroutineScope(UIDispatcher())
    private val queue = NSOperationQueue.mainQueue
    private val manager = CMMotionManager().apply {
        deviceMotionUpdateInterval = 20 / 1000.0
    }

    actual fun start() {
        _isEnabled = true
        manager.startDeviceMotionUpdatesUsingReferenceFrame(
            CMAttitudeReferenceFrameXMagneticNorthZVertical, queue
        ) { motion, error ->
            if (error != null) {
                return@startDeviceMotionUpdatesUsingReferenceFrame
            }
            motion?.let {
                scope.launch {
                    val converted = convertIntoSensorData(it)
                    _data.send(converted)
                }
            }
        }
    }

    actual fun stop() {
        _isEnabled = false
        manager.stopDeviceMotionUpdates()
    }

    private fun convertIntoSensorData(motion: CMDeviceMotion?): SensorData? {
        if (motion == null) return null
        val user = motion.userAcceleration
        val gravity = motion.gravity
        val heading = motion.heading
        return SensorData(heading, convertAcceleration(user), convertAcceleration(gravity))
    }

    private fun convertAcceleration(acceleration: CValue<CMAcceleration>): AccelerometerData {
        acceleration.useContents {
            return AccelerometerData(this.x, this.y, this.z)
        }
    }
}