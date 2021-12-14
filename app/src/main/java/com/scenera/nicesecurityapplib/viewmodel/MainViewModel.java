package com.scenera.nicesecurityapplib.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auth0.android.jwt.JWT;
import com.google.gson.Gson;
import com.scenera.nicesecurityapplib.BaseActivity;
import com.scenera.nicesecurityapplib.interfaces.ServiceInterfaces;
import com.scenera.nicesecurityapplib.models.data.ControlEndPoint;
import com.scenera.nicesecurityapplib.models.data.DetectedObjectsClass;
import com.scenera.nicesecurityapplib.models.data.Encryption;
import com.scenera.nicesecurityapplib.models.data.NetEndPointPrivacy;
import com.scenera.nicesecurityapplib.models.data.NodeList;
import com.scenera.nicesecurityapplib.models.data.Output;
import com.scenera.nicesecurityapplib.models.data.PersonFace;
import com.scenera.nicesecurityapplib.models.data.PrivacyServerEndPoint;
import com.scenera.nicesecurityapplib.models.data.SceneDataClass;
import com.scenera.nicesecurityapplib.models.data.SceneDataValues;
import com.scenera.nicesecurityapplib.models.data.SceneMarkValues;
import com.scenera.nicesecurityapplib.models.data.SchemeAppControlObject;
import com.scenera.nicesecurityapplib.models.data.SchemePrivacy;
import com.scenera.nicesecurityapplib.models.response.AddFaceResponse;
import com.scenera.nicesecurityapplib.models.response.AllItemTypesResponse;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.AppSecurityObjectResponse;
import com.scenera.nicesecurityapplib.models.response.EncryptedCMFResponse;
import com.scenera.nicesecurityapplib.models.response.GetDevicesResponse;
import com.scenera.nicesecurityapplib.models.response.GetFaceDataResponse;
import com.scenera.nicesecurityapplib.models.response.GetPrivaceObjectResponse;
import com.scenera.nicesecurityapplib.models.response.GetSceneMarkManifestResponse;
import com.scenera.nicesecurityapplib.models.response.GetSceneModeResponse;
import com.scenera.nicesecurityapplib.models.response.SceneMarkResponseCMF;
import com.scenera.nicesecurityapplib.retrofit.ApiClient;
import com.scenera.nicesecurityapplib.utilities.AppLog;
import com.scenera.nicesecurityapplib.utilities.Constants;
import com.scenera.nicesecurityapplib.utilities.PreferenceHelper;
import com.scenera.nicesecurityapplib.utilities.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private PreferenceHelper pHelper;
    private static final String TAG = "MainViewModel";
    private String keyEncrypted = "", ivEncrypted="", keyId="";

    private MutableLiveData<AppConrolObjectResponse> appConrolObjectLiveData;
    private MutableLiveData<GetPrivaceObjectResponse> privacyObjectLiveData;
    private MutableLiveData<ArrayList<SceneMarkResponseCMF>> alertLiveData;
    private ArrayList<SceneMarkResponseCMF> alertArrayList;

    private MutableLiveData<ArrayList<String>> eventDatesLiveData;

    private MutableLiveData<Boolean> isTokenAdded;
    private MutableLiveData<Boolean> isTokenRemoved;
    private MutableLiveData<Boolean> isFaceAdded;
    private MutableLiveData<Boolean> isFaceChanged;
    private MutableLiveData<ArrayList<PersonFace>> facesLiveData;

    private ArrayList<String> eventDatesList;

    private GetSceneMarkManifestResponse getSceneMarkManifestResponse;
    private MutableLiveData<GetSceneMarkManifestResponse> sceneMarkManifestLiveData;

    private SceneMarkResponseCMF sceneMarkResponse;
    private MutableLiveData<SceneMarkResponseCMF> sceneMarkResponseLive;

    private MutableLiveData<ArrayList<String>> niceItemTypesLiveData;
    private MutableLiveData<AllItemTypesResponse> allTypesLiveData;
    private ArrayList<String> niceItemTypes;
    private ArrayList<String> eventTypes;

    private MutableLiveData<ArrayList<NodeList>> nodeListLiveData;
    private ArrayList<NodeList> nodeList;
    private String currentDate = "";
    private SimpleDateFormat format;
    private ControlEndPoint appControlEndPoint;
    private ControlEndPoint sceneModeControlEndPoint;

    private static String strGlobalDeviceNodeID = "", strGlobalDevicePortID = "";
    private static List<DetectedObjectsClass> listDetectedObjects;
    private static SceneDataClass objThumbnailSceneData;
    private static int iVideoRecordingDurationSec;
    private static int iGlobalImageWidth, iGlobalImageHeight, iGlobalVideoWidth, iGlobalVideoHeight;
    private static String strGlobalBrigdeUUID = "00000005-6116-005b-8002-00000000002b",
            strGlobalVideoSceneDataID = "";
    private static String strSceneMarkID = "", strFullImageSceneDataID = "";
    private static String accessTokenSceneMode = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Imwzc1EtNTBjQ0g0eEJWWkxIVEd3blNSNzY4MCIsImtpZCI6Imwzc1EtNTBjQ0g0eEJWWkxIVEd3blNSNzY4MCJ9.eyJhdWQiOiJhcGk6Ly85ZmExNGI5NC0wMmFkLTRhNDMtOTU1MC04MzA1NDM0OGRkZGMiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC8xMzEwMzFkNy1iOThjLTQzNWQtOGM1YS1mYTc0MDk1N2Q2MmIvIiwiaWF0IjoxNjM1NDAwOTM3LCJuYmYiOjE2MzU0MDA5MzcsImV4cCI6MTYzNTQ4NDAzNywiYWlvIjoiRTJaZ1lIZzgrZi9PZktHM1NhZldlVEE3SFZ1YUFBQT0iLCJhcHBpZCI6ImEzY2I0Mzc2LThkYWUtNDc2NC1iMGNhLWRjNzc3ZmNiODE1MCIsImFwcGlkYWNyIjoiMSIsImlkcCI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0LzEzMTAzMWQ3LWI5OGMtNDM1ZC04YzVhLWZhNzQwOTU3ZDYyYi8iLCJvaWQiOiIzZmU4OTc3Zi1hZjg0LTQ4MTktYjRjZi05OTRkMTk2ODc4MDEiLCJyaCI6IjAuQVZnQTF6RVFFNHk1WFVPTVd2cDBDVmZXSzNaRHk2T3VqV1JIc01yY2QzX0xnVkJZQUFBLiIsInJvbGVzIjpbIkFwcFJvbGUiXSwic3ViIjoiM2ZlODk3N2YtYWY4NC00ODE5LWI0Y2YtOTk0ZDE5Njg3ODAxIiwidGlkIjoiMTMxMDMxZDctYjk4Yy00MzVkLThjNWEtZmE3NDA5NTdkNjJiIiwidXRpIjoiNDdTWEM3VGxta3VsYUJ3V05OTmNBQSIsInZlciI6IjEuMCJ9.JKQv0_FWnGoD3LlVV2aSyk6EsI9QMTKzX--0cx5qnNKXBeIRwtLnIiGH73www8bjWrvHzVFZsdTjJB_tBueYChxwEcpoFlq2iRlVSXcfMJboxLStcaCbNYX6797g6js15_1GNzwyA2cAe13pq-Wn4sMHAwj0D143fHFX60msgPmZ3bQa-lrNIOzZV0c65St1vAuHgYvY7sNdQvL-SHXJxoX2oyRJwp20G0g940Zp-Ba5AJLkoK3PlhedJPTJz3arTuW92JLPD547QVlk2Py-3NCDAm_FXtN7hP7MvFBPJrSxenwHDz_RYD9JLvL9muyFZBogwMN5L2QRgUw76iNGjw";
    private static String strSceneModeEndPoint, strSceneModeAuthority, strSceneModeToken;
    private static String strGlobalSceneMarkEndPoint
            , strGlobalSceneMarkToken = accessTokenSceneMode
            , strGlobalSceneMarkAuthority = "ingress-dev.scenera.live"
            , strGlobalSceneDataImageEndPoint = "00000001-5cdd-280b-8003-00020000ffff"
            , strGlobalSceneDataImageToken = accessTokenSceneMode
            , strGlobalSceneDataImageAuthority = "ingress-dev.scenera.live"
            , strGlobalSceneDataVideoEndPoint = "00000001-5cdd-280b-8003-00020000ffff"
            , strGlobalSceneDataVideoToken = accessTokenSceneMode
            , strGlobalSceneDataVideoAuthority = "ingress-dev.scenera.live";
    private static boolean fIsImageEncrypted = false;
    private static Encryption EncryptionImage, EncryptionVideo;
    private static int NodeID = 1,PortID = 1;
    private static List<String> listRelatedSceneMarksToVideo = new ArrayList<>();
    private static BaseActivity context;
    private static String strBase64OfSceneDataImage;

    public MainViewModel() {

        appConrolObjectLiveData = new MutableLiveData<>();
        privacyObjectLiveData = new MutableLiveData<>();
        isTokenAdded = new MutableLiveData<>();
        isTokenRemoved = new MutableLiveData<>();
        isFaceAdded = new MutableLiveData<>();
        isFaceChanged = new MutableLiveData<>();
        facesLiveData = new MutableLiveData<>();
        alertLiveData = new MutableLiveData<>();
        alertArrayList = new ArrayList<>();
        eventDatesLiveData = new MutableLiveData<>();
        eventDatesList = new ArrayList<>();

        sceneMarkManifestLiveData = new MutableLiveData<>();
        getSceneMarkManifestResponse = new GetSceneMarkManifestResponse();

        sceneMarkResponseLive = new MutableLiveData<>();

        sceneMarkResponse = new SceneMarkResponseCMF();
        allTypesLiveData = new MutableLiveData();

        niceItemTypesLiveData = new MutableLiveData<>();
        niceItemTypes = new ArrayList<String>();
        eventTypes = new ArrayList<String>();

        nodeListLiveData = new MutableLiveData<>();
        nodeList = new ArrayList<>();
        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:" + "000000");
    }
    /** get NodeList **/
    public MutableLiveData<ArrayList<NodeList>> getDeviceList() {

        return nodeListLiveData;
    }
    public MutableLiveData<ArrayList<SceneMarkResponseCMF>> getCurationLiveArrayList() {
        Log.d(TAG,"getAlertMutableLiveData: " + alertLiveData);
        return alertLiveData;
    }

    public MutableLiveData<SceneMarkResponseCMF> getAlertLiveData() {
        Log.d(TAG,"getAlertMutableLiveData: " + sceneMarkResponseLive);
        return sceneMarkResponseLive;
    }
    /** get Event Dates **/
    public MutableLiveData<ArrayList<String>> getEventDatesData() {
        Log.d(TAG,"getEventMutableLiveData: " + eventDatesLiveData);
        return eventDatesLiveData;
    }
    /** get NiceItemTypes List **/
    public MutableLiveData<ArrayList<String>> getNiceItemTypes() {
        Log.d(TAG,"getNiceItemTypesMutableLiveData: " + niceItemTypesLiveData);
        return niceItemTypesLiveData;
    }
    /** get AllTypes List **/
    public MutableLiveData<AllItemTypesResponse> getAllTypes() {
        Log.d(TAG,"getNiceItemTypesMutableLiveData: " + allTypesLiveData);
        return allTypesLiveData;
    }

    /** get SceneMarksManifest **/
    public MutableLiveData<GetSceneMarkManifestResponse> getSceneMarkManifestLiveData() {

        return sceneMarkManifestLiveData;
    }

    /** get PrivateObject **/
    public MutableLiveData<GetPrivaceObjectResponse> getPrivacyObjectLiveData() {

        return privacyObjectLiveData;
    }

    /** get PrivateObject **/
    public MutableLiveData<AppConrolObjectResponse> getAppConrolObjectLiveData() {

        return appConrolObjectLiveData;
    }

    public MutableLiveData<Boolean> getIsTokenAdded() {

        return isTokenAdded;
    }
    public MutableLiveData<Boolean> getIsTokenRemoved() {

        return isTokenRemoved;
    }
    public MutableLiveData<Boolean> getIsFaceAdded() {

        return isFaceAdded;
    }
    public MutableLiveData<Boolean> getIsFaceChanged() {

        return isFaceChanged;
    }

    public MutableLiveData<ArrayList<PersonFace>> getFacesLiveData(){

        return facesLiveData;
    }


    public MutableLiveData<ArrayList<NodeList>> getNodeList(AppCompatActivity activity) {

        try {
            pHelper = PreferenceHelper.getInstance(activity);
            if(isTokenNotExpired()) {
                Call<GetDevicesResponse> call;
                Utils.showCustomProgressDialog(activity, "", false);

                String accessToken = appControlEndPoint.getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                String authority = "https://" + appControlEndPoint.getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

                ServiceInterfaces.GetAccountNode api = ApiClient.getClientAccount(activity, authority).create(ServiceInterfaces.GetAccountNode.class);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Constants.Params.ACCOUNT_ID, pHelper.getAppSecurityObject().getAccountID());
                jsonObject.put(Constants.Params.APP_ID, pHelper.getAppSecurityObject().getAppID());
                jsonObject.put(Constants.Params.APP_INSTANCE_ID, pHelper.getAppSecurityObject().getAppInstanceID());

                call = api.getAccountNode("Bearer " + accessToken,
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                        ApiClient.makeJSONRequestBody(jsonObject));

                call.enqueue(new Callback<GetDevicesResponse>() {
                    @Override
                    public void onResponse(Call<GetDevicesResponse> call, retrofit2.Response<GetDevicesResponse> response) {
                        Utils.removeCustomProgressDialog();
                        Log.i("url", "---->>> GetDevice URL " + response.raw().request().url());

                        if (response.body() != null && response.body().getNodeList().size() > 0) {
                            nodeList.addAll(response.body().getNodeList());
                            Log.i("deviceList", "size*** " + response.body().getNodeList().size());

                            pHelper.putAllCamera(nodeList);
                            Gson gson = new Gson();
                            String responseString = gson.toJson(nodeList);

                            nodeListLiveData.setValue(nodeList);

                        } else {
                            Utils.removeCustomProgressDialog();
                            // Utils.showAlert(activity, activity.getResources().getString(R.string.text_error_no_scenemarks));
                        }
                    }

                    @Override
                    public void onFailure(Call<GetDevicesResponse> call, Throwable t) {
                        Log.i("profile", "Login--->> onFailure called");
                        Log.i("url", "---->>> checkAccount " + call.request().url());

                    }
                });
            }else {
                getAppControlObject(pHelper.getAppSecurityObject(),activity,
                        Constants.Method.GET_NODE_LIST, new ArrayList<String>(),"","",
                0, false, false,
                false, new ArrayList<String>(),new ArrayList<String>(), "", "","", "","", "");
            }
        }catch (Exception et){
            et.printStackTrace();
        }
        return nodeListLiveData;
    }

    /** get SceneMarksManifest **/
    public MutableLiveData<GetSceneMarkManifestResponse> getSceneMarksManifest(AppCompatActivity activity, ArrayList<String> nodeIds,String startTime, String endTime,
                                                                               int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                                                               boolean returnPage, ArrayList<String> niceItemList, ArrayList<String> eventList,String continuationToken) {
        pHelper = PreferenceHelper.getInstance(activity);
        if(isTokenNotExpired()) {
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put(Constants.Params.NODE_IDS, Utils.convertListToJsonArray(nodeIds));


                jsonObject.put(Constants.Params.START_TIME, startTime);
                jsonObject.put(Constants.Params.END_TIME, endTime);

                jsonObject.put(Constants.Params.PAGE_LENGTH, pageLength);
                jsonObject.put(Constants.Params.RETURN_NICE_ITEM_TYPES, returnNiceItemTypes);
                jsonObject.put(Constants.Params.RETURN_SCENEMARKS_DATES, returnSceneMarksDates);
                jsonObject.put(Constants.Params.RETURN_PAGE, returnPage);

                /*Filter by NICE_ITEM_TYPES*/
                if (niceItemList.size() > 0) {
                    JSONArray niceItemArray = new JSONArray();
                    for (int i = 0; i < niceItemList.size(); i++) {
                        niceItemArray.put(niceItemList.get(i));
                    }
                    jsonObject.put(Constants.Params.NICE_ITEM_TYPES, niceItemArray);
                } else {
                    JSONArray niceItemArray = new JSONArray();
                    jsonObject.put(Constants.Params.NICE_ITEM_TYPES, niceItemArray);
                }
                /*Filter by EVENT_TYPES*/
                if (eventList.size() > 0) {
                    JSONArray eventItemArray = new JSONArray();
                    for (int i = 0; i < eventList.size(); i++) {
                        eventItemArray.put(eventList.get(i));
                    }
                    jsonObject.put(Constants.Params.EVENT_TYPES, eventItemArray);
                } else {
                    JSONArray eventItemArray = new JSONArray();
                    jsonObject.put(Constants.Params.EVENT_TYPES, eventItemArray);
                }
                AppLog.Log("continuationTokenMain=> ", continuationToken);
                jsonObject.put(Constants.Params.CONTINUATION_TOKEN, continuationToken);
                AppLog.Log("jsonObject => ", jsonObject.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }

            pHelper = PreferenceHelper.getInstance(activity);

            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();
            ServiceInterfaces.GetSceneMarkManifest api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.GetSceneMarkManifest.class);

            Call<GetSceneMarkManifestResponse> call = api.getSceneMarkManifest("Bearer " + accessToken,
                    pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getAPIVersion(),
                    pHelper.getAppInstanceId(),
                    ApiClient.makeJSONRequestBody(jsonObject));

            call.enqueue(new Callback<GetSceneMarkManifestResponse>() {
                @Override
                public void onResponse(Call<GetSceneMarkManifestResponse> call, retrofit2.Response<GetSceneMarkManifestResponse> response) {
                    Log.i("url", "---->>> getSceneMarkManifest " + response.raw().request().url());

                    if (!response.equals("{}") && response != null && response.body() != null && response.body().getSceneMarkList() != null &&
                            response.body().getSceneMarkList().size() > 0) {


                        if (getSceneMarkManifestResponse.getSceneMarkList() != null && getSceneMarkManifestResponse.getSceneMarkList().size() > 0) {
                            getSceneMarkManifestResponse = null;
                            getSceneMarkManifestResponse.getSceneMarkList().clear();

                        }
                        sceneMarkManifestLiveData.setValue(response.body());

                    } else {
                        if (getSceneMarkManifestResponse.getSceneMarkList() != null && getSceneMarkManifestResponse.getSceneMarkList().size() > 0) {
                            getSceneMarkManifestResponse = null;
                            getSceneMarkManifestResponse.getSceneMarkList().clear();
                        }

                        sceneMarkManifestLiveData.setValue(response.body());

                        Utils.removeCustomProgressDialog();

                    }
                }

                @Override
                public void onFailure(Call<GetSceneMarkManifestResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());

                }
            });
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_SCENEMARKS_MANIFEST, nodeIds,startTime,endTime,
                    pageLength, returnNiceItemTypes, returnSceneMarksDates,
                    returnPage, niceItemList, eventList, continuationToken, "","", "", "", "");
        }
        return sceneMarkManifestLiveData;
    }

    /** Get Event Dates For SceneMarks for selected month..**/
    public MutableLiveData<ArrayList<String>> getEventDates(AppCompatActivity activity, ArrayList<String> nodeIds,String startTime, String endTime,
                                                            int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                                            boolean returnPage, ArrayList<String> niceItemList, ArrayList<String> eventList, String continuationToken) {
        pHelper = PreferenceHelper.getInstance(activity);
        if (isTokenNotExpired()) {
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put(Constants.Params.NODE_IDS, Utils.convertListToJsonArray(nodeIds));


                jsonObject.put(Constants.Params.START_TIME, startTime);
                jsonObject.put(Constants.Params.END_TIME, endTime);

                jsonObject.put(Constants.Params.PAGE_LENGTH, pageLength);
                jsonObject.put(Constants.Params.RETURN_NICE_ITEM_TYPES, returnNiceItemTypes);
                jsonObject.put(Constants.Params.RETURN_SCENEMARKS_DATES, returnSceneMarksDates);
                jsonObject.put(Constants.Params.RETURN_PAGE, returnPage);

                /*Filter by NICE_ITEM_TYPES*/
                if (niceItemList.size() > 0) {
                    JSONArray niceItemArray = new JSONArray();
                    for (int i = 0; i < niceItemList.size(); i++) {
                        niceItemArray.put(niceItemList.get(i));
                    }
                    jsonObject.put(Constants.Params.NICE_ITEM_TYPES, niceItemArray);
                } else {
                    JSONArray niceItemArray = new JSONArray();
                    jsonObject.put(Constants.Params.NICE_ITEM_TYPES, niceItemArray);
                }

                /*Filter by EVENT_TYPES*/
                if (eventList.size() > 0) {
                    JSONArray eventItemArray = new JSONArray();
                    for (int i = 0; i < eventList.size(); i++) {
                        eventItemArray.put(eventList.get(i));
                    }
                    jsonObject.put(Constants.Params.EVENT_TYPES, eventItemArray);
                } else {
                    JSONArray eventItemArray = new JSONArray();
                    jsonObject.put(Constants.Params.EVENT_TYPES, eventItemArray);
                }
                AppLog.Log("continuationTokenMain=> ", continuationToken);
                jsonObject.put(Constants.Params.CONTINUATION_TOKEN, continuationToken);
                AppLog.Log("jsonObject => ", jsonObject.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }


            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

            ServiceInterfaces.GetEventDates api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.GetEventDates.class);

            Call<GetSceneMarkManifestResponse> call = api.getEventDates("Bearer " + accessToken,
                    appControlEndPoint.getNetEndPointAppControl().getAPIVersion(),
                    pHelper.getAppInstanceId(), ApiClient.makeJSONRequestBody(jsonObject));
            call.enqueue(new Callback<GetSceneMarkManifestResponse>() {
                @Override
                public void onResponse(Call<GetSceneMarkManifestResponse> call, retrofit2.Response<GetSceneMarkManifestResponse> response) {

                    Log.i("url", "GetTotalSceneCountforDate------->>>> " + response.raw().request().url());
                    if (!response.equals("{}") && response != null && response.body() != null) {
                        if (response.body().getListDates() != null) {
                            Log.d("totalEventDates==> ", response.body().getListDates().size() + "");

                            eventDatesList.addAll(response.body().getListDates());
                            eventDatesLiveData.setValue(eventDatesList);

                        } else {
                            Toast.makeText(activity, "No Date Found", Toast.LENGTH_LONG).show();
                            Utils.removeCustomProgressDialog();
                        }
                    } else {

                        Utils.removeCustomProgressDialog();
                        //Utils.showAlert(activity, activity.getResources().getString(R.string.text_error_no_scenemarks));
                    }
                }

                @Override
                public void onFailure(Call<GetSceneMarkManifestResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "EventDatesResponse ---->>>> " + t.toString());
                }
            });
            return eventDatesLiveData;
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_SCENEMARKS_MANIFEST, nodeIds,startTime,endTime,
                    pageLength, returnNiceItemTypes, returnSceneMarksDates,
                    returnPage, niceItemList, eventList, continuationToken, "","", "", "", "");
            return null;
        }
    }


    /** get LiveSceneMarks from SceneMarksManifest api V1.02**/
    public MutableLiveData<ArrayList<SceneMarkResponseCMF>> getCurationLive(AppCompatActivity activity, String sceneMarkURI, final String deviceName, String deviceTimeZone) {
        pHelper = PreferenceHelper.getInstance(activity);
        if(isTokenNotExpired()){
            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();

            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();
            ServiceInterfaces.GetSceneMarks api = ApiClient.getClient(activity,authority).create(ServiceInterfaces.GetSceneMarks.class);
            Call<SceneMarkResponseCMF> call = api.getSceneMarks("Bearer "+ accessToken, sceneMarkURI);

            call.enqueue(new Callback<SceneMarkResponseCMF>() {
                @Override
                public void onResponse(Call<SceneMarkResponseCMF> call, retrofit2.Response<SceneMarkResponseCMF> response) {
                    Log.i("url", "---->>> getSceneMarks" + response.raw().request().url());

                    if (response.body() != null && response.body().getAnalysisList().size() > 0) {

                        Log.i("response body", "---->>>> " + response.body());
                        response.body().setDeviceName(deviceName);
                        response.body().setDeviceTimeZone(deviceTimeZone);
                        if(alertArrayList.isEmpty()){
                            alertArrayList.add(response.body());
                            alertLiveData.setValue(alertArrayList);
                        }else {
                            boolean isExists = false;
                            for (SceneMarkResponseCMF sceneMarkResponseCMF : alertArrayList) {
                                if (sceneMarkResponseCMF.getSceneMarkID().equals(response.body().getSceneMarkID())) {
                                    isExists = true;
                                }
                            }
                            if(!isExists){
                                alertArrayList.add(response.body());
                                alertLiveData.setValue(alertArrayList);
                            }
                        }
//                        if(!alertArrayList.contains(response.body())){
//                            alertArrayList.add(response.body());
//                            alertLiveData.setValue(alertArrayList);
//                        }


                    }else{
                        Utils.removeCustomProgressDialog();
                        // Utils.showAlert(activity, activity.getResources().getString(R.string.text_error_no_scenemarks));
                    }
                }
                @Override
                public void onFailure(Call<SceneMarkResponseCMF> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
            return alertLiveData;
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_CURATION_LIVE, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(),new ArrayList<String>(), "",
                    sceneMarkURI, "", "", deviceName, deviceTimeZone);
        }
        return null;
    }

    /** Get NiceItemTypes List **/
    public MutableLiveData<ArrayList<String>> getNiceItemTypesList(AppCompatActivity activity) {

        pHelper = PreferenceHelper.getInstance(activity);

        if(isTokenNotExpired()) {
            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

            niceItemTypes.clear();

            ServiceInterfaces.GetNiceItemTypeList api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.GetNiceItemTypeList.class);

            Call<ArrayList<String>> call = api.getNiceItemTypes("Bearer " + accessToken,
                    appControlEndPoint.getNetEndPointAppControl().getAPIVersion());

            call.enqueue(new Callback<ArrayList<String>>() {
                @Override
                public void onResponse(Call<ArrayList<String>> call, retrofit2.Response<ArrayList<String>> response) {

                    Log.i("url", "niceItemTypes URL------->>>> " + response.raw().request().url());
                    if (!response.equals("{}") && response != null && response.body() != null) {
                        if (response.body() != null) {

                            Log.d("niceItemTypesList==> ", response.body() + "");
                            if (niceItemTypes.size() > 0) {
                                niceItemTypes.clear();
                            }
                            niceItemTypes.addAll(response.body());
                            niceItemTypesLiveData.setValue(niceItemTypes);

                        }
                    } else {
                        Utils.removeCustomProgressDialog();

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                    t.printStackTrace();
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "EventDatesResponse ---->>>> " + t.toString());

                }
            });
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_NICEITEMTYPES_LIST, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(),new ArrayList<String>(), "", "","", "","", "");
        }
        return niceItemTypesLiveData;
    }

    /** Get NiceItemTypes List **/
    public MutableLiveData<AllItemTypesResponse> getAllTypesList(AppCompatActivity activity) {

        pHelper = PreferenceHelper.getInstance(activity);

        if(isTokenNotExpired()) {
            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

            niceItemTypes.clear();

            ServiceInterfaces.GetAllTypeList api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.GetAllTypeList.class);

            Call<AllItemTypesResponse> call = api.getAllTypes("Bearer " + accessToken,
                    appControlEndPoint.getNetEndPointAppControl().getAPIVersion());

            call.enqueue(new Callback<AllItemTypesResponse>() {
                @Override
                public void onResponse(Call<AllItemTypesResponse> call, retrofit2.Response<AllItemTypesResponse> response) {

                    Log.i("url", "AllItemTypes URL------->>>> " + response.raw().request().url());
                    if (!response.equals("{}") && response != null && response.body() != null) {
                        if (response.body() != null) {

                            Log.d("AllItemTypesList==> ", response.body() + "");

                            niceItemTypes.addAll(response.body().getnICEItemTypes());
                            eventTypes.addAll(response.body().getEventTypes());
                            allTypesLiveData.setValue(response.body());

                        }
                    } else {
                        Utils.removeCustomProgressDialog();

                    }
                }

                @Override
                public void onFailure(Call<AllItemTypesResponse> call, Throwable t) {
                    t.printStackTrace();
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "EventDatesResponse ---->>>> " + t.toString());

                }
            });
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_ALLTYPES_LIST, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(),new ArrayList<String>(), "", "","", "","", "");
        }
        return allTypesLiveData;
    }


    /** get LiveSceneMarks from SceneMarksManifest **/
    public MutableLiveData<SceneMarkResponseCMF> getLiveSceneMarks(AppCompatActivity activity, String sceneMarkURI) {
        pHelper = PreferenceHelper.getInstance(activity);
        if(isTokenNotExpired()) {
            String accessToken = pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();

            String authority = "https://" + pHelper.getAppControlObject().getPayload().getDataEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();
            ServiceInterfaces.GetSceneMarks api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.GetSceneMarks.class);
            Call<SceneMarkResponseCMF> call = api.getSceneMarks("Bearer " + accessToken, sceneMarkURI);

            call.enqueue(new Callback<SceneMarkResponseCMF>() {
                @Override
                public void onResponse(Call<SceneMarkResponseCMF> call, retrofit2.Response<SceneMarkResponseCMF> response) {
                    Log.i("url", "---->>> getSceneMarks" + response.raw().request().url());

                    if (response.body() != null && response.body().getAnalysisList().size() > 0) {

                        Log.i("response body", "---->>>> " + response.body());

                        sceneMarkResponseLive.setValue(response.body());

                    } else {
                        Utils.removeCustomProgressDialog();

                    }
                }

                @Override
                public void onFailure(Call<SceneMarkResponseCMF> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_LIVE_SCENEMARKS, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(),new ArrayList<String>(), "",
                    sceneMarkURI, "", "","", "");
        }
        return sceneMarkResponseLive;
    }
    /** get Privacy Object to decrypt video,audio **/
    public void getPrivacyObject(AppCompatActivity activity, String currentDate, String sceneEncryptionKeyID) {
        Utils.showCustomProgressDialog(activity, "", false);
        pHelper = PreferenceHelper.getInstance(activity);
        if(isTokenNotExpired()) {

            String accessToken = pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAccessToken();
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObjectAccessTokenPayload = new JSONObject();
            JSONObject CMFHeaderObject = new JSONObject();
            try {
                CMFHeaderObject.put(Constants.CMF.Header.VERSION, pHelper.getAppSecurityObject().getVersion());
                CMFHeaderObject.put(Constants.CMF.Header.MESSAGE_TYPE, Constants.CMF.HeaderValue.MESSAGE_TYPE);
                CMFHeaderObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, pHelper.getAppInstanceId());
                //jsonObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, pHelper.getAppSecurityObject().getAppInstanceID());
                CMFHeaderObject.put(Constants.CMF.Header.DESTINATION_END_POINT_ID, pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID());
                CMFHeaderObject.put(Constants.CMF.Header.COMMAND_ID, Constants.CMF.HeaderValue.COMMAND_ID);
                CMFHeaderObject.put(Constants.CMF.Header.COMMAND_TYPE, Constants.CMF.HeaderValue.COMMAND_TYPE_PRIVACY);
                CMFHeaderObject.put(Constants.CMF.Header.DATE_TIME_STAMP, currentDate);
                CMFHeaderObject.put(Constants.CMF.Header.ENCRYPTION_ON, true);

                JSONObject jsonBody = new JSONObject();
                JSONObject jsonCreateBody = new JSONObject();

                jsonCreateBody.put(Constants.CMF.Header.VERSION, pHelper.getAppSecurityObject().getVersion());
                jsonCreateBody.put(Constants.CMF.Header.SCENE_ENCRYPTION_KEY_ID, sceneEncryptionKeyID);
                AppLog.Log("jsonCreateBody => ", jsonCreateBody.toString());

                jsonBody.put(Constants.CMF.Payload.BODY, jsonCreateBody);

//                jsonObject.put(Constants.CMF.Payload.PAYLOAD, jsonBody);
                jsonObjectAccessTokenPayload.put(Constants.CMF.Payload.PAYLOAD_OBJECT, jsonBody);
                jsonObjectAccessTokenPayload.put(Constants.CMF.Header.ACCESS_TOKEN, accessToken);

                jsonObject.put(Constants.CMF.Payload.EndPointX509Certificate,pHelper.getAppSecurityObject().getNICEASEndPoint().getAppEndPoint().getX509Certificate());
                jsonObject.put(Constants.CMF.Payload.CMF_HEADER,CMFHeaderObject.toString());
                jsonObject.put(Constants.CMF.Payload.ACCESSTOKEN_PAYLOAD,jsonObjectAccessTokenPayload);

                AppLog.Log("jsonObjectMain => ", jsonObject.toString());



//                if(pHelper.getSignInMode() == Constants.STAGING_SIGN_IN){
                    String encryptedPayload = Utils.encryptAndSignCMF(activity, jsonObject,
                            jsonObjectAccessTokenPayload.toString());

                    JSONObject jsonObjectRequest = new JSONObject();
                    jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_KEY, PreferenceHelper.getInstance(activity).getPublicKeyRSA());
                    jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_PAYLOAD,encryptedPayload);

                    ServiceInterfaces.GetPrivacyObjectEncrypted api = ApiClient.getClientAccount(activity, "https://" +
                            pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetPrivacyObjectEncrypted.class);

                    Call<EncryptedCMFResponse> call = api.getPrivacyObject("Bearer "+ accessToken,
                            pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                            pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                            ApiClient.makeJSONRequestBody(jsonObjectRequest));


                    call.enqueue(new Callback<EncryptedCMFResponse>() {
                        @Override
                        public void onResponse(Call<EncryptedCMFResponse> call, retrofit2.Response<EncryptedCMFResponse> response) {
                            Log.i(TAG, "---->>> GetPrivacy " + response.raw().request().url());

                              Utils.removeCustomProgressDialog();

                            if (!response.equals("{}") && response != null && response.body() != null) {
                                try {
                                    String encryptedPayload = response.body().getEncryptedPayload();
                                    GetPrivaceObjectResponse getPrivaceObjectResponse = Utils.decryptAndValidateCMF2(activity, encryptedPayload);
                                    AppLog.Log("getPrivaceObjectResponse","****"+new Gson().toJson(getPrivaceObjectResponse));
                                    keyEncrypted = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getK();
                                    pHelper.putSceneEncryptionID(keyEncrypted); // SceneEncryptionID
                                    ivEncrypted = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getIv();
                                    keyId = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getKid();
                                    pHelper.putIvBytes(ivEncrypted);  // IvBytes
                                    pHelper.putKid(keyId); // KeyID
                                    privacyObjectLiveData.setValue(getPrivaceObjectResponse);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<EncryptedCMFResponse> call, Throwable t) {
                            Utils.removeCustomProgressDialog();
                            Log.i("onFailure", "---->>>> " + t.toString());
                        }
                    });
//                }else {
//                    ServiceInterfaces.GetPrivacyObject api = ApiClient.getClientAccount(activity, "https://" +
//                            pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetPrivacyObject.class);
//
//                    Call<GetPrivaceObjectResponse> call = api.getPrivacyObject("Bearer "+ accessToken,pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
//                            pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
//                            ApiClient.makeJSONRequestBody(jsonObject));
//
//                    call.enqueue(new Callback<GetPrivaceObjectResponse>() {
//                        @Override
//                        public void onResponse(Call<GetPrivaceObjectResponse> call, Response<GetPrivaceObjectResponse> response) {
//                            Utils.removeCustomProgressDialog();
//                            GetPrivaceObjectResponse getPrivaceObjectResponse = response.body();
//                            AppLog.Log("getPrivaceObjectResponse","****"+new Gson().toJson(getPrivaceObjectResponse));
//                            keyEncrypted = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getK();
//                            pHelper.putSceneEncryptionID(keyEncrypted); // SceneEncryptionID
//                            ivEncrypted = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getIv();
//                            keyId = getPrivaceObjectResponse.getPayload().getSceneEncryptionKey().getKid();
//                            pHelper.putIvBytes(ivEncrypted);  // IvBytes
//                            pHelper.putKid(keyId); // KeyID
//                            privacyObjectLiveData.setValue(getPrivaceObjectResponse);
//                        }
//
//                        @Override
//                        public void onFailure(Call<GetPrivaceObjectResponse> call, Throwable t) {
//                            Utils.removeCustomProgressDialog();
//                        }
//                    });
//                }

            } catch (JSONException e) {
                e.printStackTrace();
                Utils.removeCustomProgressDialog();
            }
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_PRIVACY_OBJECT, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(),new ArrayList<String>(), "",
                    "", currentDate, sceneEncryptionKeyID,"", "");
        }
    }
    /** get AppControlObject to refresh the token when expires **/
    public void getAppControlObject(AppCompatActivity activity, AppSecurityObjectResponse appSecurityObject,  String currentDate) {
        Utils.showCustomProgressDialog(activity, "", false);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.CMF.Header.VERSION, appSecurityObject.getVersion());
            jsonObject.put(Constants.CMF.Header.MESSAGE_TYPE, Constants.CMF.HeaderValue.MESSAGE_TYPE);
            jsonObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, appSecurityObject.getAppInstanceID());
            jsonObject.put(Constants.CMF.Header.DESTINATION_END_POINT_ID, appSecurityObject.getNICEASEndPoint().getNetEndPoint().getEndPointID());
            jsonObject.put(Constants.CMF.Header.DATE_TIME_STAMP, currentDate);
            jsonObject.put(Constants.CMF.Header.COMMAND_ID, Constants.CMF.HeaderValue.COMMAND_ID);
            jsonObject.put(Constants.CMF.Header.COMMAND_TYPE, Constants.CMF.HeaderValue.COMMAND_TYPE);

            JSONObject jsonPayLoad = new JSONObject();
            String appId = appSecurityObject.getAppID();

            jsonPayLoad.put(Constants.Params.APP_ID, appId);

            jsonObject.put(Constants.CMF.Payload.PAYLOAD, jsonPayLoad);
            AppLog.Log("jsonObjectMain => ", jsonObject.toString());


            String accessToken = pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAccessToken();

            String encryptedPayload = Utils.encryptAndSignCMF(activity, jsonObject,
                    Base64.getUrlEncoder().encodeToString(accessToken.getBytes()) + "." +
                            Base64.getUrlEncoder().encodeToString(jsonPayLoad.toString().getBytes()));

            JSONObject jsonObjectRequest = new JSONObject();
            jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_KEY, PreferenceHelper.getInstance(activity).getPublicKeyRSA());
            jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_PAYLOAD,encryptedPayload);

            ServiceInterfaces.GetAppControlObjectEncrypted api = ApiClient.getClientAccount(activity,"https://" +
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObjectEncrypted.class);

            Call<EncryptedCMFResponse> call = api.getAppControlObject("Bearer "+ accessToken,
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

                        String encryptedPayload = response.body().getEncryptedPayload();
                        AppConrolObjectResponse appConrolObjectResponse = Utils.decryptAndValidateCMF(activity, encryptedPayload);
                        AppLog.Log("appConrolObjectResponse","****"+new Gson().toJson(appConrolObjectResponse));
                        pHelper.putAppControlObject(appConrolObjectResponse);
                        String token = appConrolObjectResponse.getPayload().getDataEndPoints().get(0).
                                getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                        JWT jwt = new JWT(token);
                        pHelper.putExpiryDate(jwt.getExpiresAt().getTime());
                        pHelper.putNotBeforeDate(jwt.getNotBefore().getTime());
                        appConrolObjectLiveData.setValue(appConrolObjectResponse);
                    }
                }

                @Override
                public void onFailure(Call<EncryptedCMFResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private boolean isTokenNotExpired(){
        if(pHelper.getAppControlObject() != null && appControlEndPoint == null) {
            appControlEndPoint = pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0);
            for (ControlEndPoint endPoint : pHelper.getAppControlObject().getPayload().getControlEndPoints()) {
                if (TextUtils.equals(endPoint.getEndPointType(), "Account Service")) {
                    appControlEndPoint = endPoint;
                } else if (TextUtils.equals(endPoint.getEndPointType(), "SceneMode Source")) {
                    sceneModeControlEndPoint = endPoint;
                    strSceneModeToken = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getSchemeAppControlObject().get(0).getAccessToken();
                    strSceneModeEndPoint = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getEndPointID();
                    strSceneModeAuthority = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getSchemeAppControlObject().get(0).getAuthority();
                }
            }

        }
        Date currentDate = new Date();
        return (currentDate.getTime() > pHelper.getNotBeforeDate() && currentDate.getTime() < pHelper.getExpiryDate());
    }

    private void getAppControlObject(AppSecurityObjectResponse appSecurityObject,
                                     AppCompatActivity activity, int method, ArrayList<String> nodeIds,String startTime, String endTime,
                                     int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                     boolean returnPage, ArrayList<String> niceItemList, ArrayList<String> eventList,String continuationToken,
                                     String sceneMarkURI, String currentDateString, String sceneEncryptionKeyID,
                                     String deviceName, String deviceTimeZone) {
        Utils.showCustomProgressDialog(activity, "", false);
        this.pHelper = PreferenceHelper.getInstance(activity);
        Date today = new Date();
        currentDate =  format.format(today);
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
            String appInstanceID = appSecurityObject.getAppInstanceID();

            jsonPayLoad.put(Constants.Params.APP_ID, appInstanceID);

            jsonObjectAccessTokenPayload.put(Constants.CMF.Payload.PAYLOAD_OBJECT, jsonPayLoad);
            jsonObjectAccessTokenPayload.put(Constants.CMF.Header.ACCESS_TOKEN, accessToken);


            jsonObject.put(Constants.CMF.Payload.EndPointX509Certificate,appSecurityObject.getNICEASEndPoint().getAppEndPoint().getX509Certificate());
            jsonObject.put(Constants.CMF.Payload.CMF_HEADER,CMFHeaderObject.toString());
            jsonObject.put(Constants.CMF.Payload.ACCESSTOKEN_PAYLOAD,jsonObjectAccessTokenPayload);

            AppLog.Log("jsonObjectMain => ", jsonObject.toString());
//            if(pHelper.getSignInMode() == Constants.STAGING_SIGN_IN){

                String encryptedPayload = Utils.encryptAndSignCMF(activity, jsonObject,
                                jsonObjectAccessTokenPayload.toString());

                JSONObject jsonObjectRequest = new JSONObject();
                jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_KEY, PreferenceHelper.getInstance(activity).getPublicKeyRSA());
                jsonObjectRequest.put(Constants.CMF.Payload.ENCRYPTED_PAYLOAD,encryptedPayload);

                ServiceInterfaces.GetAppControlObjectEncrypted api = ApiClient.getClientAccount(activity,"https://" +
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObjectEncrypted.class);

                Call<EncryptedCMFResponse> call = api.getAppControlObject("Bearer "+ accessToken,
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                        Constants.CODE_GET_APP_CONTROL_REQUEST,
                        ApiClient.makeJSONRequestBody(jsonObjectRequest));




                call.enqueue(new Callback<EncryptedCMFResponse>() {
                    @Override
                    public void onResponse(Call<EncryptedCMFResponse> call, retrofit2.Response<EncryptedCMFResponse> response) {
                        Log.i(TAG, "---->>> AppControl " + response.raw().request().url());
                        Gson gson = new Gson();
                        Log.i(TAG, "---->>> AppControl-RESPONSE " + gson.toJson(response.body()));

                        Utils.removeCustomProgressDialog();

                        if (!response.equals("{}") && response != null && response.body() != null) {
                            String encryptedPayload = response.body().getEncryptedPayload();
                            AppConrolObjectResponse appConrolObjectResponse = Utils.decryptAndValidateCMF(activity, encryptedPayload);
                            AppLog.Log("appConrolObjectResponse","****"+new Gson().toJson(appConrolObjectResponse));
                            pHelper.putAppControlObject(appConrolObjectResponse);
                            appControlEndPoint = appConrolObjectResponse.getPayload().getControlEndPoints().get(0);
                            for (ControlEndPoint endPoint : appConrolObjectResponse.getPayload().getControlEndPoints()){
                                if(TextUtils.equals(endPoint.getEndPointType(),"Account Service")){
                                    appControlEndPoint = endPoint;
                                }else if(TextUtils.equals(endPoint.getEndPointType(),"SceneMode Source")){
                                    sceneModeControlEndPoint = endPoint;
                                    strSceneModeToken = sceneModeControlEndPoint.getNetEndPointAppControl()
                                            .getSchemeAppControlObject().get(0).getAccessToken();
                                    strSceneModeEndPoint = sceneModeControlEndPoint.getNetEndPointAppControl()
                                            .getEndPointID();
                                    strSceneModeAuthority = sceneModeControlEndPoint.getNetEndPointAppControl()
                                            .getSchemeAppControlObject().get(0).getAuthority();
                                }
                            }
                            String token = appConrolObjectResponse.getPayload().getDataEndPoints().get(0).
                                    getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                            if(!TextUtils.isEmpty(token)){
                                String[] strArray = token.split("\\.");
                                String JWTPayload = new String(org.jose4j.base64url.internal.apache.
                                        commons.codec.binary.Base64.decodeBase64(strArray[1]));

                                try {
                                    JSONObject jsonObject = new JSONObject(JWTPayload);
                                    AppLog.Log("EXPIRY_DATE","****"+jsonObject.getString("exp"));
                                    AppLog.Log("NOT_BEFORE_DATE","****"+jsonObject.getString("nbf"));
                                    pHelper.putExpiryDate(jsonObject.getLong("exp") * 1000);
                                    pHelper.putNotBeforeDate(jsonObject.getLong("nbf") * 1000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            pHelper.putEmail("test");
                            switch (method){
                                case Constants.Method.GET_SCENEMARKS_MANIFEST:
                                    getSceneMarksManifest(activity, nodeIds, startTime, endTime,
                                            pageLength, returnNiceItemTypes, returnSceneMarksDates,
                                            returnPage, niceItemList,eventList, continuationToken);
                                    break;
                                case Constants.Method.GET_EVENT_DATES:
                                    getEventDates(activity, nodeIds, startTime, endTime,
                                            pageLength, returnNiceItemTypes, returnSceneMarksDates,
                                            returnPage, niceItemList, eventList, continuationToken);
                                    break;
                                case Constants.Method.GET_NICEITEMTYPES_LIST:
                                    getNiceItemTypesList(activity);
                                    break;
                                case Constants.Method.GET_ALLTYPES_LIST:
                                    getAllTypesList(activity);
                                    break;
                                case Constants.Method.GET_LIVE_SCENEMARKS:
                                    getLiveSceneMarks(activity, sceneMarkURI);
                                    break;
                                case Constants.Method.GET_NODE_LIST:
                                    getNodeList(activity);
                                    break;
                                case Constants.Method.GET_PRIVACY_OBJECT:
                                    getPrivacyObject(activity, currentDateString, sceneEncryptionKeyID);
                                    break;
                                case Constants.Method.GET_CURATION_LIVE:
                                    getCurationLive(activity, sceneMarkURI, deviceName, deviceTimeZone);
                                    break;
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<EncryptedCMFResponse> call, Throwable t) {
                        Utils.removeCustomProgressDialog();
                        Log.i("onFailure", "---->>>> " + t.toString());
                    }
                });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getSceneMode(AppSecurityObjectResponse appSecurityObject, AppCompatActivity activity) {
        Utils.showCustomProgressDialog(activity, "", false);
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:" + "000000");
        currentDate =  format.format(today);
        pHelper = PreferenceHelper.getInstance(activity);
        if(pHelper.getAppControlObject() != null && appControlEndPoint == null) {
            appControlEndPoint = pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0);
            for (ControlEndPoint endPoint : pHelper.getAppControlObject().getPayload().getControlEndPoints()) {
                if (TextUtils.equals(endPoint.getEndPointType(), "Account Service")) {
                    appControlEndPoint = endPoint;
                } else if (TextUtils.equals(endPoint.getEndPointType(), "SceneMode Source")) {
                    sceneModeControlEndPoint = endPoint;
                    strSceneModeToken = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getSchemeAppControlObject().get(0).getAccessToken();
                    strSceneModeEndPoint = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getEndPointID();
                    strSceneModeAuthority = sceneModeControlEndPoint.getNetEndPointAppControl()
                            .getSchemeAppControlObject().get(0).getAuthority();
                }
            }

        }
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectAccessTokenPayload = new JSONObject();
        JSONObject CMFHeaderObject = new JSONObject();

        try {
            CMFHeaderObject.put(Constants.CMF.Header.VERSION, sceneModeControlEndPoint.getNetEndPointAppControl().getAPIVersion());
            CMFHeaderObject.put(Constants.CMF.Header.MESSAGE_TYPE, Constants.CMF.HeaderValue.MESSAGE_TYPE);
            CMFHeaderObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, appSecurityObject.getAppInstanceID() + "_0001");
            CMFHeaderObject.put(Constants.CMF.Header.DESTINATION_END_POINT_ID, strSceneModeEndPoint);
            CMFHeaderObject.put(Constants.CMF.Header.DATE_TIME_STAMP, currentDate);
            CMFHeaderObject.put(Constants.CMF.Header.COMMAND_ID, Constants.CMF.HeaderValue.COMMAND_ID);
            CMFHeaderObject.put(Constants.CMF.Header.COMMAND_TYPE, "/1.0/" + strSceneModeEndPoint + "/management/GetSceneMode");

            JSONObject jsonPayLoad = new JSONObject();
            String appInstanceID = appSecurityObject.getAppInstanceID();

            jsonPayLoad.put(Constants.Params.NODE_ID, appInstanceID + "_0001");

            jsonObjectAccessTokenPayload.put(com.scenera.nicesecurityapplib.utilities.Constants.CMF.Payload.PAYLOAD_OBJECT, jsonPayLoad);
            jsonObjectAccessTokenPayload.put(com.scenera.nicesecurityapplib.utilities.Constants.CMF.Header.ACCESS_TOKEN, strSceneModeToken);

            jsonObject.put(com.scenera.nicesecurityapplib.utilities.Constants.CMF.Payload.EndPointX509Certificate,appSecurityObject.getNICEASEndPoint().getAppEndPoint().getX509Certificate());
            jsonObject.put(com.scenera.nicesecurityapplib.utilities.Constants.CMF.Payload.CMF_HEADER,CMFHeaderObject.toString());
            jsonObject.put(com.scenera.nicesecurityapplib.utilities.Constants.CMF.Payload.ACCESSTOKEN_PAYLOAD,jsonObjectAccessTokenPayload);
            AppLog.Log("jsonObjectMain => ", jsonObject.toString());



            String encryptedPayload = Utils.encryptAndSignCMF(activity, jsonObject,
                    jsonObjectAccessTokenPayload.toString());

            JSONObject jsonObjectRequest = new JSONObject();
            jsonObjectRequest.put("EncryptionKey", PreferenceHelper.getInstance(activity).getPublicKeyRSA());
            jsonObjectRequest.put("EncryptedPayload",encryptedPayload);
            ServiceInterfaces.GetSceneMode api = ApiClient.getClientAccount(activity,"https://" + strSceneModeAuthority).create(ServiceInterfaces.GetSceneMode.class);

            Call<EncryptedCMFResponse> call = api.getSceneMode("Bearer " + strSceneModeToken,
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                    strSceneModeEndPoint,
                    ApiClient.makeJSONRequestBody(jsonObjectRequest));


            call.enqueue(new Callback<EncryptedCMFResponse>() {
                @Override
                public void onResponse(Call<EncryptedCMFResponse> call, retrofit2.Response<EncryptedCMFResponse> response) {
                    Log.i(TAG, "---->>> AppControl " + response.raw().request().url());
                    Gson gson = new Gson();
                    Log.i(TAG, "---->>> AppControl-RESPONSE " + gson.toJson(response.body()));

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {


                        String encryptedPayload = response.body().getEncryptedPayloadSceneMode();
                        JSONObject appConrolObjectResponse = Utils.decryptAndValidateCMFGetSceneMode(activity, encryptedPayload);
                        AppLog.Log("GET_SCENEMODE", "****" + new Gson().toJson(appConrolObjectResponse));
                        GetSceneModeResponse getSceneModeResponse = new Gson().fromJson(appConrolObjectResponse.toString(),
                                GetSceneModeResponse.class);
                        for (ControlEndPoint controlEndPoint : getSceneModeResponse.getPayload().getMode().getSceneMarkOutputList()) {
                            strGlobalSceneMarkEndPoint = controlEndPoint.getNetEndPointAppControl().getEndPointID();
                            for (SchemeAppControlObject schemeAppControlObject : controlEndPoint.getNetEndPointAppControl().getSchemeAppControlObject()) {
                                strGlobalSceneMarkToken = schemeAppControlObject.getAccessToken();
                                strGlobalSceneMarkAuthority = schemeAppControlObject.getAuthority();
                                break;
                            }
                            break;
                        }
                        PrivacyServerEndPoint privacyServerEndPoint = new PrivacyServerEndPoint();
                        NetEndPointPrivacy netEndPointPrivacy = new NetEndPointPrivacy();
                        List<SchemePrivacy> schemePrivacy = new ArrayList<>();
                        netEndPointPrivacy.setScheme(schemePrivacy);
                        privacyServerEndPoint.setNetEndPoint(netEndPointPrivacy);

                        JSONObject encryptionDictDefualt = new JSONObject();
                        try {
                            encryptionDictDefualt.put("EncryptionOn", false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        EncryptionImage = new Encryption(false, "",
                                privacyServerEndPoint, encryptionDictDefualt);
                        EncryptionVideo = new Encryption(false, "",
                                privacyServerEndPoint, encryptionDictDefualt);

                        for (Output output : getSceneModeResponse.getPayload().getOutputs()) {
                            if (output.getType().equals("Video")) {
//                                Utils.EncryptionVideo = output.getEncryption();
                                for (ControlEndPoint controlEndPoint : output.getDestinationEndPointList()) {
                                    strGlobalSceneDataVideoEndPoint = controlEndPoint.getNetEndPointAppControl().getEndPointID();
                                    for (SchemeAppControlObject schemeAppControlObject : controlEndPoint.getNetEndPointAppControl().getSchemeAppControlObject()) {
                                        strGlobalSceneDataVideoToken = schemeAppControlObject.getAccessToken();
                                        strGlobalSceneDataVideoAuthority = schemeAppControlObject.getAuthority();
                                        break;
                                    }
                                    break;
                                }
                            }
                            if (output.getType().equals("Image")) {
//                                Utils.EncryptionImage = output.getEncryption();
                                for (ControlEndPoint controlEndPoint : output.getDestinationEndPointList()) {
                                    strGlobalSceneDataImageEndPoint = controlEndPoint.getNetEndPointAppControl().getEndPointID();
                                    for (SchemeAppControlObject schemeAppControlObject : controlEndPoint.getNetEndPointAppControl().getSchemeAppControlObject()) {
                                        strGlobalSceneDataImageToken = schemeAppControlObject.getAccessToken();
                                        strGlobalSceneDataImageAuthority = schemeAppControlObject.getAuthority();
                                        break;
                                    }
                                    break;
                                }

                            }


                        }

                    }
                }

                @Override
                public void onFailure(Call<EncryptedCMFResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /** add token to server to get notification of particular node **/
    public void addTokenToServer(AppCompatActivity activity, String nodeID, String devideToken, String applicationId) {
        Utils.showCustomProgressDialog(activity, "", false);
        pHelper = PreferenceHelper.getInstance(activity);

        String authority = "http://" + pHelper.getAppControlObject().getPayload().
                getNotificationEndPoints().get(0).getNetEndPointAppControl().
                getSchemeAppControlObject().get(0).getAuthority();
        ServiceInterfaces.AddDeviceToken api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.AddDeviceToken.class);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.Params.DEVICE_TOKEN, devideToken);
            jsonObject.put(Constants.Params.DEVICE_TYPE, Constants.DEVICE_TYPE);
            jsonObject.put(Constants.Params.APPLICATION_ID, applicationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseBody> call = api.addDeviceToken(Constants.API,
                appControlEndPoint.getNetEndPointAppControl().getAPIVersion(),
                nodeID, ApiClient.makeJSONRequestBody(jsonObject));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.i("url", "---->>> AddDeviceToken " + response.raw().request().url());

                Utils.removeCustomProgressDialog();

                if (!response.equals("{}") && response != null && response.body() != null) {
                    isTokenAdded.setValue(true);
                    AppLog.Log(TAG, "Token Added Successfully ");

                }else {
                    isTokenAdded.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.removeCustomProgressDialog();
                Log.i("onFailure", "---->>>> " + t.toString());
            }
        });


    }

    /** remove token from server to stop notification of particular node **/
    public void removeTokenFromServer(AppCompatActivity activity, String nodeID, String devideToken, String applicationId) {
        Utils.showCustomProgressDialog(activity, "", false);
        pHelper = PreferenceHelper.getInstance(activity);

        String authority = "http://" + pHelper.getAppControlObject().getPayload().
                getNotificationEndPoints().get(0).getNetEndPointAppControl().
                getSchemeAppControlObject().get(0).getAuthority();
        ServiceInterfaces.DeleteDeviceToken api = ApiClient.getClient(activity, authority).create(ServiceInterfaces.DeleteDeviceToken.class);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.Params.DEVICE_TOKEN, devideToken);
            jsonObject.put(Constants.Params.DEVICE_TYPE, Constants.DEVICE_TYPE);
            jsonObject.put(Constants.Params.APPLICATION_ID, applicationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseBody> call = api.deleteDeviceToken(Constants.API,
                appControlEndPoint.getNetEndPointAppControl().getAPIVersion(),
                nodeID, ApiClient.makeJSONRequestBody(jsonObject));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.i("url", "---->>> DeletedDeviceToken " + response.raw().request().url());

                Utils.removeCustomProgressDialog();

                if (!response.equals("{}") && response != null && response.body() != null) {
                    isTokenRemoved.setValue(true);
                    AppLog.Log(TAG, "Token Deleted Successfully ");
                }else {
                    isTokenRemoved.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.removeCustomProgressDialog();
                Log.i("onFailure", "---->>>> " + t.toString());
            }
        });
    }

    public void addFaceDatabaseApi(AppCompatActivity activity, String name, String currentPhotoPath) {
        pHelper = PreferenceHelper.getInstance(activity);
        Utils.showCustomProgressDialog(activity, "", false);

        String authority = "http://" + pHelper.getAppControlObject().getPayload().
                getNotificationEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

        ServiceInterfaces.AddFaceDatabaseApi api = ApiClient.getClient(activity, authority).
                create(ServiceInterfaces.AddFaceDatabaseApi.class);
        HashMap<String, RequestBody> hashMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        hashMap.put(Constants.Params.PERSON_NAME, ApiClient.makeTextRequestBody
                (name));
        hashMap.put(Constants.Params.GROUP_NAME, ApiClient.makeTextRequestBody
                (pHelper.getAppSecurityObject().getAccountID()));
        hashMap.put(Constants.Params.PERSON_GROUP_ID, ApiClient.makeTextRequestBody
                (pHelper.getAppSecurityObject().getAccountID()));
        Call<AddFaceResponse> call = api.addFaceDatabaseApi(ApiClient.makeMultipartRequestBody(
                activity, currentPhotoPath, Constants.Params.IMAGE_URL), hashMap);

        call.enqueue(new Callback<AddFaceResponse>() {
            @Override
            public void onResponse(Call<AddFaceResponse> call, retrofit2.Response<AddFaceResponse> response) {

                Utils.removeCustomProgressDialog();

                if (!response.equals("{}") && response != null && response.body() != null) {
                    if(response.body().isSuccess() && response.body().getMessage().equalsIgnoreCase("Uploaded")){
                        isFaceAdded.setValue(true);
                    }else {
                       isFaceAdded.setValue(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddFaceResponse> call, Throwable t) {
                Utils.removeCustomProgressDialog();
                Log.i("onFailure", "---->>>> " + t.toString());
            }
        });

    }

    public void changeFaceDatabaseApi(AppCompatActivity activity, String personId, boolean isChecked, Switch swFace) {
        pHelper = PreferenceHelper.getInstance(activity);
        Utils.showCustomProgressDialog(activity, "", false);

        String authority = "http://" + pHelper.getAppControlObject().getPayload().
                getNotificationEndPoints().get(0).getNetEndPointAppControl().
                getSchemeAppControlObject().get(0).getAuthority();


        ServiceInterfaces.ChangeFaceDatabaseApi api = ApiClient.getClient(activity, authority).
                create(ServiceInterfaces.ChangeFaceDatabaseApi.class);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(Constants.Params.PERSON_ID, personId);
            jsonObject.put(Constants.Params.STATUS, isChecked);
            Call<AddFaceResponse> call = api.changeFaceDatabaseApi(ApiClient.makeJSONRequestBody(jsonObject));



            call.enqueue(new Callback<AddFaceResponse>() {
                @Override
                public void onResponse(Call<AddFaceResponse> call, retrofit2.Response<AddFaceResponse> response) {

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {
                        if(response.body().isSuccess()){
                            isFaceChanged.setValue(true);
                        }else {
                            isFaceChanged.setValue(false);
                            swFace.setChecked(!isChecked);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddFaceResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    isFaceChanged.setValue(false);
                    swFace.setChecked(!isChecked);
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        }catch (Exception e){

        }
    }

    public void getFaceDatabaseApi(AppCompatActivity activity) {

        Utils.showCustomProgressDialog(activity, "", false);
        pHelper = PreferenceHelper.getInstance(activity);
        String authority = "http://" + pHelper.getAppControlObject().getPayload().getNotificationEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();


        ServiceInterfaces.GetFaceDatabaseApi api = ApiClient.getClient(activity, authority)
                .create(ServiceInterfaces.GetFaceDatabaseApi.class);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(Constants.Params.PERSON_GROUP_ID, pHelper.getAppSecurityObject().getAccountID());
            Call<GetFaceDataResponse> call = api.getFaceDatabaseApi(ApiClient.makeJSONRequestBody(jsonObject));



            call.enqueue(new Callback<GetFaceDataResponse>() {
                @Override
                public void onResponse(Call<GetFaceDataResponse> call, retrofit2.Response<GetFaceDataResponse> response) {

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {
                        if(response.body().isSuccess()){
                            facesLiveData.setValue((ArrayList<PersonFace>) response.body().getFaces());
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetFaceDataResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void clearDetectedObjectList(Context activityContext){
        Utils.showCustomProgressDialog(activityContext,"",false);
        if(listDetectedObjects != null)
            listDetectedObjects.clear();
        Utils.removeCustomProgressDialog();
    }

//    public static List<DetectedObjectsClass> getDetectedObjectList(){
//        return listDetectedObjects;
//    }

    public void updateDetectedObjectList(Context activityContext,String strBase64OfSceneData,
                                                String label, double probability, int xmin,
                                                int ymin, int xmax, int ymax,
                                                int width, int height){
        Utils.showCustomProgressDialog(activityContext,"",false);
        if(listDetectedObjects == null)
            listDetectedObjects = new ArrayList<>();
        strBase64OfSceneDataImage = strBase64OfSceneData;

        iGlobalImageWidth = width;
        iGlobalImageHeight = height;

        int iSceneDataInstance = Utils.createRandomNumber();
        String strDetectedObjectSceneDataID = createSceneDataID(iSceneDataInstance);
        DetectedObjectsClass detectedObjectsClass = new DetectedObjectsClass();
        detectedObjectsClass.setStrSceneDataID(strDetectedObjectSceneDataID);
        detectedObjectsClass.setStrBase64OfSceneData(strBase64OfSceneData);
        detectedObjectsClass.setLabel(label);
        detectedObjectsClass.setProbability(probability);
        detectedObjectsClass.setXmin(xmin);
        detectedObjectsClass.setYmin(ymin);
        detectedObjectsClass.setXmax(xmax);
        detectedObjectsClass.setYmax(ymax);
        detectedObjectsClass.setWidth(xmax - xmin);
        detectedObjectsClass.setHeight(ymax - ymin);

        listDetectedObjects.add(detectedObjectsClass);
        Utils.removeCustomProgressDialog();
    }

    public static String createSceneMarkID(int instance){

        String hexInstance = Integer.toHexString(instance);
        String subString = hexInstance;
        subString = subString.substring(2);
        String hexInstancePadded = ("00000000" + subString).substring(subString.length());
        strGlobalDeviceNodeID = Utils.getDeviceNodeID(strGlobalBrigdeUUID,NodeID);
        String sceneMarkID = "SMK_" + strGlobalDeviceNodeID + "_" + hexInstancePadded;
        return sceneMarkID;
    }

    public static String createSceneDataID(int instance){

        String hexInstance = Integer.toHexString(instance);
        String subString = hexInstance;
        subString = subString.substring(2);
        String hexInstancePadded = ("00000000" + subString).substring(subString.length());
        strGlobalDeviceNodeID = Utils.getDeviceNodeID(strGlobalBrigdeUUID,NodeID);
        String sceneDataID = "SDT_" + strGlobalDeviceNodeID + "_" + hexInstancePadded;
        return sceneDataID;
    }

    public void createVideoSceneDataID(Context activityContext,
                                       int iVideoDurationSec, int iVideoWidth, int iVideoHeight){
        Utils.showCustomProgressDialog(activityContext,"",false);
        iVideoRecordingDurationSec = iVideoDurationSec;
        iGlobalVideoWidth = iVideoWidth;
        iGlobalVideoHeight = iVideoHeight;

        int iSceneDataInstance = Utils.createRandomNumber();
        strGlobalVideoSceneDataID = createSceneDataID(iSceneDataInstance);
        listRelatedSceneMarksToVideo = new ArrayList<>();
        Utils.removeCustomProgressDialog();
    }


    public static void sendSceneMark(Context activityContext){
        Utils.showCustomProgressDialog(activityContext,"",false);
        context = (BaseActivity)activityContext;
        int iSceneDataInstance = Utils.createRandomNumber();
        strSceneMarkID = createSceneMarkID(iSceneDataInstance);

        iSceneDataInstance = Utils.createRandomNumber();
        strFullImageSceneDataID = createSceneDataID(iSceneDataInstance);

        CreateAndSendSceneMark(strSceneMarkID,  strFullImageSceneDataID,
                iGlobalImageWidth, iGlobalImageHeight, listDetectedObjects,
                strGlobalVideoSceneDataID, iVideoRecordingDurationSec,
                iGlobalVideoWidth, iGlobalVideoHeight );
        listRelatedSceneMarksToVideo.add(strSceneMarkID);
    }

    private static void CreateAndSendSceneMark(String strSceneMarkID,  String strFullImageSceneDataID,
                                               int iImageWidth, int iImageHeight,
                                               List<DetectedObjectsClass> listDetectedObjects ,
                                               String strVideoSceneDataID, int iVideoDurationSec,
                                               int iVideoWidth, int iVideoHeight){
        PrivacyServerEndPoint privacyServerEndPoint = new PrivacyServerEndPoint();
        NetEndPointPrivacy netEndPointPrivacy = new NetEndPointPrivacy();
        List<SchemePrivacy> schemePrivacy = new ArrayList<>();
        netEndPointPrivacy.setScheme(schemePrivacy);
        privacyServerEndPoint.setNetEndPoint(netEndPointPrivacy);

        JSONObject encryptionDictDefualt = new JSONObject();
        try {
            encryptionDictDefualt.put("EncryptionOn",false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EncryptionImage = new Encryption(false,"",
                privacyServerEndPoint,encryptionDictDefualt);
        EncryptionVideo = new Encryption(false,"",
                privacyServerEndPoint,encryptionDictDefualt);


        SceneMarkValues sceneMarkValues = new SceneMarkValues();
        sceneMarkValues.setSceneMarkID(strSceneMarkID);
        sceneMarkValues.setNodeID(strGlobalDeviceNodeID);
        sceneMarkValues.setPortID(strGlobalDevicePortID);
        sceneMarkValues.setVersion("1.0");
        sceneMarkValues.setDestinationID(strGlobalSceneMarkEndPoint);
        sceneMarkValues.setSceneMarkStatus("Active");
//        sceneMarkValues.setSceneMode(objGlobalGetSceneMode.Mode.SceneMode);
        sceneMarkValues.setSceneMode("Label");
        sceneMarkValues.setEventType("ItemPresence");
        sceneMarkValues.setStatus("Upload in Progress");
        sceneMarkValues.setCustomAnalysisID("");
        sceneMarkValues.setAnalysisDescription("Yolo v3 configured to detect Human");
        sceneMarkValues.setProcessingStatus("Detected");
        sceneMarkValues.setAlgorithmID("12345678-1234-1234-1234-123456789abc");

        objThumbnailSceneData = new SceneDataClass();

        sceneMarkValues.setCustomItemType("");

        for(DetectedObjectsClass detectedObjectsClass : listDetectedObjects){
            if(TextUtils.equals(detectedObjectsClass.getLabel(), "person"))
                sceneMarkValues.setNICEItemType("Human");
            else if(TextUtils.equals(detectedObjectsClass.getLabel(), "car") ||
                    TextUtils.equals(detectedObjectsClass.getLabel(), "truck"))
                sceneMarkValues.setNICEItemType("Vehicle");
            else{
                sceneMarkValues.setNICEItemType("Custom");
                sceneMarkValues.setCustomItemType(detectedObjectsClass.getLabel());
            }
            sceneMarkValues.setResolution_Height(detectedObjectsClass.getYmax()
                    - detectedObjectsClass.getYmin());
            sceneMarkValues.setResolution_Width(detectedObjectsClass.getXmax()
                    - detectedObjectsClass.getXmin());
            sceneMarkValues.setXCoordinate(detectedObjectsClass.getXmin());
            sceneMarkValues.setYCoordinate(detectedObjectsClass.getYmin());
            sceneMarkValues.setProbability(detectedObjectsClass.getProbability());

            sceneMarkValues.setThumbnail_SceneDataID(detectedObjectsClass.getStrSceneDataID());
            sceneMarkValues.setDetectedObjects_ThumbnailSceneDataID(detectedObjectsClass.getStrSceneDataID());
            sceneMarkValues.setDetectedObjects_Thumbnail_dictEncryption(EncryptionImage);
            break;
        }

        sceneMarkValues.setDetectedObjects_Image_SceneDataID(strFullImageSceneDataID);
        sceneMarkValues.setDetectedObjects_Video_SceneDataID(strVideoSceneDataID);
        sceneMarkValues.setSceneDataList_SourceNodeID(strGlobalDeviceNodeID);
        sceneMarkValues.setSceneDataList_Duration(String.valueOf(iVideoDurationSec));
        sceneMarkValues.setTimeStamp(Utils.GetDateTime());
        sceneMarkValues.setSourceNodeID(strGlobalDeviceNodeID);
        sceneMarkValues.setSourceNodeDescription("Scenera Bridge");
        sceneMarkValues.setVideo_Duration(String.valueOf(iVideoDurationSec));
        sceneMarkValues.setThumbnail_DataType("Thumbnail");

        sceneMarkValues.setDetectedObjects_Image_DataType("RGBStill");
        sceneMarkValues.setDetectedObjects_Image_dictEncryption(EncryptionImage);
        sceneMarkValues.setImage_Resolution_Height(iImageHeight);
        sceneMarkValues.setImage_Resolution_Width(iImageWidth);

        sceneMarkValues.setDetectedObjects_Video_DataType("RGBVideo");
        sceneMarkValues.setDetectedObjects_Video_dictEncryption(EncryptionVideo);
        sceneMarkValues.setVideo_Resolution_Height(iVideoHeight);
        sceneMarkValues.setVideo_Resolution_Width(iVideoWidth);

        sceneMarkValues.setThumbnail_MediaFormat("JPEG");
        sceneMarkValues.setDetectedObjects_Image_MediaFormat("JPEG");
        sceneMarkValues.setDetectedObjects_Video_MediaFormat("H.264");
        sceneMarkValues.setThumbnail_SceneDataURI("");
        sceneMarkValues.setDetectedObjects_Image_SceneDataURI("");
        sceneMarkValues.setDetectedObjects_Video_SceneDataURI("");

        JSONObject dictionary = new JSONObject();
        dictionary = CreateSceneMark(sceneMarkValues);
        NICESendScenemarkThread(dictionary,
                strGlobalSceneMarkEndPoint,strGlobalSceneMarkToken, strGlobalSceneMarkAuthority);

    }

    private static JSONObject CreateSceneMark(SceneMarkValues sceneMarkValues){
        JSONObject dictSceneMark = new JSONObject();
        try {
            dictSceneMark.put("Version",sceneMarkValues.getVersion());
            dictSceneMark.put("TimeStamp",sceneMarkValues.getTimeStamp());
            dictSceneMark.put("SceneMarkID",sceneMarkValues.getSceneMarkID());
//            dictSceneMark.put("DestinationID",sceneMarkValues.getDestinationID());
            dictSceneMark.put("SceneMarkStatus",sceneMarkValues.getSceneMarkStatus());
            dictSceneMark.put("NotificationMessage","");
            dictSceneMark.put("NodeID",sceneMarkValues.getNodeID());
//            dictSceneMark.put("PortID",sceneMarkValues.getPortID());

            JSONObject jsonVersionList = new JSONObject();
            JSONArray jsonArrayVersionList = new JSONArray();
            JSONObject jsonVersionListData = new JSONObject();
            jsonVersionListData.put("VersionNumber",sceneMarkValues.getVersion());
            jsonVersionListData.put("DateTimeStamp",sceneMarkValues.getTimeStamp());
            jsonVersionListData.put("NodeID",sceneMarkValues.getNodeID());
            jsonArrayVersionList.put(jsonVersionListData);
            jsonVersionList.put("VersionList",jsonArrayVersionList);
            dictSceneMark.put("VersionControl",jsonVersionList);

            JSONArray jsonArrayThumbnailList = new JSONArray();
            JSONObject jsonThumbnailData = new JSONObject();
            jsonThumbnailData.put("VersionNumber",sceneMarkValues.getVersion());
            jsonThumbnailData.put("SceneDataID",sceneMarkValues.getThumbnail_SceneDataID());
            jsonArrayThumbnailList.put(jsonThumbnailData);
            dictSceneMark.put("ThumbnailList",jsonArrayThumbnailList);

            JSONArray jsonArrayAnalysisList = new JSONArray();
            JSONObject jsonAnalysisData = new JSONObject();
            jsonAnalysisData.put("VersionNumber",sceneMarkValues.getVersion());
            jsonAnalysisData.put("SceneMode",sceneMarkValues.getSceneMode());
            jsonAnalysisData.put("EventType",sceneMarkValues.getEventType());
            jsonAnalysisData.put("CustomEventType","");
//            jsonAnalysisData.put("CustomAnalysisID",sceneMarkValues.getCustomAnalysisID());
            jsonAnalysisData.put("AnalysisID","0001-0002-AI2");
            jsonAnalysisData.put("AnalysisDescription",sceneMarkValues.getAnalysisDescription());
            jsonAnalysisData.put("ProcessingStatus",sceneMarkValues.getProcessingStatus());
            jsonAnalysisData.put("ErrorMessage","");
            jsonAnalysisData.put("TotalItemCount",1);

            JSONArray jsonArrayDetectedObjects = new JSONArray();
            JSONObject jsonDetectedObjects = new JSONObject();
            jsonDetectedObjects.put("NICEItemType",sceneMarkValues.getNICEItemType());
            jsonDetectedObjects.put("AlgorithmID",sceneMarkValues.getAlgorithmID());
            jsonDetectedObjects.put("CustomItemType",sceneMarkValues.getCustomItemType());
            jsonDetectedObjects.put("ItemID","_123_");
            jsonDetectedObjects.put("ItemTypeCount",1);
            jsonDetectedObjects.put("Probability",sceneMarkValues.getProbability());

            JSONArray jsonArrayAttributes = new JSONArray();
            JSONObject jsonAttributes = new JSONObject();
            jsonArrayAttributes.put(jsonAttributes);
            jsonDetectedObjects.put("Attributes",jsonArrayAttributes);

            JSONObject jsonBoundingBox = new JSONObject();
            jsonBoundingBox.put("XCoordinate",sceneMarkValues.getXCoordinate());
            jsonBoundingBox.put("YCoordinate",sceneMarkValues.getYCoordinate());
            jsonBoundingBox.put("Height",sceneMarkValues.getResolution_Height());
            jsonBoundingBox.put("Width",sceneMarkValues.getResolution_Width());

            jsonDetectedObjects.put("BoundingBox",jsonBoundingBox);
            jsonDetectedObjects.put("RelatedSceneData",sceneMarkValues.getDetectedObjects_Image_SceneDataID());

            jsonArrayDetectedObjects.put(jsonDetectedObjects);
            jsonAnalysisData.put("DetectedObjects",jsonArrayDetectedObjects);

            jsonArrayAnalysisList.put(jsonAnalysisData);
            dictSceneMark.put("AnalysisList",jsonArrayAnalysisList);

            JSONArray jsonArraySceneDataList = new JSONArray();

            JSONObject jsonSceneDataThumbnail = new JSONObject();
            jsonSceneDataThumbnail.put("VersionNumber",1.0);
            jsonSceneDataThumbnail.put("SceneDataID",sceneMarkValues.getThumbnail_SceneDataID());
            jsonSceneDataThumbnail.put("TimeStamp",sceneMarkValues.getTimeStamp());
            jsonSceneDataThumbnail.put("SourceNodeID",sceneMarkValues.getSourceNodeID());
            jsonSceneDataThumbnail.put("SourceNodeDescription",sceneMarkValues.getSourceNodeDescription());
            jsonSceneDataThumbnail.put("DataType",sceneMarkValues.getThumbnail_DataType());
            jsonSceneDataThumbnail.put("Status",sceneMarkValues.getStatus());
            jsonSceneDataThumbnail.put("Encryption",sceneMarkValues.getDetectedObjects_Thumbnail_dictEncryption().getDictEncryption());
            jsonSceneDataThumbnail.put("MediaFormat",sceneMarkValues.getThumbnail_MediaFormat());
            JSONObject jsonResolutionImage = new JSONObject();
            jsonResolutionImage.put("Height",sceneMarkValues.getImage_Resolution_Height());
            jsonResolutionImage.put("Width",sceneMarkValues.getImage_Resolution_Width());
            jsonSceneDataThumbnail.put("Resolution",jsonResolutionImage);
            jsonSceneDataThumbnail.put("SceneDataURI",sceneMarkValues.getThumbnail_SceneDataURI());


            JSONObject jsonSceneDataImage = new JSONObject();
            jsonSceneDataImage.put("VersionNumber",1.0);
            jsonSceneDataImage.put("SceneDataID",sceneMarkValues.getDetectedObjects_Image_SceneDataID());
            jsonSceneDataImage.put("TimeStamp",sceneMarkValues.getTimeStamp());
            jsonSceneDataImage.put("SourceNodeID",sceneMarkValues.getSourceNodeID());
            jsonSceneDataImage.put("SourceNodeDescription",sceneMarkValues.getSourceNodeDescription());
            jsonSceneDataImage.put("DataType",sceneMarkValues.getDetectedObjects_Image_DataType());
            jsonSceneDataImage.put("Status",sceneMarkValues.getStatus());
            jsonSceneDataImage.put("Encryption",sceneMarkValues.getDetectedObjects_Image_dictEncryption().getDictEncryption());
            jsonSceneDataImage.put("MediaFormat",sceneMarkValues.getDetectedObjects_Image_MediaFormat());
            jsonSceneDataImage.put("Resolution",jsonResolutionImage);
            jsonSceneDataImage.put("SceneDataURI",sceneMarkValues.getDetectedObjects_Image_SceneDataURI());

            JSONObject jsonSceneDataVideo = new JSONObject();
            jsonSceneDataVideo.put("VersionNumber",1.0);
            jsonSceneDataVideo.put("SceneDataID",sceneMarkValues.getDetectedObjects_Video_SceneDataID());
            jsonSceneDataVideo.put("TimeStamp",sceneMarkValues.getTimeStamp());
            jsonSceneDataVideo.put("SourceNodeID",sceneMarkValues.getSourceNodeID());
            jsonSceneDataVideo.put("SourceNodeDescription",sceneMarkValues.getSourceNodeDescription());
            jsonSceneDataVideo.put("DataType",sceneMarkValues.getDetectedObjects_Video_DataType());
            jsonSceneDataVideo.put("Status",sceneMarkValues.getStatus());
            jsonSceneDataVideo.put("Encryption",sceneMarkValues.getDetectedObjects_Video_dictEncryption().getDictEncryption());
            jsonSceneDataVideo.put("MediaFormat",sceneMarkValues.getDetectedObjects_Video_MediaFormat());
            JSONObject jsonResolutionVideo = new JSONObject();
            jsonResolutionVideo.put("Height",sceneMarkValues.getVideo_Resolution_Height());
            jsonResolutionVideo.put("Width",sceneMarkValues.getVideo_Resolution_Width());
            jsonSceneDataVideo.put("Resolution",jsonResolutionVideo);
            jsonSceneDataVideo.put("SceneDataURI",sceneMarkValues.getDetectedObjects_Video_SceneDataURI());

            jsonArraySceneDataList.put(jsonSceneDataThumbnail);
            jsonArraySceneDataList.put(jsonSceneDataImage);
            jsonArraySceneDataList.put(jsonSceneDataVideo);

            dictSceneMark.put("SceneDataList",jsonArraySceneDataList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dictSceneMark;
    }

    private static void NICESendScenemarkThread(JSONObject dictNiceSceneMark,
                                                String strGlobalSceneMarkEndPoint,
                                                String strGlobalSceneMarkToken,
                                                String strGlobalSceneMarkAuthority){
        NICERestAPISendSceneMarkToCloud(dictNiceSceneMark,
                strGlobalSceneMarkEndPoint,strGlobalSceneMarkToken,
                strGlobalSceneMarkAuthority);

    }

    private static void NICERestAPISendSceneMarkToCloud(JSONObject dictNiceSceneMark,
                                                        String strGlobalSceneMarkEndPoint,
                                                        String strGlobalSceneMarkToken,
                                                        String strGlobalSceneMarkAuthority){
        SendSceneMarkToMSPipeLine(dictNiceSceneMark,
                strGlobalSceneMarkEndPoint,strGlobalSceneMarkToken,
                strGlobalSceneMarkAuthority);
    }

    private static void SendSceneMarkToMSPipeLine(JSONObject dictNiceSceneMark,
                                                  String strGlobalSceneMarkEndPoint,
                                                  String strSceneMarkToken,
                                                  String strSceneMarkAuthority) {
        String authority = "https://" + strSceneMarkAuthority;
        ServiceInterfaces.SetSceneMark api = ApiClient.getClient(context, authority).create(ServiceInterfaces.SetSceneMark.class);
        Utils.showCustomProgressDialog(context, "", false);
        Call<ResponseBody> call = api.setSceneMark("Bearer " + strSceneMarkToken,
                ApiClient.makeJSONRequestBody(dictNiceSceneMark));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("url", "SetSceneMarkURL------->>>> " + response.raw().request().url());
                Utils.removeCustomProgressDialog();
                if (!response.equals("{}") && response != null && response.body() != null) {
                    Log.i("SetSceneMark_RESPONSE", "Success");
                    SendDetectedObjectsSceneData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.removeCustomProgressDialog();
            }
        });
    }

    public static void SendDetectedObjectsSceneData(){
        SendObjectSceneData(strSceneMarkID, listDetectedObjects);
    }

    private static void SendObjectSceneData(String strSceneMarkID,
                                            List<DetectedObjectsClass> listDetectedObjects){
        objThumbnailSceneData = new SceneDataClass();
        for (DetectedObjectsClass detectedObjectsClass : listDetectedObjects){
            objThumbnailSceneData.setStrSceneDataID(detectedObjectsClass.getStrSceneDataID());
            objThumbnailSceneData.setStrSceneMarkID(strSceneMarkID);
            objThumbnailSceneData.setiChunkNumber(1);
            objThumbnailSceneData.setiNumberOfChunks(1);
            objThumbnailSceneData.setStrBase64OfSceneData(detectedObjectsClass.getStrBase64OfSceneData());
            NiceRestAPISendImagesToCloud(objThumbnailSceneData, strGlobalBrigdeUUID,
                    strGlobalSceneDataImageEndPoint,strGlobalSceneDataImageToken,
                    strGlobalSceneDataImageAuthority,true);
            break;
        }
    }

    private static void NiceRestAPISendImagesToCloud(SceneDataClass objDetectedObject
            ,String strGlobalBrigdeUUID, String strGlobalSceneDataImageEndPoint
            ,String strGlobalSceneDataImageToken,String strGlobalSceneDataImageAuthority, boolean isSendFullImage){
        Utils.showCustomProgressDialog(context, "", false);
        JSONObject jsonResponse = new JSONObject();
        String base64Image = objDetectedObject.getStrBase64OfSceneData();
        if(fIsImageEncrypted){
            boolean fVideo = false;
            byte[] binImage = android.util.Base64.decode(base64Image,android.util.Base64.DEFAULT);
            byte[] binEncryptedData = Utils.EncryptImageData(context, binImage);
            String binbase64Image = android.util.Base64.encodeToString(binEncryptedData,android.util.Base64.DEFAULT);
            base64Image = binbase64Image;
        }
        String strPathID = objDetectedObject.getStrSceneDataID();
        String fileName = objDetectedObject.getStrSceneDataID() + ".jpg";
        SceneDataValues objSD = new SceneDataValues();
        objSD.setVersion("1.0");
        objSD.setDataID(objDetectedObject.getStrSceneDataID());
        objSD.setFileType("Image");
        objSD.setFileName(fileName);
        objSD.setPathURI(strPathID);
        objSD.setSection(1);
        objSD.setLastSection(1);
        objSD.setHashMethod("SHA256");
        objSD.setOriginalFileHash("ABCDEFGHIJKLMNO");
        objSD.setSectionBase64(base64Image);
        List<String> relatedSceneMarks = new ArrayList<>();
        relatedSceneMarks.add(objDetectedObject.getStrSceneMarkID());
        objSD.setRelatedSceneMarks(relatedSceneMarks);

        JSONObject dictDataSectionObject = CreateSceneData(objSD);

        Utils.writeToFile(context,"SceneDataImageBase64_"+strFullImageSceneDataID ,
                dictDataSectionObject.toString());

        SendSceneDataImageToMSPipeLine(strGlobalSceneDataImageEndPoint,strGlobalSceneDataImageToken
                ,strGlobalSceneDataImageAuthority, dictDataSectionObject,base64Image, isSendFullImage);
    }

    private static void SendSceneDataImageToMSPipeLine(
            String strGlobalSceneDataImageEndPoint,
            String strGlobalSceneDataImageToken,
            String strGlobalSceneDataImageAuthority, JSONObject jsonCMP,String base64Image,
            boolean isSendFullImage){

        String authority = "https://" + strGlobalSceneDataImageAuthority;
        ServiceInterfaces.SetSceneData api = ApiClient.getClient(context, authority).create(ServiceInterfaces.SetSceneData.class);

        Call<ResponseBody> call = api.setSceneData("Bearer " + strGlobalSceneDataImageToken,
                ApiClient.makeJSONRequestBody(jsonCMP));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.equals("{}") && response != null && response.body() != null) {
                    Log.i("SetSceneDATA_RESPONSE", "Success");
                    Utils.removeCustomProgressDialog();
//                    if (isSendFullImage)
//                        SendFullImageSceneData(strBase64OfSceneDataImage);
                }else {
                    Log.i("SetSceneDATA_RESPONSE", "Fail");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AppLog.Log("SetSceneDATA_RESPONSE", "failed >>>>"+t.toString());
            }
        });

    }

    private static JSONObject CreateSceneData(SceneDataValues objSD){
        JSONObject dictSceneData = new JSONObject();
        try {
            dictSceneData.put("Version",objSD.getVersion());
            dictSceneData.put("DataID",objSD.getDataID());
            dictSceneData.put("FileType",objSD.getFileType());
            dictSceneData.put("FileName",objSD.getFileName());
            dictSceneData.put("PathURI",objSD.getPathURI());
            dictSceneData.put("Section",objSD.getSection());
            dictSceneData.put("LastSection",objSD.getLastSection());
            dictSceneData.put("HashMethod",objSD.getHashMethod());
            dictSceneData.put("OriginalFileHash",objSD.getOriginalFileHash());
            dictSceneData.put("SectionBase64",objSD.getSectionBase64());
            JSONArray jsonArrayRelatedSceneMarks = new JSONArray();
            for (String relatedSceneMarkId : objSD.getRelatedSceneMarks())
                jsonArrayRelatedSceneMarks.put(relatedSceneMarkId);
            dictSceneData.put("RelatedSceneMarks",jsonArrayRelatedSceneMarks);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dictSceneData;

    }

    public static void SendFullImageSceneData(Context context,String strFullImageBase64){
        Utils.showCustomProgressDialog(context, "", false);
        SceneDataClass objFullImageSceneData = new SceneDataClass();
        objFullImageSceneData.setStrSceneMarkID(strSceneMarkID);
        objFullImageSceneData.setStrSceneDataID(strFullImageSceneDataID);
        objFullImageSceneData.setStrBase64OfSceneData(strFullImageBase64);
        objFullImageSceneData.setiChunkNumber(1);
        objFullImageSceneData.setiNumberOfChunks(1);


        SendFullImageceneData(strSceneMarkID, objFullImageSceneData);
    }

    private static void SendFullImageceneData(String strSceneMarkID, SceneDataClass objFullImageSceneData){
        NiceRestAPISendImagesToCloud(objFullImageSceneData, strGlobalBrigdeUUID,
                strGlobalSceneDataImageEndPoint,strGlobalSceneDataImageToken,
                strGlobalSceneDataImageAuthority, false);
    }

    public static void SendVideoSection(Context context,String strBase64Video, int iChunkNumber, int iNumberOfChunksToUse){
        Utils.showCustomProgressDialog(context, "", false);
        SceneDataClass objVideoSceneData = new SceneDataClass();
        objVideoSceneData.setStrSceneMarkID(strSceneMarkID);
        objVideoSceneData.setStrSceneDataID(strGlobalVideoSceneDataID);
        objVideoSceneData.setStrBase64OfSceneData(strBase64Video);
        objVideoSceneData.setiChunkNumber(iChunkNumber);
        objVideoSceneData.setiNumberOfChunks(iNumberOfChunksToUse);

        NiceRestAPISendVideoToCloud(objVideoSceneData, listRelatedSceneMarksToVideo);
    }

    private static void NiceRestAPISendVideoToCloud(SceneDataClass objVideo
            ,List<String> listRelatedSceneMarksToVideo){

        String strCreateSceneDataID = objVideo.getStrSceneDataID();
        String base64MP4 = objVideo.getStrBase64OfSceneData();
        String strPathID = strCreateSceneDataID;
        String fileName = strCreateSceneDataID + ".jpg";

        SceneDataValues objSD = new SceneDataValues();
        objSD.setVersion("1.0");
        objSD.setDataID(strCreateSceneDataID);
        objSD.setFileType("Video");
        objSD.setFileName(fileName);
        objSD.setPathURI(strPathID);
        objSD.setSection(objVideo.getiChunkNumber());
        objSD.setLastSection(objVideo.getiNumberOfChunks());
        objSD.setHashMethod("SHA256");
        objSD.setOriginalFileHash("ABCDEFGHIJKLMNO");
        objSD.setSectionBase64(base64MP4);
        objSD.setRelatedSceneMarks(listRelatedSceneMarksToVideo);

        JSONObject dictDataSectionObject = CreateSceneData(objSD);

        Utils.writeToFile(context,"SceneDataVideo_"+strGlobalVideoSceneDataID ,
                dictDataSectionObject.toString());

        SendSceneDataVideoToMSPipeLine(strGlobalSceneDataVideoEndPoint,strGlobalSceneDataVideoToken
                ,strGlobalSceneDataVideoAuthority, dictDataSectionObject);
    }

    private static void SendSceneDataVideoToMSPipeLine(
            String strGlobalSceneDataVideoEndPoint,
            String strGlobalSceneDataVideoToken,
            String strGlobalSceneDataVideoAuthority, JSONObject dictDataSectionObject){

        String authority = "https://" + strGlobalSceneDataVideoAuthority;
        ServiceInterfaces.SetSceneData api = ApiClient.getClient(context, authority).create(ServiceInterfaces.SetSceneData.class);

        Call<ResponseBody> call = api.setSceneData("Bearer " + strGlobalSceneDataVideoToken,
                ApiClient.makeJSONRequestBody(dictDataSectionObject));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("url", "SetSceneMarkURL------->>>> " + response.raw().request().url());
                Utils.removeCustomProgressDialog();
                if (!response.equals("{}") && response != null && response.body() != null) {
                    Log.i("SetSceneMark_RESPONSE", "Success");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.removeCustomProgressDialog();
            }
        });

    }

}

