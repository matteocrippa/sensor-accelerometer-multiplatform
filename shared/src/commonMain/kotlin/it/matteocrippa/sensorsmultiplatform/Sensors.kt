package it.matteocrippa.sensorsmultiplatform

interface AccelerometerInterface {
    val x: Double
    val y: Double
    val z: Double
}

data class AccelerometerData(
    override val x: Double,
    override val y: Double,
    override val z: Double
) : AccelerometerInterface


interface SensorDataInterface {
    val heading: Double
    val sensor: AccelerometerInterface
    val gravity: AccelerometerInterface?
}

data class SensorData(
    override val heading: Double,
    override val sensor: AccelerometerInterface,
    override val gravity: AccelerometerInterface? = null
) : SensorDataInterface

interface SensorInterface {
    val data: CommonFlow<SensorDataInterface?>
    val isEnabled: Boolean
    fun start()
    fun stop()
}

expect class Sensors : SensorInterface {
    override val data: CommonFlow<SensorDataInterface?>
    override val isEnabled: Boolean
    override fun start()
    override fun stop()
}