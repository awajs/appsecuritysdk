package com.scenera.nicesecurityapplib.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scenera.nicesecurityapplib.models.data.NodeList;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.AppSecurityObjectResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */
public class PreferenceHelper {

    private static final String PREF_NAME = "SceneraCloud";
    private static SharedPreferences app_preferences;
    private static PreferenceHelper preferenceHelper = new PreferenceHelper();

    private final String APP_SECURITY_OBJECT = "appSecurityObject";
    private final String APP_CONTROL_OBJECT = "appControlObject";

    private final String ACCOUNT_ID = "niceAccountId";
    private final String CAMERA_DETAILS = "cameraDetails";
    private final String CAMERA_PAGE_DETAILS= "cameraPageDetails";
    private final String CAMERA_TIMEZONE= "cameraTimeZone";

    private final String NICE_ITEM_TYPES = "niceItemTypes";
    private final String ALL_CAMERAS= "allCameras";
    private final String ALL_NICE_ITEMS= "allNiceItems";

    private final String SEEN_ALERTS_IDS = "seenAlertsIds";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String EXPIRY_DATE = "expiryDate";
    private final String NOT_BEFORE_DATE = "notBeforeDate";
    private final String ID = "_id";

    private final String PLAYBACK_SPPED = "playback_speed";

    private final String IS_DATE_SELECTED = "isDateSelected";
    private final String PREF_UNIQUE_ID = "uuid";
    private final String DEVICE_TOKEN = "device_token";

    private final String PRIVATE_KEY = "private_key";
    private final String PUBLIC_KEY = "public_key";
    private final String PRIVATE_KEY_RSA = "private_key_rsa";
    private final String PUBLIC_KEY_RSA = "public_key_rsa";

    private final String IS_VIDEO_LOADED = "isVideoLoaded";
    private final String IS_FILTER_SELECTED = "isFilterSelected";
    private final String IS_FILTER_ACTIVITY_SELECTED = "isFilterActivitySelected";

    private final String SERVICE_PROVIDER_NAME = "serviceProviderName";
    private final String END_POINT_ID = "endPointId";
    private final String DEVICE_TOKEN_PUSHY = "pushyDeviceToken";

    private final String SCENE_ENCRYPTION_ID = "SceneEncryptionKeyID";
    private final String IV_BYTES = "ivBytes";
    private final String KID = "kid";
    private final String APP_CONTROL_REQUEST_TIME = "appControlRequestTime";
    public PreferenceHelper() {
    }

    public PreferenceHelper(Context context) {
        app_preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance(Context context) {
        app_preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferenceHelper;
    }

    public void putCameraDetails(List<NodeList> cameraDetails) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(CAMERA_PAGE_DETAILS, new Gson().toJson(cameraDetails));
        edit.apply();
    }

