package com.scenera.nicesecurityapplib.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import androidx.appcompat.app.AppCompatActivity;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    public PreferenceHelper pHelper;
    public byte[] sessionKey, ivBytes;
    public DownloadImage(AppCompatActivity activity) {
        pHelper = PreferenceHelper.getInstance(activity);
    }

    @Override
    protected Bitmap doInBackground(String... imageurls) {
        // This is done in a background thread
        AppLog.Log("doInBackground => ", "doInBackground");
        try {

            Bitmap myBitmap = null;

            URL url = new URL(imageurls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            /*************************************/
            Cipher cipher = null;

            sessionKey = Base64.decodeBase64(pHelper.getSceneEncryptionID().getBytes("UTF-8"));
            AppLog.Log("sessionKey => ", sessionKey + "");

            ivBytes = Base64.decodeBase64(pHelper.getIvBytes().getBytes("UTF-8"));
            AppLog.Log("ivBytes => ", ivBytes + "");

            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(sessionKey, "AES");

            cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
            InputStream input = connection.getInputStream();
            CipherInputStream cis = new CipherInputStream(input, cipher);
            /*************************************/
            // Convert the BufferedInputStream to a Bitmap
            myBitmap = BitmapFactory.decodeStream(cis);
            return myBitmap;

        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }

        return null;
    }
}
