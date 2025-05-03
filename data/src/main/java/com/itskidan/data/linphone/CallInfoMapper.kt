package com.itskidan.data.linphone

import com.itskidan.domain.model.CallDirection
import com.itskidan.domain.model.CallInfo
import org.linphone.core.Call

fun Call.toCallInfo(): CallInfo {
    return CallInfo(
        callId = this.callLog.callId,
        remoteName = this.remoteAddress.displayName,
        callDirection = getCallDir(this),
        isMuted = this.microphoneMuted,
        callStatus = this.callLog.status.toCallStatus()
    )
}

private fun getCallDir(call: Call): CallDirection {
    return when (call.dir) {
        Call.Dir.Outgoing -> CallDirection.OUTGOING
        Call.Dir.Incoming -> CallDirection.INCOMING
        else -> CallDirection.UNKNOWN
    }
}