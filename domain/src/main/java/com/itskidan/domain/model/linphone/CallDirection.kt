package com.itskidan.domain.model.linphone

enum class CallDirection {
    INCOMING,
    OUTGOING,
    UNKNOWN;


    fun isIncoming(): Boolean = this == INCOMING
    fun isOutgoing(): Boolean = this == OUTGOING
}