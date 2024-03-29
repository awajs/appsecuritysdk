package com.scenera.nicesecurityapplib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.scenera.nicesecurityapplib.Encryption.Crypto;
import com.scenera.nicesecurityapplib.Encryption.EcdhDecrypt;
import com.scenera.nicesecurityapplib.interfaces.ServiceInterfaces;
import com.scenera.nicesecurityapplib.models.data.AppControlError;
import com.scenera.nicesecurityapplib.models.data.AppControlHeader;
import com.scenera.nicesecurityapplib.models.data.AppSecurityObject;
import com.scenera.nicesecurityapplib.models.data.NodeList;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.AppSecurityObjectResponse;
import com.scenera.nicesecurityapplib.models.response.EncryptedCMFResponse;
import com.scenera.nicesecurityapplib.retrofit.ApiClient;
import com.scenera.nicesecurityapplib.utilities.AppLog;
import com.scenera.nicesecurityapplib.utilities.Constants;
import com.scenera.nicesecurityapplib.utilities.PreferenceHelper;
import com.scenera.nicesecurityapplib.utilities.Utils;
import com.scenera.nicesecurityapplib.viewmodel.MainViewModel;

import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.lang.JoseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;
import retrofit2.Callback;

public class BSSLoginActivity extends BaseActivity {

    private static final String SPEC = "secp256r1";
    private static final String ALGO = "EC";
    private static final String PROVIDER = "SC";
    public static PublicKey publicKey, publicKeyRSA;
    public static PrivateKey privateKey, privateKeyRSA;
    public static KeyPair keyPair, keyPairRSA;

    private String niceUrl;
    public WebView wvNiceAS;
    private AppSecurityObject appSecurityObject;
    private String responseString;
    private String jsonPublicKeyString = "", encodedPublicKey = "";
    private String currentDate = "";
    private Crypto crypto;
    private MainViewModel mainViewModel;
    private final static int MY_REQUEST_CODE = 1;

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    //lib update token
    //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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bss_login);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        crypto = Crypto.getInstance();

        /**************** EC KEY PAIR **************************/
        generateEcKeyPair();
        /**************** RSA KEY PAIR **************************/
        generateRSAKeyPair();

