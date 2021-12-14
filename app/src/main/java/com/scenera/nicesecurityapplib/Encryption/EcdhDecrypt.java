package com.scenera.nicesecurityapplib.Encryption;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scenera.nicesecurityapplib.models.data.AppControlHeader;
import com.scenera.nicesecurityapplib.models.data.Payload;
import com.scenera.nicesecurityapplib.models.data.PrivacyPayload;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.AppSecurityObjectResponse;
import com.scenera.nicesecurityapplib.models.response.GetPrivaceObjectResponse;
import com.scenera.nicesecurityapplib.utilities.AppLog;
import com.scenera.nicesecurityapplib.utilities.PreferenceHelper;
import com.scenera.nicesecurityapplib.utilities.Utils;

import org.jose4j.jca.ProviderContext;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by Ekta Bhatt on 25-Jun-2020
 */
public class EcdhDecrypt {

    public AppSecurityObjectResponse getAppSecurityObject(Context context,String encryptedPayload, String privateKeyToDecrypt, AppControlHeader appControlHeader)
    {
        AppSecurityObjectResponse appSecurityObjectResponse = null;

        try {

            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

            String jwsPayload = parseCertificateAppSecurity(encryptedPayload, appControlHeader.getX5c());

            final PrivateKey privateKey = getPrivateKey(privateKeyToDecrypt, "ECDSA");
            final JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            final ProviderContext provideContext = new ProviderContext();
            provideContext.getGeneralProviderContext().setGeneralProvider("SC");
            jsonWebEncryption.setProviderContext(provideContext);
            jsonWebEncryption.setKey(privateKey);
            jsonWebEncryption.setCompactSerialization(jwsPayload);
            String response = jsonWebEncryption.getPayload();

            System.out.println("JsonWebEncryption: " +jsonWebEncryption.getPayload()); // JWE

            /**
             * Get the AppSecurity Object
             */
            appSecurityObjectResponse = new Gson().fromJson(jsonWebEncryption.getPayload(), AppSecurityObjectResponse.class);
            System.out.println("JsonWebEncryption: " +jsonWebEncryption.getPayload());
        }
        catch(JoseException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appSecurityObjectResponse;
    }

    private String parseCertificateAppSecurity(String jwe, List<String> listX5c) throws JoseException, CertificateException, NoSuchProviderException {

        String jweResponse = null;
        try {
            String encodedCert = listX5c.get(0);
            System.out.println("encodedCert: " + encodedCert);

            InputStream inputStream = null;
            final CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "SC");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                inputStream = new ByteArrayInputStream(
                        Base64.getDecoder().decode(encodedCert));
            } else {
                inputStream = new ByteArrayInputStream(
                        org.spongycastle.util.encoders.Base64.decode(encodedCert));
            }

            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);

            PublicKey pubKey = cert.getPublicKey();

            if (pubKey instanceof RSAPublicKey) {
                // We have an RSA public key
                Log.d("Inside => ", "RSA Pub");
                final JsonWebSignature jws = new JsonWebSignature();
                jws.setCompactSerialization(jwe);
                jws.setKey(pubKey);
                final boolean valid = jws.verifySignature(); //JWS
                System.out.println("The payload is RSA: " + jws.getPayload());
                System.out.println("IsValid ==> " + valid);
                jweResponse = jws.getPayload().toString();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return jweResponse;
    }

    private String parseCertificate(Context context, String jwe, List<String> listX5c) throws JoseException, CertificateException, NoSuchProviderException {

        String jweResponse = null;
        try {
            String encodedCert = listX5c.get(0);
            String encodedASCert = PreferenceHelper.getInstance(context).
                    getAppSecurityObject().getNICEASEndPoint().getAppEndPoint().getX509Certificate();
            System.out.println("encodedCert: " + encodedCert);

            InputStream inputStream = null, inputStreamAS = null;
            final CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "SC");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                inputStream = new ByteArrayInputStream(
                        Base64.getDecoder().decode(encodedCert));
                inputStreamAS = new ByteArrayInputStream(
                        Base64.getDecoder().decode(encodedASCert));
            } else {
                inputStream = new ByteArrayInputStream(
                        org.spongycastle.util.encoders.Base64.decode(encodedCert));
                inputStreamAS = new ByteArrayInputStream(
                        org.spongycastle.util.encoders.Base64.decode(encodedASCert));
            }

            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(inputStream);
            X509Certificate aSCert = (X509Certificate) certFactory.generateCertificate(inputStreamAS);

