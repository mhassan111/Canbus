package com.canbus.app.featureUartSerial.presentation

import com.canbus.app.featureUartSerial.domain.model.CanbusData

data class UartCommunicationState(
    val dataPacket: String = "...",
    val dataList: List<String> = emptyList(),
    val speed: CanbusData = CanbusData(Canbus.Speed.name, if (dataList.isEmpty()) "..." else "${dataList[0]} km/h"),
    val currentOfBattery: CanbusData = CanbusData(Canbus.CurrentOfBattery.name, if (dataList.isEmpty()) "..." else "${dataList[5]} Amp"),
    val socBatteryPack: CanbusData = CanbusData(Canbus.SocOfBatteryPack.name, if (dataList.isEmpty()) "..." else "${dataList[1]} %"),
    val motorRpm: CanbusData = CanbusData(Canbus.MotorRpm.name, if (dataList.isEmpty()) "..." else "${dataList[6]} Rpm"),
    val temperatureSensor1: CanbusData = CanbusData(Canbus.TemperatureSensor1.name, if (dataList.isEmpty()) "..." else "${dataList[2]} °C"),
    val temperatureSensor2: CanbusData = CanbusData(Canbus.TemperatureSensor2.name, if (dataList.isEmpty()) "..." else "${dataList[3]} °C"),
    val whPerKm: CanbusData = CanbusData(Canbus.WHPerKm.name, if (dataList.isEmpty()) "..." else "${dataList[4]} Wh/km"),
    val driverInput: CanbusData = CanbusData(Canbus.DriverInputThrottle.name,  if (dataList.isEmpty()) "..." else dataList[7]),
    val outputThrottle: CanbusData = CanbusData(Canbus.AllowedPowerOutput.name,  if (dataList.isEmpty()) "..." else dataList[8]),
    val spare1: CanbusData = CanbusData(Canbus.Spare1.name, if (dataList.isEmpty()) "..." else dataList[9]),
    val spare2: CanbusData = CanbusData(Canbus.Spare2.name, if (dataList.isEmpty()) "..." else dataList[10]),
    val spare3: CanbusData = CanbusData(Canbus.Spare3.name, if (dataList.isEmpty()) "..." else dataList[11]),
    val timeSinceBoot: CanbusData = CanbusData(Canbus.TimeSinceBoot.name, "00:45")
)

//    Data packet I will send:
//    @@@@V1/80/V2/80/V3/60/V4/80/V5/10/V6/100/V7/20/V8/123/V9/123/V10/123/####
//    V1 speed
//    V2 SOC
//    V3 TEMP1
//    V4 TEMP2
//    V5 WH PER KM
//    V6 CURRENT
//    V7 MOTOR RPM
//    V8 SPARE1
//    V9 SPARE2
//    V10 SPARE3
