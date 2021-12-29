package com.scenera.nicesecurityapplib.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptData extends AsyncTask<byte[], Void, byte[]> {
    Context context;
    public EncryptData(Context context){
        this.context = context;
    }

    @Override
    protected byte[] doInBackground(byte[]... binImage) {
        // This is done in a background thread
        AppLog.Log("doInBackground => ", "doInBackground");
        try {
            /*************************************/
            Cipher cipher = null;

            byte[] sessionKey = org.jose4j.base64url.internal.apache.commons.codec.binary.Base64.
                    decodeBase64(PreferenceHelper.getInstance(context).getSceneEncryptionID().getBytes("UTF-8"));
            AppLog.Log("sessionKey => ", sessionKey + "");
            //    ivEncrypted = pHelper.getIvBytes();
            byte[] ivBytes = org.jose4j.base64url.internal.apache.commons.codec.binary.Base64.
                    decodeBase64(PreferenceHelper.getInstance(context).getIvBytes().getBytes("UTF-8"));
            AppLog.Log("ivBytes => ", ivBytes + "");

            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(sessionKey, "AES");

            cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            return cipher.doFinal(binImage[0]);


        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }

        return null;
    }

    protected void onPostExecute(byte[] image) {
    }


}