            if(Arrays.equals(cert.getPublicKey().getEncoded(), aSCert.getPublicKey().getEncoded())) {
                PublicKey pubKey = cert.getPublicKey();

                if (pubKey instanceof RSAPublicKey) {
                    // We have an RSA public key
                    Log.d("Inside => ", "RSA Pub");
                    final JsonWebSignature jws = new JsonWebSignature();
                    jws.setCompactSerialization(jwe);
                    jws.setKey(pubKey);
                    final boolean valid = jws.verifySignature(); //JWS
                    System.out.println("The payload is RSA: " + jws.getPayload());
                    System.out.println("IsValid ==> " + valid);
                    jweResponse = jws.getPayload().toString();


                }
            }else {
                throw new IllegalStateException("");
            }

        }catch (IllegalStateException e1){
            Toast.makeText(context, "Wrong Certificate chain", Toast.LENGTH_SHORT).show();
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jweResponse;
    }
    /* Get Private Key of given algorithm in PKCS8EncodedKeySpec */
    public static PrivateKey getPrivateKey(String encodedPrivateKey, String algorithm) {
        PrivateKey privateKey = null;
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance(algorithm, "SC");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                privateKey   = keyFactory
                        .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedPrivateKey)));
            }else{
                privateKey = keyFactory
                        .generatePrivate(new PKCS8EncodedKeySpec( org.spongycastle.util.encoders.Base64.decode(encodedPrivateKey)));

            }
            return privateKey;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new IllegalStateException("Oops");
        }
    }


    public AppConrolObjectResponse getAppControlObject(Context context, String encryptedPayload,
                                                       String privateKeyToDecrypt, AppControlHeader appControlHeader)
    {
        AppConrolObjectResponse appConrolObjectResponse = null;

        try {

            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

            String jwsPayload = parseCertificate(context, encryptedPayload, appControlHeader.getX5c());
            appConrolObjectResponse = new Gson().fromJson(jwsPayload, AppConrolObjectResponse.class);

            System.out.println("Before ::" + jwsPayload);
            final PrivateKey privateKey = getPrivateKey(privateKeyToDecrypt, "ECDSA");
            final JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            final ProviderContext provideContext = new ProviderContext();
            provideContext.getGeneralProviderContext().setGeneralProvider("SC");
            jsonWebEncryption.setProviderContext(provideContext);
            jsonWebEncryption.setKey(privateKey);
            jsonWebEncryption.setCompactSerialization(appConrolObjectResponse.getStringPayload());
            String response = jsonWebEncryption.getPayload();

            System.out.println("JsonWebEncryption: " +jsonWebEncryption.getPayload()); // JWE
            System.out.println("Payload: " +new Gson().fromJson(jsonWebEncryption.getPayload(), Payload.class).toString()); // JWE

            appConrolObjectResponse.setPayload(new Gson().fromJson(jsonWebEncryption.getPayload(), Payload.class));

        }
        catch(JoseException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appConrolObjectResponse;
    }

    public JSONObject getSceneMode(String encryptedPayload, Context context,
                                          String privateKeyToDecrypt, AppControlHeader appControlHeader)
    {
        JSONObject appConrolObjectResponse = null;

        try {

            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

            String jwsPayload = parseCertificate(context, encryptedPayload, appControlHeader.getX5c());
            Utils.printLongLog("GET_SCENEMODE", jwsPayload);
            appConrolObjectResponse = new Gson().fromJson(jwsPayload, JSONObject.class);
            appConrolObjectResponse = new JSONObject(jwsPayload);

            System.out.println("Before ::" + jwsPayload);
            final PrivateKey privateKey = getPrivateKey(privateKeyToDecrypt, "ECDSA");
            final JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            final ProviderContext provideContext = new ProviderContext();
            provideContext.getGeneralProviderContext().setGeneralProvider("SC");
            jsonWebEncryption.setProviderContext(provideContext);
            jsonWebEncryption.setKey(privateKey);
            jsonWebEncryption.setCompactSerialization(appConrolObjectResponse.getString("Payload"));
            String response = jsonWebEncryption.getPayload();
            AppLog.LogMaxSize("jwsObject encryptedJws ",jsonWebEncryption.getPayload());
            System.out.println("JsonWebEncryption: " +jsonWebEncryption.getPayload()); // JWE
            System.out.println("Payload: " +new Gson().fromJson(jsonWebEncryption.getPayload(), Payload.class).toString()); // JWE
            appConrolObjectResponse.put("Payload",new JSONObject(jsonWebEncryption.getPayload()));
//            appConrolObjectResponse.setPayload(new Gson().fromJson(jsonWebEncryption.getPayload(), Payload.class));

            Utils.printLongLog("GET_SCENEMODE_RESPONSE", "****"+new Gson().toJson(appConrolObjectResponse));
        }
        catch(JoseException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appConrolObjectResponse;
    }

    public GetPrivaceObjectResponse getPrivaceObjectResponse(Context context,String encryptedPayload,
                                                        String privateKeyToDecrypt, AppControlHeader appControlHeader)
    {
        GetPrivaceObjectResponse getPrivaceObjectResponse = null;

        try {

            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

            String jwsPayload = parseCertificate(context,encryptedPayload, appControlHeader.getX5c());
            getPrivaceObjectResponse = new Gson().fromJson(jwsPayload, GetPrivaceObjectResponse.class);

            System.out.println("Before ::" + jwsPayload);
            final PrivateKey privateKey = getPrivateKey(privateKeyToDecrypt, "ECDSA");
            final JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            final ProviderContext provideContext = new ProviderContext();
            provideContext.getGeneralProviderContext().setGeneralProvider("SC");
            jsonWebEncryption.setProviderContext(provideContext);
            jsonWebEncryption.setKey(privateKey);
            jsonWebEncryption.setCompactSerialization(getPrivaceObjectResponse.getStringPayload());
            String response = jsonWebEncryption.getPayload();

            System.out.println("JsonWebEncryption: " +jsonWebEncryption.getPayload()); // JWE
            System.out.println("Payload: " +new Gson().fromJson(jsonWebEncryption.getPayload(), PrivacyPayload.class).toString()); // JWE

            getPrivaceObjectResponse.setPayload(new Gson().fromJson(jsonWebEncryption.getPayload(), PrivacyPayload.class));

        }
        catch(JoseException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getPrivaceObjectResponse;
    }

}