        try {

            Date today = new Date();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:" + "000000");
            currentDate = format.format(today);
            System.out.println("currentDate:    " + currentDate);
            wvNiceAS = (WebView) findViewById(R.id.wvNiceAS);
            WebSettings webSettings = wvNiceAS.getSettings();
            WebViewClient webViewClient = new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    wvNiceAS.loadUrl("javascript:HtmlViewer.showHTML" +
                            "(document.getElementsByTagName('body')[0].innerHTML);");
                }
            };

            webSettings.setJavaScriptEnabled(true);
            wvNiceAS.setWebChromeClient(new WebChromeClient());
            wvNiceAS.setWebViewClient(webViewClient);

            AppLog.Log("jsonPublicKeyString => ", encodedPublicKey + "");
            if (jsonPublicKeyString != "" && jsonPublicKeyString != null) {
                byte[] bytes = jsonPublicKeyString.getBytes("UTF-8");
                encodedPublicKey = Utils.base64Encode(bytes);
                Log.d("encodedPublicKey => ", encodedPublicKey + "");
                String bssUrl = Constants.ServiceType.LINK_APP_TO_BSS_ACCOUNT;
                String appId = Constants.APP_ID;

                if (getIntent() != null && getIntent().hasExtra(Constants.BSS_URL)) {
                    bssUrl = getIntent().getStringExtra(Constants.BSS_URL);
                    pHelper.putSignInMode(getIntent().getIntExtra(Constants.SIGN_IN_MODE, 0));
                }

                if (getIntent() != null && getIntent().hasExtra(Constants.BSS_APP_ID)) {
                    appId = getIntent().getStringExtra(Constants.BSS_APP_ID);
                }

                niceUrl = bssUrl + "/" + "link_app/" + appId + "/" + encodedPublicKey;

                Log.d(TAG + "NiceURL==> ", niceUrl + "");

                wvNiceAS.loadUrl(niceUrl);

                wvNiceAS.addJavascriptInterface(new MyJavaScriptInterface(BSSLoginActivity.this), "HtmlViewer");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyJavaScriptInterface {

        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            AppLog.Log("TAG", "showHTML: " + html);

            System.out.println(html);
            responseString = html.trim();
            AppLog.Log("TAG", "responseString: " + responseString);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (responseString.contains("error_message")) {
                        AppControlError appControlError = new Gson().fromJson(responseString.toString(), AppControlError.class);
                        AlertDialog appLinkDialog = new android.app.AlertDialog.Builder(BSSLoginActivity.this).create();
                        appLinkDialog.setTitle(getResources().getString(R.string.text_error_try_again));
                        appLinkDialog.setMessage(appControlError.getErrorMessage());
                        appLinkDialog.setCancelable(false);
                        appLinkDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        appLinkDialog.show();
                    } else {
                        convertToJson();
                    }
                }
            });

        }

    }

    private void convertToJson() {
        AppLog.Log(TAG, "responseStringJson=>" + responseString);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    try {
                        //  pHelper.putServiceProviderName(serviceProviderName);
                        parseAppSecurity();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void parseAppSecurity() {
        try {
            String privateKey = pHelper.getPrivateKey();
            Log.i("privateKey", "--->>> " + privateKey);

            responseString = responseString.replace("\"", "");
            AppControlHeader appControlHeader = SplitAndDecrypt(responseString);

            /*********** Decrypt Encrypted Payload **************/
            EcdhDecrypt ecdhDecrypt = new EcdhDecrypt();

            AppSecurityObjectResponse appSecurityObject = ecdhDecrypt.getAppSecurityObject(this, responseString, privateKey, appControlHeader);
            Gson gson = new Gson();
            String jsonInString = gson.toJson(appSecurityObject);

            if (appSecurityObject != null && appSecurityObject.getAccountID() != null) {

                /************ Call api to get the AppControl Object *************/
                pHelper.putAppSecurityObject(appSecurityObject);
                pHelper.putAppInstanceId(appSecurityObject.getAppInstanceID()); // AppInstanceID
                //  pHelper.putAppInstanceId("00000001-5e84-224c-8003-000000000065");
                AppLog.Log(TAG + " AppInstaceID => ", pHelper.getAppSecurityObject().getAppInstanceID());
                AppLog.Log(TAG + " appSecurityObject => ", String.valueOf(appSecurityObject));

                /**********************GET APP CONTROL OBJECT FROM CURRENT USER'S APP SECURITY OBJECT**********************************************/
                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String currentTime = format.format(today);
                AppLog.Log("currentTime:    ", currentTime);
                AppLog.Log("appControlTime:    ", pHelper.getAppControlTime());

                getAppControlObject(pHelper.getAppSecurityObject());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AppControlHeader SplitAndDecrypt(String encryptedPaylodString) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchProviderException, JoseException {
        AppControlHeader appControlHeader = null;

        try {
            String[] strArray = encryptedPaylodString.split("\\.");
            for (String str : strArray) {
                System.out.println("Splited: " + str);
                System.out.println("\n");
            }
            String s = encryptedPaylodString;//user can give any combination
            int dotsCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    dotsCount++;
                }
            }
            System.out.println("Total " + "Dots ==> " + dotsCount);
            /********************** encryption ***********************/
            String JWE_Header = strArray[0];

            /*  String JWE_Encrypted_Key = strArray[1];
            String JWE_Initialization_Vector = strArray[2];
            String JWE_CipherText = strArray[3];
            String JWE_Authentication_Tag = strArray[4];*/

            Log.d("JWE_Header ==> ", JWE_Header + "");
            //Log.d("JWE_Encrypted_Key ==> ", JWE_Encrypted_Key + "");
            // Log.d("JWE_Initialization_Vector ==> ", JWE_Initialization_Vector + "");
            //  Log.d("JWE_CipherText ==> ", JWE_CipherText + "");
            // Log.d("JWE_Authentication_Tag ==> ", JWE_Authentication_Tag + "");

            System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");

            String header = new String(org.jose4j.base64url.Base64.decode(JWE_Header));
            System.out.println("JWTHeader : " + header);

            appControlHeader = new Gson().fromJson(header, AppControlHeader.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appControlHeader;
    }

    private void getAppControlObject(AppSecurityObjectResponse appSecurityObject) {
        Utils.showCustomProgressDialog(this, "", false);

        String accessToken = pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAccessToken();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectAccessTokenPayload = new JSONObject();
        JSONObject CMFHeaderObject = new JSONObject();
        try {
            CMFHeaderObject.put(Constants.CMF.Header.VERSION, appSecurityObject.getVersion());
            CMFHeaderObject.put(Constants.CMF.Header.MESSAGE_TYPE, Constants.CMF.HeaderValue.MESSAGE_TYPE);
            CMFHeaderObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, appSecurityObject.getAppInstanceID());
            CMFHeaderObject.put(Constants.CMF.Header.DESTINATION_END_POINT_ID, appSecurityObject.getNICEASEndPoint().getNetEndPoint().getEndPointID());
            CMFHeaderObject.put(Constants.CMF.Header.DATE_TIME_STAMP, currentDate);
            CMFHeaderObject.put(Constants.CMF.Header.COMMAND_ID, Constants.CMF.HeaderValue.COMMAND_ID);
            CMFHeaderObject.put(Constants.CMF.Header.COMMAND_TYPE, Constants.CMF.HeaderValue.COMMAND_TYPE);

            JSONObject jsonPayLoad = new JSONObject();
            String appId = appSecurityObject.getAppInstanceID();

            jsonPayLoad.put(Constants.Params.APP_ID, appId);

            jsonObjectAccessTokenPayload.put(Constants.CMF.Payload.PAYLOAD_OBJECT, jsonPayLoad);
            jsonObjectAccessTokenPayload.put(Constants.CMF.Header.ACCESS_TOKEN, accessToken);

            jsonObject.put(Constants.CMF.Payload.EndPointX509Certificate,appSecurityObject.getNICEASEndPoint().getAppEndPoint().getX509Certificate());
            jsonObject.put(Constants.CMF.Payload.CMF_HEADER, CMFHeaderObject.toString());
            jsonObject.put(Constants.CMF.Payload.ACCESSTOKEN_PAYLOAD, jsonObjectAccessTokenPayload);

            AppLog.Log("jsonObjectMain => ", jsonObject.toString());


//            if(pHelper.getSignInMode() == Constants.STAGING_SIGN_IN) {
            String encryptedPayload = Utils.encryptAndSignCMF(this, jsonObject,
                    jsonObjectAccessTokenPayload.toString());

            AppLog.Log("encryptedPayload => ", encryptedPayload.toString());

            JSONObject jsonObjectRequest = new JSONObject();
            jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_KEY, PreferenceHelper.getInstance(this).getPublicKeyRSA());
            jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_PAYLOAD,encryptedPayload);
            //jsonObjectRequest.put(Constants.CMF.Payload.SIGNED_CMF, encryptedPayload);

            ServiceInterfaces.GetAppControlObjectEncrypted api = ApiClient.getClientAccount(this, "https://" +
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObjectEncrypted.class);

            Call<EncryptedCMFResponse> call = api.getAppControlObject("Bearer " + accessToken,
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                    Constants.CODE_GET_APP_CONTROL_REQUEST,
                    ApiClient.makeJSONRequestBody(jsonObjectRequest));


            call.enqueue(new Callback<EncryptedCMFResponse>() {
                @Override
                public void onResponse(Call<EncryptedCMFResponse> call, retrofit2.Response<EncryptedCMFResponse> response) {
                    Log.i(TAG, "---->>> AppControl " + response.raw().request().url());

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {

                        /**************************** PUT APPCONTROL TIME*********************************/
                        String encryptedPayload = response.body().getencryptedPayload();
                        AppConrolObjectResponse appConrolObjectResponse = Utils.decryptAndValidateCMF(BSSLoginActivity.this, encryptedPayload);
                        AppLog.Log("appConrolObjectResponse", "****" + new Gson().toJson(appConrolObjectResponse));
                        pHelper.putAppControlObject(appConrolObjectResponse);
                        pHelper.putEmail("test");
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_error_app_link_success), Toast.LENGTH_LONG).show();
                        //((HomeActivity) getActivity()).changeButtonUI(R.id.tvCameras);

                        String token = appConrolObjectResponse.getPayload().getDataEndPoints().get(0).
                                getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                        if (!TextUtils.isEmpty(token)) {
                            String[] strArray = token.split("\\.");
                            String JWTPayload = new String(org.jose4j.base64url.internal.apache.
                                    commons.codec.binary.Base64.decodeBase64(strArray[1]));

                            try {
                                JSONObject jsonObject = new JSONObject(JWTPayload);
                                AppLog.Log("EXPIRY_DATE", "****" + jsonObject.getString("exp"));
                                AppLog.Log("NOT_BEFORE_DATE", "****" + jsonObject.getString("nbf"));
                                pHelper.putExpiryDate(jsonObject.getLong("exp") * 1000);
                                pHelper.putNotBeforeDate(jsonObject.getLong("nbf") * 1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent = new Intent();
                        intent.putExtra("TestData", "TestData");
                        setResult(RESULT_OK, intent);
                        finish();
                      /*  Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);*/
                        // finish();
                    }
                }

                @Override
                public void onFailure(Call<EncryptedCMFResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });


//            }else {
//                ServiceInterfaces.GetAppControlObject api = ApiClient.getClientAccount(this,"https://" +
//                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObject.class);
//
//                Call<AppConrolObjectResponse> call = api.getAppControlObject("Bearer "+ accessToken, pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
//                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
//                        Constants.CODE_GET_APP_CONTROL_REQUEST,
//                        ApiClient.makeJSONRequestBody(jsonObject));
//                call.enqueue(new Callback<AppConrolObjectResponse>() {
//                    @Override
//                    public void onResponse(Call<AppConrolObjectResponse> call, Response<AppConrolObjectResponse> response) {
//                        /**************************** PUT APPCONTROL TIME*********************************/
//
//                        AppConrolObjectResponse appConrolObjectResponse = response.body();
//                        AppLog.Log("appConrolObjectResponse", "****" + new Gson().toJson(appConrolObjectResponse));
//                        pHelper.putAppControlObject(appConrolObjectResponse);
//                        pHelper.putEmail("test");
//                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_error_app_link_success), Toast.LENGTH_LONG).show();
//                        //((HomeActivity) getActivity()).changeButtonUI(R.id.tvCameras);
//
//
//                        Intent intent = new Intent();
//                        intent.putExtra("TestData", "TestData");
//                        setResult(RESULT_OK, intent);
//                        finish();
//                      /*  Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                        startActivity(intent);*/
//                        // finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<AppConrolObjectResponse> call, Throwable t) {
//
//                    }
//                });
//            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MY_REQUEST_CODE) {
                if (data != null) {
                    ArrayList<NodeList> nodeLists = data.getParcelableArrayListExtra("nodeList");
                    AppLog.Log("ArrayListIntent: ", nodeLists.size() + "");
                }

            }
        }
    }

    public void generateEcKeyPair() {

        try {
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGO, PROVIDER);
            keyPairGenerator.initialize(ecSpec);

            if (pHelper.getPublicKey() != null && pHelper.getPrivateKey() != null) {
                keyPair = readKeyPair();
            } else {

                keyPair = keyPairGenerator.generateKeyPair();

                saveKeyPair(keyPair);

                keyPair = readKeyPair();
            }

            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

            ECPoint ecPointX = ((ECPublicKey) publicKey).getW();
            String stringBigEndianBigIntegerX = BigEndianBigInteger.toBase64Url(ecPointX.getAffineX());

            ECPoint ecPointY = ((ECPublicKey) publicKey).getW();
            String stringBigEndianBigIntegerY = BigEndianBigInteger.toBase64Url(ecPointY.getAffineY());

            JSONObject jsonObjectMain = new JSONObject();

            try {
                jsonObjectMain.put(Constants.Crypto.ALGORITHM, Constants.Crypto.ECDH_ES);

                JSONObject jsonObjPubKey = new JSONObject();

                jsonObjPubKey.put(Constants.Crypto.CRV, Constants.Crypto.P_256);
                jsonObjPubKey.put(Constants.Crypto.KTY, Constants.Crypto.EC);
                jsonObjPubKey.put(Constants.Crypto.X, stringBigEndianBigIntegerX);
                jsonObjPubKey.put(Constants.Crypto.Y, stringBigEndianBigIntegerY);

                jsonObjectMain.put(Constants.Crypto.ENCRYPTION_KEY, jsonObjPubKey);
                jsonObjectMain.put(Constants.Crypto.SIGNING_KEY_ID, "00000002-5cdd-280b-8003-000000000000");

                jsonPublicKeyString = jsonObjectMain.toString();
                AppLog.Log("jsonObjectMain => ", jsonPublicKeyString);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveKeyPair(KeyPair keyPair) {
        String pubStr = Utils.base64Encode(keyPair.getPublic().getEncoded());
        String priStr = Utils.base64Encode(keyPair.getPrivate().getEncoded());


        pHelper.putPrivateKey(priStr);
        pHelper.putPublicKey(pubStr);
    }

    private KeyPair readKeyPair() throws Exception {
        String pubKeyStr = pHelper.getPublicKey();
        AppLog.Log("pubKeyStr=> ", pubKeyStr);
        String privKeyStr = pHelper.getPrivateKey();
        AppLog.Log("privKeyStr=> ", privKeyStr);
        if (pubKeyStr == null || privKeyStr == null) {
            return null;
        }

        return Crypto.readEcKeyPair(pubKeyStr, privKeyStr);
    }


    public void generateRSAKeyPair() {

        try {
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
            keyPairGenerator.initialize(2048);

            if (pHelper.getPublicKeyRSA() != null && pHelper.getPrivateKeyRSA() != null) {
                Log.d("SavedPref", "true");

                keyPairRSA = readKeyPairRSA();

                Log.d("PublickeyRSA=> ", keyPair.getPublic() + "");
                Log.d("PrivateKeyRSA=> ", keyPair.getPrivate() + "");

            } else {
                Log.d("SavedPref", "false");

                keyPairRSA = keyPairGenerator.generateKeyPair();
                Log.d("PublickeyRSA=> ", keyPairRSA.getPublic() + "");
                Log.d("PrivateKeyRSA=> ", keyPairRSA.getPrivate() + "");

                saveRSAKeyPair(keyPairRSA);

                keyPairRSA = readKeyPairRSA();

            }

            publicKeyRSA = keyPairRSA.getPublic();
            privateKeyRSA = keyPairRSA.getPrivate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveRSAKeyPair(KeyPair keyPair) {
        String pubStr = Utils.base64Encode(keyPair.getPublic().getEncoded());
        String priStr = Utils.base64Encode(keyPair.getPrivate().getEncoded());

        pHelper.putPrivateKeyRSA(priStr);
        pHelper.putPublicKeyRSA(pubStr);

    }

    private KeyPair readKeyPairRSA() throws Exception {
        String pubKeyStr = pHelper.getPublicKeyRSA();
        AppLog.Log("pubKeyStrRSA=> ", pubKeyStr);
        String privKeyStr = pHelper.getPrivateKeyRSA();
        AppLog.Log("privKeyStrRSA=> ", privKeyStr);
        if (pubKeyStr == null || privKeyStr == null) {
            return null;
        }

        return Crypto.readRSAKeyPair(pubKeyStr, privKeyStr);
    }

}