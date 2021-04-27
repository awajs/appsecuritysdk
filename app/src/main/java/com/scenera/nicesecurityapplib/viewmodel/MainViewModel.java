package com.scenera.nicesecurityapplib.viewmodel;

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
import com.scenera.nicesecurityapplib.models.data.NodeList;
import com.scenera.nicesecurityapplib.models.data.PersonFace;
import com.scenera.nicesecurityapplib.models.response.AddFaceResponse;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.AppSecurityObjectResponse;
import com.scenera.nicesecurityapplib.models.response.GetDevicesResponse;
import com.scenera.nicesecurityapplib.models.response.GetFaceDataResponse;
import com.scenera.nicesecurityapplib.models.response.GetPrivaceObjectResponse;
import com.scenera.nicesecurityapplib.models.response.GetSceneMarkManifestResponse;
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
import java.util.Date;
import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainViewModel extends ViewModel {

    private PreferenceHelper pHelper;
    private static final String TAG = "MainViewModel";
    private String keyEncrypted = "", ivEncrypted="", keyId="";

    private MutableLiveData<AppConrolObjectResponse> appConrolObjectLiveData;
    private MutableLiveData<GetPrivaceObjectResponse> privaceObjectLiveData;
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
    private ArrayList<String> niceItemTypes;

    private MutableLiveData<ArrayList<NodeList>> nodeListLiveData;
    private ArrayList<NodeList> nodeList;
    private String currentDate = "";
    private SimpleDateFormat format;

    public MainViewModel() {

        appConrolObjectLiveData = new MutableLiveData<>();
        privaceObjectLiveData = new MutableLiveData<>();
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

        niceItemTypesLiveData = new MutableLiveData<>();
        niceItemTypes = new ArrayList<String>();

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

    /** get SceneMarksManifest **/
    public MutableLiveData<GetSceneMarkManifestResponse> getSceneMarkManifestLiveData() {

        return sceneMarkManifestLiveData;
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

                String accessToken = pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                String authority = "https://" + pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAuthority();

                ServiceInterfaces.GetAccountNode api = ApiClient.getClientAccount(activity, authority).create(ServiceInterfaces.GetAccountNode.class);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Constants.Params.ACCOUNT_ID, pHelper.getAppSecurityObject().getAccountID());

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
                false, new ArrayList<String>(), "", "","", "","", "");
            }
        }catch (Exception et){
            et.printStackTrace();
        }
        return nodeListLiveData;
    }

    /** get SceneMarksManifest **/
    public MutableLiveData<GetSceneMarkManifestResponse> getSceneMarksManifest(AppCompatActivity activity, ArrayList<String> nodeIds,String startTime, String endTime,
                                                                               int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                                                               boolean returnPage, ArrayList<String> niceItemList, String continuationToken) {
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
                    pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getAPIVersion(),
                    pHelper.getAppInstanceId(), Constants.NODE_ID, Constants.PORT_ID,
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
                    returnPage, niceItemList, continuationToken, "","", "", "", "");
        }
        return sceneMarkManifestLiveData;
    }

    /** Get Event Dates For SceneMarks for selected month..**/
    public MutableLiveData<ArrayList<String>> getEventDates(AppCompatActivity activity, ArrayList<String> nodeIds,String startTime, String endTime,
                                                            int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                                            boolean returnPage, ArrayList<String> niceItemList, String continuationToken) {
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
                    pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getAPIVersion(),
                    pHelper.getAppInstanceId(), Constants.NODE_ID, Constants.PORT_ID, ApiClient.makeJSONRequestBody(jsonObject));
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
                    returnPage, niceItemList, continuationToken, "","", "", "", "");
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

                    if (response.body() != null && response.body().getDetectedObjects().size() > 0) {

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
                    false, new ArrayList<String>(), "",
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
                    pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getAPIVersion());

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
                    false, new ArrayList<String>(), "", "","", "","", "");
        }
        return niceItemTypesLiveData;
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

                    if (response.body() != null && response.body().getDetectedObjects().size() > 0) {

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
                    false, new ArrayList<String>(), "",
                    sceneMarkURI, "", "","", "");
        }
        return sceneMarkResponseLive;
    }
    /** get Privacy Object to decrypt video,audio **/
    public void getPrivacyObject(AppCompatActivity activity, String currentDate, String sceneEncryptionKeyID) {
        Utils.showCustomProgressDialog(activity, "", false);
        pHelper = PreferenceHelper.getInstance(activity);
        if(isTokenNotExpired()) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(Constants.CMF.Header.VERSION, pHelper.getAppSecurityObject().getVersion());
                jsonObject.put(Constants.CMF.Header.MESSAGE_TYPE, Constants.CMF.HeaderValue.MESSAGE_TYPE);
                //00000001-5e84-224c-8003-000000000065
                jsonObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, pHelper.getAppInstanceId());
                //jsonObject.put(Constants.CMF.Header.SOURCE_END_POINT_ID, pHelper.getAppSecurityObject().getAppInstanceID());
                jsonObject.put(Constants.CMF.Header.DESTINATION_END_POINT_ID, pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID());
                jsonObject.put(Constants.CMF.Header.COMMAND_ID, Constants.CMF.HeaderValue.COMMAND_ID);
                jsonObject.put(Constants.CMF.Header.COMMAND_TYPE, Constants.CMF.HeaderValue.COMMAND_TYPE_PRIVACY);
                jsonObject.put(Constants.CMF.Header.DATE_TIME_STAMP, currentDate);
                jsonObject.put(Constants.CMF.Header.ENCRYPTION_ON, true);

                JSONObject jsonBody = new JSONObject();
                JSONObject jsonCreateBody = new JSONObject();

                jsonCreateBody.put(Constants.CMF.Header.VERSION, pHelper.getAppSecurityObject().getVersion());
                jsonCreateBody.put(Constants.CMF.Header.SCENE_ENCRYPTION_KEY_ID, sceneEncryptionKeyID);
                AppLog.Log("jsonCreateBody => ", jsonCreateBody.toString());

                jsonBody.put(Constants.CMF.Payload.BODY, jsonCreateBody);

                jsonObject.put(Constants.CMF.Payload.PAYLOAD, jsonBody);
                AppLog.Log("jsonObjectMain => ", jsonObject.toString());

                String accessToken = pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAccessToken();

                ServiceInterfaces.GetPrivacyObject api = ApiClient.getClientAccount(activity, "https://" +
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetPrivacyObject.class);

                Call<GetPrivaceObjectResponse> call = api.getPrivacyObject("Bearer " + accessToken,
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                        pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                        ApiClient.makeJSONRequestBody(jsonObject));


                call.enqueue(new Callback<GetPrivaceObjectResponse>() {
                    @Override
                    public void onResponse(Call<GetPrivaceObjectResponse> call, retrofit2.Response<GetPrivaceObjectResponse> response) {
                        Log.i(TAG, "---->>> GetPrivacy " + response.raw().request().url());

                        //  Utils.removeCustomProgressDialog();

                        if (!response.equals("{}") && response != null && response.body() != null) {
                            try {

                                keyEncrypted = response.body().getPayload().getSceneEncryptionKey().getK();
                                pHelper.putSceneEncryptionID(keyEncrypted); // SceneEncryptionID
                                ivEncrypted = response.body().getPayload().getSceneEncryptionKey().getIv();
                                keyId = response.body().getPayload().getSceneEncryptionKey().getKid();
                                pHelper.putIvBytes(ivEncrypted);  // IvBytes
                                pHelper.putKid(keyId); // KeyID
                                privaceObjectLiveData.setValue(response.body());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetPrivaceObjectResponse> call, Throwable t) {
                        Utils.removeCustomProgressDialog();
                        Log.i("onFailure", "---->>>> " + t.toString());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            getAppControlObject(pHelper.getAppSecurityObject(),activity,
                    Constants.Method.GET_PRIVACY_OBJECT, new ArrayList<String>(),"","",
                    0, false, false,
                    false, new ArrayList<String>(), "",
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

            ServiceInterfaces.GetAppControlObject api = ApiClient.getClientAccount(activity,"https://" +
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObject.class);

            Call<AppConrolObjectResponse> call = api.getAppControlObject("Bearer "+ accessToken,
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                    Constants.CODE_GET_APP_CONTROL_REQUEST,
                    ApiClient.makeJSONRequestBody(jsonObject));


            call.enqueue(new Callback<AppConrolObjectResponse>() {
                @Override
                public void onResponse(Call<AppConrolObjectResponse> call, retrofit2.Response<AppConrolObjectResponse> response) {
                    Log.i(TAG, "---->>> AppControl " + response.raw().request().url());

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {

                        pHelper.putAppControlObject(response.body());
                        String token = response.body().getPayload().getDataEndPoints().get(0).
                                getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                        JWT jwt = new JWT(token);
                        pHelper.putExpiryDate(jwt.getExpiresAt().getTime());
                        pHelper.putNotBeforeDate(jwt.getNotBefore().getTime());
                        appConrolObjectLiveData.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<AppConrolObjectResponse> call, Throwable t) {
                    Utils.removeCustomProgressDialog();
                    Log.i("onFailure", "---->>>> " + t.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private boolean isTokenNotExpired(){
        Date currentDate = new Date();
        return (currentDate.getTime() > pHelper.getNotBeforeDate() && currentDate.getTime() < pHelper.getExpiryDate());
    }

    private void getAppControlObject(AppSecurityObjectResponse appSecurityObject,
                                     AppCompatActivity activity, int method, ArrayList<String> nodeIds,String startTime, String endTime,
                                     int pageLength, boolean returnNiceItemTypes, boolean returnSceneMarksDates,
                                     boolean returnPage, ArrayList<String> niceItemList, String continuationToken,
                                     String sceneMarkURI, String currentDateString, String sceneEncryptionKeyID,
                                     String deviceName, String deviceTimeZone) {
        Utils.showCustomProgressDialog(activity, "", false);

        Date today = new Date();
        currentDate =  format.format(today);
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
            String appInstanceID = appSecurityObject.getAppInstanceID();

            jsonPayLoad.put(Constants.Params.APP_ID, appInstanceID);

            jsonObject.put(Constants.CMF.Payload.PAYLOAD, jsonPayLoad);
            AppLog.Log("jsonObjectMain => ", jsonObject.toString());


            String accessToken = pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAccessToken();

            ServiceInterfaces.GetAppControlObject api = ApiClient.getClientAccount(activity,"https://" +
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getScheme().get(0).getAuthority()).create(ServiceInterfaces.GetAppControlObject.class);

            Call<AppConrolObjectResponse> call = api.getAppControlObject("Bearer "+ accessToken,
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getAPIVersion(),
                    pHelper.getAppSecurityObject().getNICEASEndPoint().getNetEndPoint().getEndPointID(),
                    Constants.CODE_GET_APP_CONTROL_REQUEST,
                    ApiClient.makeJSONRequestBody(jsonObject));



            call.enqueue(new Callback<AppConrolObjectResponse>() {
                @Override
                public void onResponse(Call<AppConrolObjectResponse> call, retrofit2.Response<AppConrolObjectResponse> response) {
                    Log.i(TAG, "---->>> AppControl " + response.raw().request().url());
                    Gson gson = new Gson();
                    Log.i(TAG, "---->>> AppControl-RESPONSE " + gson.toJson(response.body()));

                    Utils.removeCustomProgressDialog();

                    if (!response.equals("{}") && response != null && response.body() != null) {

                        pHelper.putAppControlObject(response.body());
                        String token = response.body().getPayload().getDataEndPoints().get(0).
                                getNetEndPointAppControl().getSchemeAppControlObject().get(0).getAccessToken();
                        JWT jwt = new JWT(token);
                        pHelper.putExpiryDate(jwt.getExpiresAt().getTime());
                        pHelper.putNotBeforeDate(jwt.getNotBefore().getTime());
                        pHelper.putEmail("test");
                        switch (method){
                            case Constants.Method.GET_SCENEMARKS_MANIFEST:
                                getSceneMarksManifest(activity, nodeIds, startTime, endTime,
                                pageLength, returnNiceItemTypes, returnSceneMarksDates,
                                returnPage, niceItemList, continuationToken);
                                break;
                            case Constants.Method.GET_EVENT_DATES:
                                getEventDates(activity, nodeIds, startTime, endTime,
                                        pageLength, returnNiceItemTypes, returnSceneMarksDates,
                                        returnPage, niceItemList, continuationToken);
                                break;
                            case Constants.Method.GET_NICEITEMTYPES_LIST:
                                getNiceItemTypesList(activity);
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
                public void onFailure(Call<AppConrolObjectResponse> call, Throwable t) {
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
                pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getAPIVersion(),
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
                pHelper.getAppControlObject().getPayload().getControlEndPoints().get(0).getNetEndPointAppControl().getAPIVersion(),
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



}
