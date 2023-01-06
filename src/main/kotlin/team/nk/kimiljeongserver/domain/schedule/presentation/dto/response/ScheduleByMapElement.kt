package team.nk.kimiljeongserver.domain.schedule.presentation.dto.response

import team.nk.kimiljeongserver.domain.schedule.domain.type.Color
import java.time.LocalDateTime

data class ScheduleByMapElement(
    val scheduleId: Int,
    val content: String,
    val address: String,
    val color: Color,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)