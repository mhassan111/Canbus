package com.canbus.app.featureUartSerial.presentation

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.canbus.app.ui.spacing
import com.canbus.app.ui.theme.CanbusAppTheme
import com.canbus.app.utilities.logVerbose
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UartSerialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanbusAppTheme {
                UartCommunicationApp()
            }
        }
    }
}

@Composable
fun UartCommunicationApp() {
    Surface {
        UartCommunicationScreen()
    }
}

val textPaint = Paint().asFrameworkPaint().apply {
    isAntiAlias = true
    textSize = 24f
    color = android.graphics.Color.BLACK
    typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
}

@Composable
fun GooglePhotos() {
    Canvas(modifier = Modifier.size(100.dp)) {
        drawArc(
            color = Color(0xFFf04231),
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .25f, 0f)
        )

        drawArc(
            color = Color(0xFF4385f7),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .50f, size.height * .25f)
        )

        drawArc(
            color = Color(0xFF30a952),
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(0f, size.height * .25f)
        )

        drawArc(
            color = Color(0xFFffbf00),
            startAngle = 270f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .25f, size.height * .50f)
        )

//        drawRoundRect(
//            brush = Brush.linearGradient(instaColors),
//            cornerRadius = CornerRadius(60f, 60f),
//            style = Stroke(width = 15f, cap = StrokeCap.Round)
//        )
//        drawCircle(
//            brush = Brush.linearGradient(colors = instaColors),
//            radius = 45f,
//            style = Stroke(width = 15f, cap = StrokeCap.Round)
//        )
//        drawCircle(
//            brush = Brush.linearGradient(colors = instaColors),
//            radius = 13f,
//            center = Offset(this.size.width * .80f, this.size.height * 0.20f),
//        )
    }

}

@Composable
fun InstagramIcon() {
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)
    Canvas(modifier = Modifier.size(100.dp)) {

        drawArc(
            color = Color(0xFFf04231),
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .25f, 0f)
        )

        drawArc(
            color = Color(0xFF4385f7),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .50f, size.height * .25f)
        )

        drawArc(
            color = Color(0xFF30a952),
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(0f, size.height * .25f)
        )

        drawArc(
            color = Color(0xFFffbf00),
            startAngle = 270f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width * .50f, size.height * .50f),
            topLeft = Offset(size.width * .25f, size.height * .50f)
        )

    }
}

@Composable
fun PracticeCanvas(progress: Int = 20) {

    val arcDegrees = 300
    val startArcAngle = 120f
    val startStepAngle = -60
    val numberOfMarkers = 100
    val degreesMarkerStep = arcDegrees / numberOfMarkers

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(bottom = MaterialTheme.spacing.x2Dp)
            .background(Color.Green),
        onDraw = {

            drawIntoCanvas { canvas ->
                val w = drawContext.size.width
                val h = drawContext.size.height
                val centerOffset = Offset(w / 2f, h / 2f)
                val quarterOffset = Offset(w / 4f, h / 4f)

                val (mainColor, secondaryColor) = Color(0xFF388E3C) to Color(0xFFC8E6C9)
                val paint = Paint().apply {
                    color = mainColor
                }

                val centerArcSize = Size(w / 2f, h / 2f)
                val centerArcStroke = Stroke(30f, 0f, StrokeCap.Round)

                drawArc(
                    color = secondaryColor,
                    startAngle = startArcAngle,
                    sweepAngle = arcDegrees.toFloat(),
                    useCenter = false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawArc(
                    color = Color.Red,
                    startAngle = startArcAngle,
                    sweepAngle = (degreesMarkerStep * progress).toFloat(),
                    useCenter = false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawCircle(mainColor, 60f, centerOffset)
                drawCircle(Color.White, 30f, centerOffset)
                drawCircle(Color.Black.copy(alpha = 0.8f), 25f, centerOffset)

                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    logVerbose("SpeedoMeter: counter = $counter, and degrees = $degrees")

                    val lineEndX = 80f
                    paint.color = mainColor
                    val lineStartX = if (counter % 5 == 0) {
                        paint.strokeWidth = 3f
                        0f
                    } else {
                        paint.strokeWidth = 1f
                        lineEndX * .2f
                    }
                    canvas.save()
                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
                    canvas.drawLine(
                        Offset(lineStartX, h / 2f),
                        Offset(lineEndX, h / 2f),
                        paint
                    )

                    if (counter % 5 == 0) {
                        canvas.nativeCanvas.drawText(
                            "$counter",
                            lineStartX + 100f,
                            h / 2f,
                            textPaint
                        )
                    }
                    canvas.restore()

                }
            }
        })

}

