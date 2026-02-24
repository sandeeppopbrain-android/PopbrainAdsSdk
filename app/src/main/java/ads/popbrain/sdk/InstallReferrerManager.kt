package ads.popbrain.sdk

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import ads.popbrain.sdk.model.AttributionResult

object InstallReferrerManager {

    fun fetchReferrer(
        context: Context,
        callback: (AttributionResult) -> Unit
    ) {

        val client = InstallReferrerClient.newBuilder(context).build()

        client.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {

                if (responseCode ==
                    InstallReferrerClient.InstallReferrerResponse.OK
                ) {

                    val response = client.installReferrer
                    val referrer = response.installReferrer

                    val result = ReferrerParser.parse(referrer)

                    callback(result)

                    client.endConnection()
                }
            }

            override fun onInstallReferrerServiceDisconnected() {}
        })
    }
}