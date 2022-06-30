package com.canbus.app.featureUartSerial.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.canbus.app.ui.spacing
import com.canbus.app.utilities.logVerbose

@Composable
fun SpeedoMeterReading(targetValue1: Float, targetValue2: Float) {

    val initValue = targetValue1 * 100
    val initValue2 = targetValue2 * 100

    val arcDegrees = 300
    val startArcAngle = 120f
    val startStepAngle = -60
    val numberOfMarkers = 100
    val degreesMarkerStep = arcDegrees / numberOfMarkers

    val progress1: Float by animateFloatAsState(
        targetValue = initValue,
        tween(durationMillis = 500)
    )
    val primaryColor = MaterialTheme.colors.primary
    val secondaryColor = MaterialTheme.colors.secondary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(bottom = MaterialTheme.spacing.x2Dp)
            .background(Color.Green),
        onDraw = {
            drawIntoCanvas { canvas ->
            }
        })

//    Canvas(
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(1f)
//            .padding(bottom = MaterialTheme.spacing.x2Dp)
//            .background(Color.Green),
//        onDraw = {
//            drawIntoCanvas { canvas ->
//                val w = drawContext.size.width
//                val h = drawContext.size.height
//
//                val centerOffset = Offset(x = w / 2f, y = h / 2f)
//                val quarterOffset = Offset(x = w / 4f, y = h / 4f)
//
//                val paint = Paint().apply { color = primaryColor }
//                val centerArcSize = Size(w / 2f, h / 2f)
//                val centerArcStroke = Stroke(50f, 0f, StrokeCap.Round)
//
//                logVerbose("SpeedoMeter: progress = $progress1")
//                logVerbose("SpeedoMeter: Arc progress = ${(degreesMarkerStep * progress1).toFloat()}")
//
//                drawArc(
//                    color = secondaryColor,
//                    sweepAngle = arcDegrees.toFloat(),
//                    startAngle = startArcAngle,
//                    useCenter = false,
//                    topLeft = quarterOffset,
//                    size = centerArcSize,
//                    style = centerArcStroke
//                )
//
//                drawArc(
//                    color = primaryColor,
//                    startAngle = startArcAngle,
//                    sweepAngle = (degreesMarkerStep * progress1),
//                    useCenter = false,
//                    topLeft = quarterOffset,
//                    size = centerArcSize,
//                    style = centerArcStroke
//                )
//
//                drawCircle(
//                    color = primaryColor,
//                    center = centerOffset,
//                    radius = 50f
//                )
//
//                drawCircle(Color.White, 25f, centerOffset)
//                drawCircle(Color.Black, 20f, centerOffset)
//
//                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
//                    logVerbose("SpeedoMeter: counter = $counter, and degrees = $degrees")
//                    val lineEndX = 200f
//                    paint.color = primaryColor
//                    val lineStartX = if (counter % 5 == 0) {
//                        paint.strokeWidth = 3f
//                        0f
//                    } else {
//                        paint.strokeWidth = 1f
//                        lineEndX * .2f
//                    }
//                    canvas.save()
//                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
//                    canvas.drawLine(
//                        Offset(lineStartX, h / 2f),
//                        Offset(lineEndX, h / 2f),
//                        paint
//                    )
//
//                    if (counter == progress1.toInt()) {
//                        paint.color = Color.Red
//                        canvas.drawPath(
//                            Path().apply {
//                                moveTo(w / 2, (h / 2) - 5)
//                                lineTo(w / 2, (h / 2) + 5)
//                                lineTo(w / 4f, h / 2)
//                                lineTo(w / 2, (h / 2) - 5)
//                                close()
//                            },
//                            paint
//                        )
//                    }
//                    canvas.restore()
//                }
//
//            }
//        }
//    )
}