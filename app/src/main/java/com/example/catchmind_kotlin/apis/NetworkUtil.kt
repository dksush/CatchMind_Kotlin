package com.example.catchmind_kotlin.apis

import okhttp3.Dns
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException
import com.google.gson.GsonBuilder
import com.google.gson.Gson



class NetworkUtil {




    companion object {


        var gson = GsonBuilder()
            .setLenient()
            .create()


        private var SAFE_DNS: Dns? = Dns { hostname ->
            try {
                return@Dns Dns.SYSTEM.lookup(hostname)
            } catch (e: IllegalArgumentException) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            } catch (e: NullPointerException) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            } catch (e: Exception) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            }
        }

        private var okHttpClient: OkHttpClient? = OkHttpClient.Builder()
            .dns(SAFE_DNS)
            //.addInterceptor(appInterceptor())
            .build()

        fun getRetrofit(baseUrl: String, factory: Converter.Factory?): Retrofit {

            if (okHttpClient == null) {
                if (SAFE_DNS == null) {
                    SAFE_DNS = Dns { hostname ->
                        try {
                            return@Dns Dns.SYSTEM.lookup(hostname)
                        } catch (e: IllegalArgumentException) {
                            // Hack. See https://github.com/square/okhttp/issues/3345
                            throw UnknownHostException(e.message)
                        } catch (e: NullPointerException) {
                            // Hack. See https://github.com/square/okhttp/issues/3345
                            throw UnknownHostException(e.message)
                        } catch (e: Exception) {
                            // Hack. See https://github.com/square/okhttp/issues/3345
                            throw UnknownHostException(e.message)
                        }
                    }
                }

                okHttpClient = OkHttpClient.Builder()
                    //                    .addInterceptor(new LoggingInterceptor())
                    .dns(SAFE_DNS)
                    //.addInterceptor(appInterceptor())
                    .build()
            }

            var retrofit: Retrofit

            try {
                if (factory == null) {
                    retrofit = Retrofit.Builder().client(okHttpClient)
                        .baseUrl(baseUrl)
                        .build()
                } else {
                    retrofit = Retrofit.Builder().client(okHttpClient)
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (factory == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .build()
                } else {
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
            }

            return retrofit
        }


        // 인터셉터.
        class appInterceptor : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val mRequest = chain.request()
                val newmequest = mRequest.newBuilder()
                    .addHeader("키", "밸류")
                    .build()

                return chain.proceed(newmequest)
            }
        }
    }





}