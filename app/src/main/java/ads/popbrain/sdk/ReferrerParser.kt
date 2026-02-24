package ads.popbrain.sdk

import ads.popbrain.sdk.model.AttributionResult

object ReferrerParser {

    fun parse(referrer: String?): AttributionResult {

        if (referrer.isNullOrEmpty()) {
            return AttributionResult(isOrganic = true)
        }

        val params = referrer.split("&")
            .mapNotNull {
                val pair = it.split("=")
                if (pair.size == 2) pair[0] to pair[1] else null
            }
            .toMap()

        val isAdInstall =
            params.containsKey("utm_source") ||
                    params.containsKey("utm_medium") ||
                    params.containsKey("gclid") ||
                    params.containsKey("fbclid")

        return AttributionResult(
            isOrganic = !isAdInstall,
            referrer = referrer,
            campaign = params["utm_campaign"]
        )
    }
}