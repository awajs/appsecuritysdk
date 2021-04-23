package com.scenera.nicesecurityapp.utilities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.nimbusds.jose.util.X509CertUtils;
import com.scenera.nicesecurityapp.Encryption.EcdhDecrypt;
import com.scenera.nicesecurityapp.R;
import com.scenera.nicesecurityapp.models.data.AppControlHeader;
import com.scenera.nicesecurityapp.models.response.AppConrolObjectResponse;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.lang.JoseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.util.encoders.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */
public class Utils {
    private static Dialog mDialog;
    private static String uniqueID = null;
    private final static String JWE_SECTION_DELIMITER = ".";

    public static boolean isNetworkAvailable(AppCompatActivity activity) {
        ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        }
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info == null) {
            return false;
        }
        for (NetworkInfo state : info) {
            if (state.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }


    public static void removeCustomProgressDialog(){
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        }
    }
    public static void showAlert(Context context, String message){
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getResources().getString(R.string.text_error_try_again));
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false); // This blocks the 'BACK' button
        alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void showCustomProgressDialog(Context context, String message, boolean isCancelable){
        if (mDialog == null || !mDialog.isShowing()) {
            mDialog = new Dialog(context, R.style.CustomProgressDialog);
            mDialog.setCancelable(isCancelable);
            mDialog.requestWindowFeature(1);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.colorProgressBg)));
            mDialog.setContentView(R.layout.progressbar);
            if (!((AppCompatActivity) context).isFinishing()) {
                mDialog.show();
            }
        }
    }

    public static void setViewGoneByAnimation(Context context, final View view){
        view.setVisibility(View.GONE);
        final Animation anim= AnimationUtils.loadAnimation
                (context, R.anim
                        .flot_slide_in_top);
        view.startAnimation(anim);
    }
    public static void setViewVisibleByAnimation(Context context, final View view){
        view.setVisibility(View.VISIBLE);
        final Animation anim = AnimationUtils.loadAnimation
                (context, R.anim
                        .flot_slide_out_top);
        view.startAnimation(anim);
    }

    public static JSONArray convertListToJsonArray(List<String> stringList) {
        JSONArray jsonArray = new JSONArray();
        for (String data : stringList) {
            jsonArray.put(data);
        }
        return jsonArray;
    }
    public static String base64Encode(byte[] b) {
        try {
            return new String(Base64.encode(b), "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public static String jsonEncodeToString(String jsonStringToEncode){
        Gson gson = new Gson();
        AppLog.Log("JsonString==> ",gson.toJson(jsonStringToEncode));
        return gson.toJson(jsonStringToEncode);
    }
    public static void showSelectCameraDialog(Context context, String message){
        android.app.AlertDialog NoSceneMarksErrorMessage = new android.app.AlertDialog.Builder(context).create();
        NoSceneMarksErrorMessage.setTitle(context.getResources().getString(R.string.text_error_try_again));
        NoSceneMarksErrorMessage.setMessage(message);
       // NoSceneMarksErrorMessage.setMessage(context.getString(R.string.text_error_select_camera));
        NoSceneMarksErrorMessage.setCancelable(false); // This blocks the 'BACK' button
        NoSceneMarksErrorMessage.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        NoSceneMarksErrorMessage.show();
    }
    public static long calculateTimeDiff(String dateStart, String dateStop) {
        long diffSeconds = 0, diffMinutes = 0, diffHours = 0, diffDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");



        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            long diff = d1.getTime() - d2.getTime();

            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffHours;
    }

    public static String encryptAndSignCMF(Context context, JSONObject jsonObjectCMFHeader, String payLoadToEncrypt){
        String jwe = "";

        AppLog.Log("payload", " ****"+payLoadToEncrypt);
        try {
            JsonArray x5cArray = new JsonArray();
            List<com.nimbusds.jose.util.Base64> x5cList = new ArrayList<>();
            JsonObject jsonObjectHeader = new JsonObject();
            Key publicKeyRSA = getPublicKey(PreferenceHelper.getInstance(context).getPublicKeyRSA());

            AppLog.Log("getPublicKeyRSA", " ****"+PreferenceHelper.getInstance(context).getPublicKeyRSA());
            PrivateKey privateKeyRSA = getPrivateKey(PreferenceHelper.getInstance(context).getPrivateKeyRSA());

            String publicKeyCertEncoded = java.util.Base64.getEncoder().encodeToString(publicKeyRSA.getEncoded());
            X509Certificate certJWE = X509CertUtils.parse(Constants.NICE_AS_X509_CERTIFICATE);
            jsonObjectHeader.addProperty("alg", KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            jsonObjectHeader.addProperty("enc", ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
            jsonObjectHeader.addProperty("kid", Constants.KID);
            if (publicKeyRSA != null) {

                JsonPrimitive encodedX509JsonPrimitive = new JsonPrimitive(publicKeyCertEncoded);
                x5cArray.add(encodedX509JsonPrimitive);
                x5cList.add(com.nimbusds.jose.util.Base64.encode(publicKeyRSA.getEncoded()));
                jsonObjectHeader.add("x5c", x5cArray);
            }

            String header = jsonObjectHeader.toString();
            String headerPortion = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256, new SecureRandom());
            SecretKey contentEncryptionKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256", "MGF1",
                    MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            cipher.init(Cipher.ENCRYPT_MODE, certJWE.getPublicKey(), oaepSpec);
            byte[] wrappedKey = cipher.doFinal(contentEncryptionKey.getEncoded());
            AppLog.Log("ENCRYPTED_AES_KEY", java.util.Base64.getUrlEncoder().encodeToString(wrappedKey));
            final String wrappedKeyPortion = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(wrappedKey);

            // generateIv
            final SecureRandom secureRandom = new SecureRandom();
            final byte[] iv = new byte[12];
            secureRandom.nextBytes(iv);
            final String ivPortion = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(iv);

            final EncryptedPayloadAndTag encryptedOutput = encryptDataWithBC(contentEncryptionKey, iv, payLoadToEncrypt.getBytes(),
                    headerPortion);
            final String encryptedPayloadPortion = java.util.Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(encryptedOutput.getEncryptedPayload());
            final String tagPortion = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedOutput.getTag());


            final StringJoiner stringJoiner = new StringJoiner(JWE_SECTION_DELIMITER);
            jwe = stringJoiner.add(headerPortion).add(wrappedKeyPortion).add(ivPortion)
                    .add(encryptedPayloadPortion).add(tagPortion).toString();

            jsonObjectCMFHeader.put(Constants.CMF.Payload.PAYLOAD , jwe );

            return sign(privateKeyRSA, x5cList, jsonObjectCMFHeader.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return jwe;
    }

    public static String decryptAndValidateCMF(Context context, String jws){
        AppConrolObjectResponse appConrolObjectResponse = null;
        try {
            String privateKey = PreferenceHelper.getInstance(context).getPrivateKeyRSA();

            jws = jws.replace("\"", "");
            AppControlHeader appControlHeader = SplitAndDecrypt(jws);

            /*********** Decrypt Encrypted Payload **************/
            EcdhDecrypt ecdhDecrypt = new EcdhDecrypt();

            appConrolObjectResponse = ecdhDecrypt.getAppControlObject(jws,privateKey,appControlHeader);
            AppLog.Log("appConrolObjectResponse","****"+new Gson().toJson(appConrolObjectResponse));

        }catch (Exception e){
            e.printStackTrace();
        }

        return new Gson().toJson(appConrolObjectResponse);

    }

    public static AppControlHeader SplitAndDecrypt(String encryptedPaylodString) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchProviderException, JoseException {
        AppControlHeader appControlHeader = null;
        try {
            String[] strArray = encryptedPaylodString.split("\\.");
            String s = encryptedPaylodString;//user can give any combination
            /********************** encryption ***********************/
            String JWE_Header = strArray[0];
            Log.d("JWE_Header ==> ", JWE_Header + "");
            System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
            String header = new String(org.jose4j.base64url.Base64.decode(JWE_Header));
            System.out.println("JWTHeader : " + header);

            appControlHeader = new Gson().fromJson(header, AppControlHeader.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appControlHeader;
    }


    private static EncryptedPayloadAndTag encryptDataWithBC(final Key contentEncryptionKey, final byte[] iv,
                                                            final byte[] payload, final String header) {

        try {
//            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            final GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.ENCRYPT_MODE, contentEncryptionKey, parameterSpec);
            cipher.updateAAD(header.getBytes("US-ASCII"));

            return new EncryptedPayloadAndTag(cipher.doFinal(payload));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new IllegalStateException("How did we get here?");
    }


    public static String sign(PrivateKey privateKey, List<com.nimbusds.jose.util.Base64> x5cList, String payload) {
        try {

            AppLog.Log("payload", " ****"+payload);
            final String payloadPortion = java.util.Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(payload.getBytes(StandardCharsets.UTF_8));



            JsonWebSignature jws = new JsonWebSignature();
            jws.setPayload(payload);
            jws.setKey(privateKey);
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA512);
            jws.setKeyIdHeaderValue(Constants.KID);
            jws.setHeader(HeaderParameterNames.X509_CERTIFICATE_CHAIN, x5cList);
            String encryptedJws = jws.getCompactSerialization();


//            JWSObject jwsObject = new JWSObject(new JWSHeader.Builder(JWSAlgorithm.RS256)
//                    .keyID(Constants.KID).x509CertChain(x5cList).build(),
//                    new Payload(payload));
//            JWSSigner signer = new RSASSASigner(privateKey);
//            jwsObject.sign(signer);
//            String encryptedJws = jwsObject.serialize();
            String[] parts = encryptedJws.split("//.");
            System.out.println("jwsObject parts"+ parts.length );
            System.out.println("jwsObject encryptedJws"+ encryptedJws);
            int maxLogSize = 2000;
            for(int i = 0; i <= encryptedJws.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > encryptedJws.length() ? encryptedJws.length() : end;
                android.util.Log.d("jwsObject encryptedJws " + i + "  ", encryptedJws.substring(start, end));
            }

            return encryptedJws;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    public static class EncryptedPayloadAndTag {
        private final byte[] encryptedPayload;
        private final byte[] tag;

        public EncryptedPayloadAndTag(final byte[] encryptionOutput) {
            this.encryptedPayload = Arrays.copyOfRange(encryptionOutput, 0, encryptionOutput.length - 16);
            this.tag = Arrays.copyOfRange(encryptionOutput, encryptionOutput.length - 16, encryptionOutput.length);
        }

        public byte[] getEncryptedPayload() {
            return this.encryptedPayload;
        }

        public byte[] getTag() {
            return this.tag;
        }
    }


    /* Get Private Key of RSA in PKCS8EncodedKeySpec */
    public static PrivateKey getPrivateKey(String encodedPrivateKey) {
        PrivateKey privateKey = null;
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                privateKey   = keyFactory
                        .generatePrivate(new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(encodedPrivateKey)));
            }else{
                privateKey = keyFactory
                        .generatePrivate(new PKCS8EncodedKeySpec( org.spongycastle.util.encoders.Base64.decode(encodedPrivateKey)));

            }
            return privateKey;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Oops");
        }
    }

    /* Get Public Key of RSA in PKCS8EncodedKeySpec */
    public static PublicKey getPublicKey(String encodedPublicKey) {
        PublicKey publicKey = null;
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                publicKey   = keyFactory
                        .generatePublic(new X509EncodedKeySpec(java.util.Base64.getDecoder().decode(encodedPublicKey)));
            }else{
                publicKey = keyFactory
                        .generatePublic(new X509EncodedKeySpec( org.spongycastle.util.encoders.Base64.decode(encodedPublicKey)));

            }
            return publicKey;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
