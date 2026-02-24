package ads.popbrain.sdk

import android.content.Context

object PopbrainAdsSDK {
    fun initialize(context: Context) {
        InstallReferrerManager.fetchReferrer(context) { result ->
            ConversionTracker.track(result)
        }
    }
}