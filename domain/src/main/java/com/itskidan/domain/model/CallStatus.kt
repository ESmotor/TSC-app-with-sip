package com.itskidan.domain.model

enum class CallStatus(val code: Int) {
    Success(0),
    Aborted(1),
    Missed(2),
    Declined(3),
    EarlyAborted(4),
    AcceptedElsewhere(5),
    DeclinedElsewhere(6);

    companion object {
        private val map = CallStatus.entries.associateBy(CallStatus::code)

        fun fromCode(code: Int): CallStatus {
            return map[code] ?: throw IllegalArgumentException("Unknown CallResult code: $code")
        }
    }
    fun getName(): String = name
}
