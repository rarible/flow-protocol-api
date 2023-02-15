package com.rarible.protocol.dto

import java.time.Instant

fun blockchainEventMark(markName: String, seconds: Long): FlowEventTimeMarksDto {
    return blockchainEventMark(markName, Instant.ofEpochSecond(seconds))
}

fun blockchainEventMark(markName: String, date: Instant): FlowEventTimeMarksDto {
    return FlowEventTimeMarksDto("blockchain")
        .add("source", date)
        .add(markName, Instant.now())
}

fun integrationEventMark(markName: String, date: Instant): FlowEventTimeMarksDto {
    return FlowEventTimeMarksDto("integration")
        .add("source", date)
        .add(markName, Instant.now())
}

fun offchainEventMark(markName: String): FlowEventTimeMarksDto {
    return FlowEventTimeMarksDto("offchain").add(markName, null)
}

fun FlowEventTimeMarksDto.add(name: String, date: Instant? = null): FlowEventTimeMarksDto {
    val mark = FlowEventTimeMarkDto(name, date ?: Instant.now())
    val marks = this.marks.toMutableList()
    marks.add(mark)
    return this.copy(marks = marks)
}

