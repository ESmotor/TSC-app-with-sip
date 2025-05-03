package com.itskidan.data.linphone

import com.itskidan.domain.model.CallStatus
import org.linphone.core.Call

fun Call.Status.toCallStatus(): CallStatus = when (this) {
    Call.Status.Success -> CallStatus.Success
    Call.Status.Aborted -> CallStatus.Aborted
    Call.Status.Missed -> CallStatus.Missed
    Call.Status.Declined -> CallStatus.Declined
    Call.Status.EarlyAborted -> CallStatus.EarlyAborted
    Call.Status.AcceptedElsewhere -> CallStatus.AcceptedElsewhere
    Call.Status.DeclinedElsewhere -> CallStatus.DeclinedElsewhere
}