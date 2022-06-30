package com.canbus.app.featureUartSerial.presentation

sealed class Canbus(val name : String) {
    object Speed : Canbus("Speed")
    object CurrentOfBattery : Canbus("Current of Battery")
    object SocOfBatteryPack : Canbus("Soc of battery pack")
    object MotorRpm : Canbus("Motor rpm")
    object TemperatureSensor1 : Canbus("Temp Sensor 1 bms")
    object TemperatureSensor2 : Canbus("Temp Sensor 2 bms")
    object WHPerKm : Canbus("Wh per km")
    object Spare1 : Canbus("Spare1")
    object Spare2 : Canbus("Spare2")
    object Spare3 : Canbus("Spare3")
    object DriverInputThrottle : Canbus("Driver throttle input")
    object AllowedPowerOutput : Canbus("Allowed power output")
    object TimeSinceBoot : Canbus("Time Since Boot")
}