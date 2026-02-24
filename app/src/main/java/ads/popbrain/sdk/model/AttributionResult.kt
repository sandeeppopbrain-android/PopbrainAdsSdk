package ads.popbrain.sdk.model

data class AttributionResult(
    val isOrganic: Boolean,
    val referrer: String? = null,
    val campaign: String? = null
)