package kr.ac.kaist.itchy.util

import java.time.LocalDate
import java.time.LocalDateTime

data class playerData(
    val playername: String,
    val time: LocalDateTime,
    val score: Float,
    val duration: Float
)

