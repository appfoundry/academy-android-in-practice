package be.appfoundry.aipdemo.core

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import org.apache.http.conn.ssl.SSLSocketFactory.SSL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    /**
     * This [ImageLoader] uses a custom insecure [OkHttpClient]
     * and should not be used in production code whatsoever.
     */
    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(this)
            .okHttpClient {
                val trustAllCerts = arrayOf(
                    object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
                    }
                )
                val sslContext = SSLContext.getInstance(SSL).apply {
                    init(null, trustAllCerts, SecureRandom())
                }
                OkHttpClient.Builder()
                    .sslSocketFactory(
                        sslContext.socketFactory,
                        trustAllCerts.first()
                    )
                    .hostnameVerifier { _, _ -> true }
                    .build()
            }
            .build()
}
