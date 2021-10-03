package it.matteocrippa.sensorsmultiplatform

data class AccelerometerData(val x: Double, val y: Double, val z: Double)
data class SensorData(
    val heading: Double,
    val sensor: AccelerometerData,
    val gravity: AccelerometerData? = null
)

expect class Sensors {
    val data: CommonFlow<SensorData?>
    val isEnabled: Boolean
    fun start()
    fun stop()
}