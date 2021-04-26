package com.scenera.nicesecurityapplib.retrofit;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.scenera.nicesecurityapplib.R;
import com.scenera.nicesecurityapplib.utilities.AppLog;

import org.json.JSONObject;

import java.io.File;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */

public class ApiClient {
    public static final String Tag = "ApiClient";
    private static Retrofit retrofit = null;
    public static MediaType MEDIA_TYPE_IMAGE = MediaType.parse("placeholder/*");
    private static MediaType MEDIA_TYPE_TEXT = MediaType.parse("multipart/form-data");
    private static Gson gson;
    private static AppCompatActivity context;

    public static Retrofit getClient(AppCompatActivity activity, String getSceneMarkUrl) {

        context = activity;
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(getSceneMarkUrl)
                        .client(getUnsafeOkHttpClient(activity))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create());

        retrofit = builder.build();

        return retrofit;
    }
    private static OkHttpClient getUnsafeOkHttpClient(AppCompatActivity activity) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            /*****************************************************************************/
            File httpCacheDirectory = new File(activity.getCacheDir(), "cache_file");

            Cache cache = new Cache(httpCacheDirectory, 20 * 1024 * 1024);

            /*****************************************************************************/
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            HttpLoggingInterceptor interceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY);


            builder.addInterceptor(interceptor);

            builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES)
            ;

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Retrofit getClientAccount(AppCompatActivity activity, String accountURL) {


        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);


        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(accountURL)
                        .client(getUnsafeOkHttpClient(activity))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create());

        retrofit = builder.build();

        return retrofit;
    }

    @NonNull
    public static RequestBody makeJSONRequestBody(JSONObject jsonObject) {
        String params = jsonObject.toString();
        return RequestBody.create(MEDIA_TYPE_TEXT, params);
    }

    @NonNull
    public static RequestBody makeTextRequestBody(Object stringData) {
        return RequestBody.create(MEDIA_TYPE_TEXT, String.valueOf(stringData));
    }

    @NonNull
    public static MultipartBody.Part makeMultipartRequestBody(Context context, String
            photoPath, String partName) {
        try {
            File file = new File(photoPath);
            RequestBody requestFile = RequestBody.create(MEDIA_TYPE_IMAGE, file);
            return MultipartBody.Part.createFormData(partName, context.getResources().getString(R
                            .string
                            .app_name),
                    requestFile);
        } catch (NullPointerException e) {
            AppLog.handleException(Tag, e);
            return null;
        }

    }


    @NonNull
    public static String JSONResponse(Object jsonObject) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(jsonObject);
    }
}
