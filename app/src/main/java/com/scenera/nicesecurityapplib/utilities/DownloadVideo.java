package com.scenera.nicesecurityapplib.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;

import com.scenera.nicesecurityapplib.BaseActivity;

import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DownloadVideo  {
    boolean  fWaitForDownloadFromInternetToComplete = false;
    public PreferenceHelper pHelper;
    public byte[] sessionKey, ivBytes;
    public BaseActivity activity;
    public String vidoeURI ;
    public DownloadVideo(BaseActivity activity) {
        this.activity = activity;
        pHelper = PreferenceHelper.getInstance(activity);
    }
    /****************************** DOWNLOAD AND PLAY************************************/
    public void playVideo(String strUrl,String strFilename)
    {

        fWaitForDownloadFromInternetToComplete = true;
        new DownloadFromInternet(strUrl, strFilename).execute();
        while (fWaitForDownloadFromInternetToComplete)
        {
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {}
        }


        String strFileDirectory = String.valueOf(activity.getFilesDir());
        String strPathFilename = strFileDirectory + "/" + strFilename;
        vidoeURI = strPathFilename;
        AppLog.Log("VideoURIPlayVideo==>","PlayVideo");


    }
    private class DownloadFromInternet extends AsyncTask<URL, Void, String> {
        String strUrl;
        String strFilename;

        public DownloadFromInternet(String _strFileUrl, String _strFileName) {
            this.strUrl = _strFileUrl;
            this.strFilename = _strFileName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(!pHelper.getVideoLoaded()) {
                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        AppLog.Log("onFinish", "onFinish");
                        Utils.showCustomProgressDialog(activity, "", false);
                    }
                }.start();
            }

        }

        protected String doInBackground(URL... params)
        {
            try {
                int connectTimeout = 10000;
                int readTimeout = 10000;

                URL url = new URL(strUrl);
                URLConnection con = url.openConnection();
                con.setConnectTimeout(connectTimeout);
                con.setReadTimeout(readTimeout);
                InputStream in = con.getInputStream();

                byte[] abBuffer = toByteArray(in);
                int iOffset = 0;
                int iLength = abBuffer.length;
                SaveDataOnDisk(strFilename, abBuffer, iOffset, iLength);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fWaitForDownloadFromInternetToComplete = false;
            return("Success");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Utils.removeCustomProgressDialog();
        }

    }
    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1000000];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }
    private void SaveDataOnDisk(String filename, byte[] abBuffer, int iOffset, int iLength) {
        try {
            FileOutputStream outputStream;

            if (pHelper.getSceneEncryptionID() != null && pHelper.getIvBytes() != null
                    && pHelper.getKid() != null) {

                sessionKey = Base64.decodeBase64(pHelper.getSceneEncryptionID().getBytes("UTF-8"));

                ivBytes = Base64.decodeBase64(pHelper.getIvBytes().getBytes("UTF-8"));

                Cipher cipher = null;

                AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                SecretKeySpec newKey = new SecretKeySpec(sessionKey, "AES");

                cipher = Cipher.getInstance("AES/CTR/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
                byte[] decypted = cipher.doFinal(abBuffer);

                outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(decypted, iOffset, iLength);
                outputStream.close();
            }else{
                try {
                    outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(abBuffer, iOffset, iLength);
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