@Composable
fun CanSpeedoMeter(
    progress: Int = 0,
    progress2: Int = 0
) {

    val arcDegrees = 300
    val startArcAngle = 120f
    val startStepAngle = -60
    val numberOfMarkers = 100
    val degreesMarkerStep = arcDegrees / numberOfMarkers

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .aspectRatio(1f),
        onDraw = {
            drawIntoCanvas { canvas ->

                logVerbose("SpeedoMeter: progress = $progress")
                logVerbose("SpeedoMeter: Arc progress = ${(degreesMarkerStep * progress).toFloat()}")

                val w = drawContext.size.width
                val h = drawContext.size.height
                val centerOffset = Offset(w / 2f, h / 2f)
                val quarterOffset = Offset(w / 4f, h / 4f)

//                val (mainColor, secondaryColor) = when {
//                    progress < 20 -> Color(0xFFD32F2F) to Color(0xFFFFCDD2)
//                    progress < 40 -> Color(0xFFF57C00) to Color(0xFFFFE0B2)
//                    else -> Color(0xFF388E3C) to Color(0xFFC8E6C9)
//                }
//
                val (mainColor, secondaryColor) = Color(0xFF388E3C) to Color(0xFFC8E6C9)
                val paint = Paint().apply {
                    color = mainColor
                }
                val centerArcSize = Size(w / 2f, h / 2f)
                val centerArcStroke = Stroke(30f, 0f, StrokeCap.Round)

                drawArc(
                    color = secondaryColor,
                    startAngle = startArcAngle,
                    sweepAngle = arcDegrees.toFloat(),
                    useCenter = false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawArc(
                    color = Color.Green.copy(alpha = 0.5f),
                    startAngle = startArcAngle,
                    sweepAngle = (degreesMarkerStep * progress).toFloat(),
                    useCenter = false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawArc(
                    color = Color.Red.copy(alpha = 0.5f),
                    startAngle = startArcAngle,
                    sweepAngle = (degreesMarkerStep * progress2).toFloat(),
                    useCenter = false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawCircle(mainColor, 60f, centerOffset)
                drawCircle(Color.White, 30f, centerOffset)
                drawCircle(Color.Black.copy(alpha = 0.8f), 25f, centerOffset)

                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    logVerbose("SpeedoMeter: counter = $counter, and degrees = $degrees")
                    val lineEndX = 80f
                    paint.color = mainColor
                    val lineStartX = if (counter % 5 == 0) {
                        paint.strokeWidth = 3f
                        0f
                    } else {
                        paint.strokeWidth = 1f
                        lineEndX * .2f
                    }
                    canvas.save()

                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
                    canvas.drawLine(
                        Offset(lineStartX, h / 2f),
                        Offset(lineEndX, h / 2f),
                        paint
                    )

                    if (counter % 5 == 0) {
                        canvas.nativeCanvas.drawText(
                            "$counter",
                            lineStartX + 100f,
                            h / 2f,
                            textPaint
                        )
                    }

                    if (counter == progress) {
                        paint.color = Color.Green
                        canvas.drawPath(
                            Path().apply {
                                moveTo(w / 2, (h / 2) - 20)
                                lineTo(w / 2, (h / 2) + 20)
                                lineTo(w / 4f, h / 2)
                                close()
                            },
                            paint
                        )
                    }
                    canvas.restore()
                }

                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    logVerbose("SpeedoMeter: counter = $counter, and degrees = $degrees")
                    val lineEndX = 80f
                    paint.color = mainColor
                    val lineStartX = if (counter % 5 == 0) {
                        paint.strokeWidth = 3f
                        0f
                    } else {
                        paint.strokeWidth = 1f
                        lineEndX * .2f
                    }
                    canvas.save()
                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
                    canvas.drawLine(
                        Offset(lineStartX, h / 2f),
                        Offset(lineEndX, h / 2f),
                        paint
                    )

                    if (counter == progress2) {
                        paint.color = Color.Red
                        canvas.drawPath(
                            Path().apply {
                                moveTo(w / 2, (h / 2) - 20)
                                lineTo(w / 2, (h / 2) + 20)
                                lineTo(w / 4f, h / 2)
//                                lineTo(w / 2, (h / 2) - 20)
                                close()
                            },
                            paint
                        )
                    }
                    canvas.restore()
                }

            }
        }
    )
}

