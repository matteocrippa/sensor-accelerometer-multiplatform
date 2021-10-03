# Kotlin Mobile Multiplatform Accelerometer Sensors Library

This library allows you to get a `Flow` from your Android or iOS (only on _main thread_) data from accelerometers.

Application exposes a custom model `SensorData` that exposes:

- heading calculated in degrees
- sensor as `AccelerometerData`
- gravity as optional `AccelerometerData`

```kotlin
data class SensorData(
    val heading: Double,
    val sensor: AccelerometerData,
    val gravity: AccelerometerData? = null
)
```

Each `AccelerometerData` has this structure:

```kotlin
data class AccelerometerData(val x: Double, val y: Double, val z: Double)
```

## Usage

For usage you can refer to the two examples you can find in `iOSApp` and `AndroidApp`.
