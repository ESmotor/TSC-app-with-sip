package com.itskidan.domain.model

enum class CallDirection {
    INCOMING,
    OUTGOING,
    UNKNOWN;


    fun isIncoming(): Boolean = this == INCOMING
    fun isOutgoing(): Boolean = this == OUTGOING
}