    public ArrayList<NodeList> getCameraDetails() {
        return new Gson().fromJson(app_preferences.getString(CAMERA_PAGE_DETAILS, null), new TypeToken<ArrayList<NodeList>>() {
        }.getType());
    }
    public void putCameraTimezone(List<NodeList> cameraDetails) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(CAMERA_TIMEZONE, new Gson().toJson(cameraDetails));
        edit.apply();
    }

    public ArrayList<NodeList> getCameraTimezone() {
        return new Gson().fromJson(app_preferences.getString(CAMERA_TIMEZONE, null), new TypeToken<ArrayList<NodeList>>() {
        }.getType());
    }


    public void putSelectedCameraDetails(List<NodeList> cameraDetails) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(CAMERA_DETAILS, new Gson().toJson(cameraDetails));
        edit.apply();
    }

    public ArrayList<NodeList> getSelectedCameraDetails() {
        return new Gson().fromJson(app_preferences.getString(CAMERA_DETAILS, null), new TypeToken<ArrayList<NodeList>>() {
        }.getType());
    }

    public void putSeenAlertsIds(HashSet<String> alertIdSet){
        SharedPreferences.Editor edit = app_preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(alertIdSet);
        Log.i("alertIdSet","preference-->> " + json);
        edit.putString(SEEN_ALERTS_IDS, json);
        edit.apply();
    }

    public HashSet<String> getSeenAlertsIds(){
        Gson gson = new Gson();
        String json = app_preferences.getString(SEEN_ALERTS_IDS, null);
        if(json != null && json.length() >= 1){
            Type type = new TypeToken<HashSet<String>>() {}.getType();
            HashSet<String> alertIdSet = gson.fromJson(json, type);
            return alertIdSet;
        }
        else{
            return null;
        }
    }
    public void putAppSecurityObject(AppSecurityObjectResponse appSecurityObject) {
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString(APP_SECURITY_OBJECT, new Gson().toJson(appSecurityObject));
        editor.apply();
    }

    public AppSecurityObjectResponse getAppSecurityObject() {
        return new Gson().fromJson(app_preferences.getString(APP_SECURITY_OBJECT, null), AppSecurityObjectResponse.class);
    }

    public void putAppControlObject(AppConrolObjectResponse appConrolObjectResponse) {
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString(APP_CONTROL_OBJECT, new Gson().toJson(appConrolObjectResponse));
        editor.apply();
    }

    public AppConrolObjectResponse getAppControlObject() {
        return new Gson().fromJson(app_preferences.getString(APP_CONTROL_OBJECT, null), AppConrolObjectResponse.class);
    }

    public void putEmail(String email){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(EMAIL, email);
        edit.apply();
    }

    public String getEmail(){
        return app_preferences.getString(EMAIL, null);
    }

    public void putExpiryDate(long expiryDate){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putLong(EXPIRY_DATE, expiryDate);
        edit.apply();
    }

    public long getExpiryDate(){
        return app_preferences.getLong(EXPIRY_DATE, 0);
    }

    public void putNotBeforeDate(long notBeforeDate){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putLong(NOT_BEFORE_DATE, notBeforeDate);
        edit.apply();
    }

    public long getNotBeforeDate(){
        return app_preferences.getLong(NOT_BEFORE_DATE, 00000000);
    }

    public void putPassword(String password){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PASSWORD, password);
        edit.apply();
    }

    public void putPlaybackSpeed(int speed){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putInt(PLAYBACK_SPPED, speed);
        edit.apply();
    }

    public int getPlaybackSpeed(){
        return app_preferences.getInt(PLAYBACK_SPPED, 1);
    }

    public void putUUID(String id){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PREF_UNIQUE_ID, id);
        edit.apply();
    }

    public void putNiceItemTypes(List<String> eventDetails) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(NICE_ITEM_TYPES, new Gson().toJson(eventDetails));
        edit.apply();
    }

    public ArrayList<String> getNiceItemTypes() {
        return new Gson().fromJson(app_preferences.getString(NICE_ITEM_TYPES, null), new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    public void putIsDateSelected(Boolean isMobile) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putBoolean(IS_DATE_SELECTED, isMobile);
        edit.apply();
    }

    public void putDeviceToken(String deviceToken) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(DEVICE_TOKEN, deviceToken);
        edit.apply();
    }

    public String getDeviceToken() {
        return app_preferences.getString(DEVICE_TOKEN, "");

    }
    public void putPrivateKey(String privateKey){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PRIVATE_KEY, privateKey);
        edit.apply();
    }

    public String getPrivateKey(){
        return app_preferences.getString(PRIVATE_KEY, null);
    }

    public void putPublicKey(String publicKey){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PUBLIC_KEY, publicKey);
        edit.apply();
    }

    public String getPublicKey(){
        return app_preferences.getString(PUBLIC_KEY, null);
    }


    public void putPublicKeyRSA(String publicKeyRSA){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PUBLIC_KEY_RSA, publicKeyRSA);
        edit.apply();
    }

    public String getPublicKeyRSA(){
        return app_preferences.getString(PUBLIC_KEY_RSA, null);
    }


    public void putPrivateKeyRSA(String privateKeyRSA){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(PRIVATE_KEY_RSA, privateKeyRSA);
        edit.apply();
    }

    public String getPrivateKeyRSA(){
        return app_preferences.getString(PRIVATE_KEY_RSA, null);
    }

    public void putVideoLoaded(Boolean isVideoLoaded) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putBoolean(IS_VIDEO_LOADED, isVideoLoaded);
        edit.apply();
    }

    public Boolean getVideoLoaded() {
        return app_preferences.getBoolean(IS_VIDEO_LOADED, false);
    }



    public void putAllCamera(List<NodeList> cameraDetails) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(ALL_CAMERAS, new Gson().toJson(cameraDetails));
        edit.apply();
    }

    public ArrayList<NodeList> getAllCameras() {
        return new Gson().fromJson(app_preferences.getString(ALL_CAMERAS, null), new TypeToken<ArrayList<NodeList>>() {
        }.getType());
    }

    public void putAllNiceItems(List<String> niceItems) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(ALL_NICE_ITEMS, new Gson().toJson(niceItems));
        edit.apply();
    }

    public ArrayList<String> getAllNiceItems() {
        return new Gson().fromJson(app_preferences.getString(ALL_NICE_ITEMS, null), new TypeToken<ArrayList<String>>() {
        }.getType());
    }
    public void putIsFilterActivitySelected(Boolean isFilter) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putBoolean(IS_FILTER_ACTIVITY_SELECTED, isFilter);
        edit.apply();
    }

    public Boolean getIsFilterActivitySelected() {
        return app_preferences.getBoolean(IS_FILTER_ACTIVITY_SELECTED, false);
    }
    public void putIsFilterSelected(Boolean isFilter) {
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putBoolean(IS_FILTER_SELECTED, isFilter);
        edit.apply();
    }

    public Boolean getIsFilterSelected() {
        return app_preferences.getBoolean(IS_FILTER_SELECTED, false);
    }
    public void putServiceProviderName(String providerName){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(SERVICE_PROVIDER_NAME, providerName);
        edit.apply();
    }

    public String getServiceProviderName(){
        return app_preferences.getString(SERVICE_PROVIDER_NAME, null);
    }
    public void putAppInstanceId(String appInstanceId){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(END_POINT_ID, appInstanceId);
        edit.apply();
    }

    public String getAppInstanceId(){
        return app_preferences.getString(END_POINT_ID, null);
    }
    public void putDeviceTokenPushy(String deviceTokenPushy){
        Log.i("preference","------->>deviceTokenPushy called " + deviceTokenPushy);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString(DEVICE_TOKEN_PUSHY, deviceTokenPushy);
        editor.apply();
    }

    public String getDeviceTokenPushy(){
        return app_preferences.getString(DEVICE_TOKEN_PUSHY, null);
    }
    public void putSceneEncryptionID(String sceneEncryptionID){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(SCENE_ENCRYPTION_ID, sceneEncryptionID);
        edit.apply();
    }

    public String getSceneEncryptionID(){
        return app_preferences.getString(SCENE_ENCRYPTION_ID, null);
    }
    public void putIvBytes(String ivBytes){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(IV_BYTES, ivBytes);
        edit.apply();
    }

    public String getIvBytes(){
        return app_preferences.getString(IV_BYTES, null);
    }
    public void putKid(String kid){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(KID, kid);
        edit.apply();
    }
    public void putAppControlTime(String appControlReqTime){
        SharedPreferences.Editor edit = app_preferences.edit();
        edit.putString(APP_CONTROL_REQUEST_TIME, appControlReqTime);
        edit.apply();
    }

    public String getAppControlTime(){
        return app_preferences.getString(APP_CONTROL_REQUEST_TIME, null);
    }

    public String getKid(){
        return app_preferences.getString(KID, null);
    }
    public void clearPreference(){
        putIsFilterActivitySelected(false);

        putAppSecurityObject(null);
        putPassword(null);
        putUUID(null);
        putSelectedCameraDetails(null);
        putCameraTimezone(null);
        putCameraDetails(null);
        putNiceItemTypes(null);
        putIsDateSelected(false);
        putIsFilterSelected(false);
        putEmail(null);
        putAppInstanceId(null);
        putPrivateKey(null);
        putPublicKey(null);
        putAllCamera(null);
        putAllNiceItems(null);
        putServiceProviderName(null);
        putDeviceTokenPushy(null);

        putSceneEncryptionID(null);
        putIvBytes(null);
        putKid(null);
        putAppControlTime(null);
    }
}
