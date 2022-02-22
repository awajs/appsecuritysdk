package com.scenera.nicesecurityapplib.utilities;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */
public class Constants {

    private static PreferenceHelper pHelper;

    public static void setPreference(Context context) {
        pHelper = PreferenceHelper.getInstance(context);
    }

    //public static final String HOST_URL = "http://egsa1.southafricanorth.cloudapp.azure.com";

    // public static final String HOST_URL = "http://scenerasec1.azurefd.net"; // The Microsoft Frondoor URL

    //public static final String HOST_URL = "https://nodesequencer.ds.scenera.live";
     public static final String HOST_URL = "http://scenerasec1.southafricanorth.cloudapp.azure.com"; //The Normal Azure Server URL
    public static String BSS_URL = "bssUrl";
    public static String BSS_APP_ID = "bssAppId";
    public static String SIGN_IN_MODE = "sign_in_mode";
    public static int DEMO_SIGN_IN = 0;
    public static int STAGING_SIGN_IN = 1;

    /* code when requesing for AppControl Object
    * https://create-account.azurewebsites.net/api/GetAppControlObject?code=c5aCL3d0I7Fk3wW0sXdhX9w6V9NauiyMGXzlzyyk0vt3p21n7UvQrQ==
    */
    public static final String CODE_GET_APP_CONTROL_REQUEST = "c5aCL3d0I7Fk3wW0sXdhX9w6V9NauiyMGXzlzyyk0vt3p21n7UvQrQ==";
    public static final String NICE_AS_X509_CERTIFICATE = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDvDCCAiSgAwIBAgIGAXKChXgAMA0GCSqGSIb3DQEBDQUAMCAxHjAcBgNVBAMMFU1hc3RlcktleS5zY2VuZXJhLmNvbTAgFw0yMDA2MDUwMzI4MDRaGA8yMTIwMTIzMTA4MDAwMFowHDEaMBgGA1UEAxMRQVNSb290LnNjZW5lcy5jb20wggGiMA0GCSqGSIb3DQEBAQUAA4IBjwAwggGKAoIBgQCi2dghP7y+bP6tvkJmmRYbqW50W0JNa9J5+D2ImfFK+7ipMz+G4431bylsPyftIwjhDvC1Mehl3nWwTmKIxBGOYnuIbEkuqV2/+JmjCymjd+zga6IQm+0He2t7NIr8pT3g2rwjf1dbRrYMCUtDCPOWVceBDEnCqi0u1RJBKJth6esOTGDUrAx33qV3amxT5X1ZZfJ6Jgp6+0lY9+W9umM+jJ9ka6lKQs2U/H9zr6fSfVvc+NQewpt3L5yQkunJyXel6jZVraUMBaFSwkhPuEBMSDkIR3PrziM8R03F3HHCvA/Kq5JX6HljoVOMa+3ggwiW4OcAmrl1qmtNd3tgBKdul7Q73IYb1oQ8w6dcmfQ5K/liQU6HREFGVtPtmxm5ecUfw1l/ZP4yGBq7VXiBg5IdgtdIbWk+nJ+In4/1klG9TgPjub/ppFOPB4v4ql6h+gfFZJ9ajZe8rnyYKCYzHXDpCkC4nBZkpWt+0kcz/t5JVnzT4Je0zoT9EXJYDzt8OC0CAwEAATANBgkqhkiG9w0BAQ0FAAOCAYEAm4/xl6y210c+FKRBCTLxCGDmjnG5H9+CT2Vt6+ouBJnbN6PtpgI/5fS1dUJcrRJFfJsZ3ZqyrTmQcm1INdudZ9y9FjDQRfNHc3jvzaFk6LFm+qJPbIoR4K/0twaIQQPKl48WbdLAh3Kxvz49vRMpl5RMVE0Z6JF3YhtyBber0Zvn1/JPN70h18r2JK5SHiMpJ0pDOQnEZmCFgV6dG5W6ErF5cWNlhydTD5u6RcX+baAt/II8aOEoAodhbFKT+i4QCFGnsnDif4UrQwNYuWuwa+pwA/dITRnzmp4TzSxhnii7AGn0XuuXRoLfm0uCP5Am4DmI3Ebef/MutqPDbtI6SNfFYdp0+MRRgo4GSmRUQts/i4gRtgeAaL1zlgBysapcIxtt4qHU/YjOyNhJCHH5s7HUAWF7dgmXSwjkhxmDSj7BI872u6bYwEwTv5OqyasxOrDR/kb7UJvOLUb1+u2xFkaeNsQJA5I2Kjv3JiD/AUcOByyAYd0cOpscRX/g3WMa" +
            "-----END CERTIFICATE-----\n";
    public static final String KID = "00000002-5cdd-280b-8003-000000000000";
    public static final String HOST_URL_BSS = "https://bss-webapp.azurewebsites.net";
    public static final String HOST_URL_NICE_ACCOUNT = "https://nice-api.azure-api.net";
  //  public static final String BASE_URL_API = HOST_URL + "/{version}" + "/12345678-1234-1234-1234-123456789abc/data/0002/0001/";

    public static final String CREATE_BSS_USER = "https://bss-webapp.azurewebsites.net/create_bss_user";

    public static final String NODE_ID = "0000";
    public static final String PORT_ID = "0000";
    public static final String API = "api";

    public static final int DELAY = 30000; // Refresh SceneMark List Delay
    public static final int TIME_SCHEDULE = 5 * 1000; // changed by Ekta: 18.10.2019 -> snapshot update time

    //GENERAL
    public static final String DEVICE_TYPE = "Android";
    public static final String DEVICE_TYPE_PUSHY = "AndroidPushy";
    public static final String TAG = "SCENERA";
    public static final String START_TIME = "T00:00:00.351Z";
    public static final String END_TIME = "T23:59:59.999Z";

    //public static final String END_POINT_ID = "100000-000-0000-0000000000";


    public static final String APP_ID = "00000001-5e84-224c-000000000065";



    // OLD
    /*public static final String BASE_URL_SCENEMARK  = HOST_URL + ":8082/";
    public static final String BASE_URL_IMAGE = HOST_URL + ":80/";
    public static final String BASE_URL_VIDEO = HOST_URL + ":8080/";
    public static final String HOST_URL_FAMILY_IMAGE = "http://210.122.7.91:4000";*/

    public class ServiceProvider {
        public static final int ABC_TELECOM = 1;
        public static final int PURPLE_TELECOM = 2;

    }


    public static class ServiceType {
        public static final String GET_ACCOUNT_NODE = "GetAccountNode";
        public static final String GET_SCENEMARK_MANIFEST = "GetSceneMarkManifest";



        public static final String LINK_APP_TO_BSS_ACCOUNT = HOST_URL_BSS;
        public static final String GET_APP_CONTROL = "GetAppControlObject";
        public static final String GET_SCENEMODE = "GetSceneMode";
        public static final String GET_DEVICE_MANAGEMENT_ENDPOINT = "GetManagementEndPoint";
        public static final String GET_DEVICE_MANAGEMENT_OBJECT = "GetManagementObject";
        public static final String GET_DEVICE_CONTROL_OBJECT = "GetControlObject";
        public static final String ADD_DEVICE_TOKEN = "adddevicetoken";
        public static final String DELETE_DEVICE_TOKEN = "deletedevicetoken";
        public static final String GET_PRIVACY_OBJECT = "GetPrivacyObject";
        public static final String GET_NICE_ITEM_TYPES_LIST = "GetNICEItemTypes";
        public static final String GET_ALL_TYPES_LIST = "getalltypes";

        /*********** SceneMarks CMF Version 1.02*********/
       // public static final String GET_SCENEMARK_MANIFEST_CMF = "GetSceneMarkManifestCMF";


      /*  public static final String DELETE_SCENE_MARK = BASE_URL_API + "scene_marks/";
        public static final String GET_ACCOUNTS = BASE_URL_API + "accounts";
        public static final String UPDATE_ACCOUNTS = BASE_URL_API + "accounts/{account_id}";
        public static final String CREATE_ACCOUNTS = BASE_URL_API + "accounts";
        public static final String GET_EVENT_DATES = BASE_URL_API + "scene_marks/date_api";
        public static final String SCENEMARK_KAFKA = BASE_URL_SCENEMARK + "topics/scene_mark_created/partitions/0/messages";
        public static final String UPLOAD_FAMILY_MEMBER_DETAIL = HOST_URL_FAMILY_IMAGE + "/image_upload";
        public static final String UPDATE_FAMILY_MEMBER_DETAIL = HOST_URL_FAMILY_IMAGE + "/image_upload";
        public static final String GET_FAMILY_MEMBERS = BASE_URL_API + "family_photos?";
        public static final String DELETE_FAMILY_MEMBER = BASE_URL_API + "family_photos/";
        public static final String GET_CURATION = BASE_URL_API + "scene_curation?";*/
    }

    public static class CMF {
        public static class Header {
            /*Request*/
            public static final String VERSION = "Version";
            public static final String MESSAGE_TYPE = "MessageType";
            public static final String SOURCE_END_POINT_ID = "SourceEndPointID";
            public static final String DESTINATION_END_POINT_ID = "DestinationEndPointID";
            public static final String COMMAND_ID = "CommandID";
            public static final String COMMAND_TYPE = "CommandType";
            public static final String ACCESS_TOKEN = "AccessToken";
            public static final String DATE_TIME_STAMP = "DateTimeStamp";
            public static final String ENCRYPTION_ON = "EncryptionOn";
            public static final String SCENE_ENCRYPTION_KEY_ID = "SceneEncryptionKeyID";


            /*Response*/
            public static final String REPLY_ID = "ReplyID";
            public static final String REPLY_STATUS_CODE = "ReplyStatusCode";
            public static final String REPLY_ERROR_CODE = "ReplyErrorCode";
            public static final String REPLY_STATUS_MESSAGE = "ReplyStatusMessage";
        }

        public static class Payload {
            public static final String EndPointX509Certificate = "EndPointX509Certificate";
            public static final String CMF_HEADER = "CMFHeader";
            public static final String ACCESSTOKEN_PAYLOAD = "AccessToken&Payload";
            public static final String PAYLOAD_OBJECT = "PayloadObject";
            public static final String DEVICE_ID = "DeviceID";
            public static final String PAYLOAD = "Payload";
            public static final String BODY = "Body";
            public static final String ENCRYPTED_KEY = "EncryptionKey";
            public static final String ENCRYPTED_PAYLOAD = "EncryptedPayload";
        }

        public static class HeaderValue {
            public static final String VERSION = "1.0";
            public static final String MESSAGE_TYPE = "request";
            public static final String SOURCE_END_POINT_ID = "00000001-5e84-224c-000000000065";
            public static final String DESTINATION_END_POINT_ID = "00000000-5eab-2e10-8003-000000000000";
            public static final String DATE_TIME_STAMP = "2019-12-24T15:59:01.938Z";
            public static final int COMMAND_ID = 0;
            public static final int COMMAND_ID_PRIVACY = 11;
            public static final String COMMAND_TYPE = "/1.0/00000000-5eab-2e10-8003-000000000000/management/GetAppControlObject";
            public static final String COMMAND_TYPE_PRIVACY = "/1.0/00000001-5cdd-280b-8003-000100000001/management/GetPrivacyObject";
            public static final String ACCESS_TOKEN = "dummy-access-token";

            public static final boolean ENCRYPTION_ON = false;
        }
    }

    public static class Params {

        public static final String DEVICE_TYPE = "Devicetype";
        public static final String DEVICE_TOKEN = "DeviceToken";
        public static final String APPLICATION_ID = "applicationId";


        public static final String EMAIL = "email";
        public static final String PASSWORD = "pass";
        public static final String ACCOUNT_ID = "AccountID";
        public static final String APP_ID = "AppID";
        public static final String APP_INSTANCE_ID = "AppInstanceID";
        public static final String PROPERTIES = "properties";


        public static final String NODE_IDS = "NodeIDs";
        public static final String MAX_NUMBER = "MaxNumber";
        public static final String PAGE_LENGTH = "PageLength";
        public static final String RESET_CACHE = "ResetCache";
        public static final String RETURN_SCENEMARK_LIST = "ReturnSceneMarkList";
        public static final String RETURN_PAGE = "ReturnPage";

        public static final String RETURN_NO_OF_SCENEMARKS = "ReturnNumberOfSceneMarks";
        public static final String RETURN_SCENEMARKS_DATES = "ReturnSceneMarkDates";
        public static final String RETURN_NICE_ITEM_TYPES = "ReturnNICEItemTypes";
        public static final String START_NUMBER = "StartNumber";
        public static final String END_NUMBER = "EndNumber";
        public static final String START_TIME = "StartTime";
        public static final String END_TIME = "EndTime";
        public static final String NICE_ITEM_TYPES = "ListNICEItemTypes";
        public static final String EVENT_TYPES = "ListEventTypes";
        public static final String CONTINUATION_TOKEN = "ContinuationToken";

        public static final String NICE_LINK_APP = "niceLinkApp";
        public static final String SERVICE_PROVIDER_NAME = "serviceProviderName";
        public static final String PERSON_NAME = "person_name";
        public static final String GROUP_NAME = "group_name";
        public static final String PERSON_GROUP_ID = "personGroupId";
        public static final String PERSON_ID = "personId";
        public static final String STATUS = "status";
        public static final String IMAGE_URL = "Image";
        public static final String NODE_ID = "NodeID";
        public static final String DEVICE_ID = "DeviceID";
        public static final String BODY = "Body";
    }

    public static class Crypto {

        public static final String PUBLIC_KEY_OBJECT = "PublicKeyObject";
        public static final String APP_ID = "AppID";
        public static final String PUBLIC_KEY = "PublicKey";
        public static final String VERSION = "Version";
        public static final String KTY = "kty";
        public static final String CRV = "crv";
        public static final String X = "x";
        public static final String Y = "y";
        public static final String EC = "EC";
        public static final String P_256 = "P-256";

        public static final String ALGORITHM = "Algorithm";
        public static final String OBJECT_CLEAR_TEXT = "ObjectInClearText";
        public static final String ENCRYPTION_KEY = "EncryptionKey";
        public static final String SIGNING_KEY_ID = "SigningKeyID";
        public static final String ECDH_ES = "ECDH-ES+A256KW";
        public static final String KID = "kid";
        public static final String ENCRYPTED_OBJECT = "EncryptedAndSignedObject";
    }

    //    DateFormats

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);


    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);

    public static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat curationTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
    public static final SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
    public static final SimpleDateFormat yearMonth = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
    public static final SimpleDateFormat imgTimeFormat = new SimpleDateFormat("HH_mm_ss");

    //    Fragment Tags
    public static final String TAG_FRAGMENT_ALERT = "fragment_alert";
    public static final String TAG_FRAGMENT_PROFILE = "fragment_profile";
    public static final String TAG_FRAGMENT_VIDEO_PLAYER = "fragment_video_Player";
    public static final String TAG_FRAGMENT_DEVICES = "fragment_devices";
    public static final String TAG_FRAGMENT_WEBVIEW = "fragment_webview";
    public static final String TAG_FRAGMENT_CURATION = "fragment_curation";
    public static final String TAG_FRAGMENT_ENC_DEC = "fragment_enc_dec";


    public static HashSet<String> seenAlertIdSet = new HashSet<>();
    public static boolean mFullScreen = false;

    //    Intent Extras

    public static final String TAG_EXTRA_SCENE_MARK_DATA = "sceneMarkData";
    public static final String TAG_EXTRA_SCENE_MARK_IMAGE = "sceneMarkImage";

    public static final String TAG_EXTRA_CAMERA = "camera";
    public static final String TAG_EXTRA_CAMERA_LIST = "camera_list";

    public static final String TAG_EXTRA_CURATION_VIDEO = "curation_video";
    public static final String TAG_EXTRA_IS_CURATION_VIDEO = "is_curation_video";
    public static final String TAG_EXTRA_CURATION_VIDEO_URIS = "curation_video_uris";

    //    Permission
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;


    public static class PushAction {

        public static final String REGISTER_FCM = "REGISTER_FCM";

    }

    public static class FCM {

        public static final String TAG = "FCM";
        public static final String FROM_NOTIFICATION = "fromNotification";
        public static final String MESSAGE = "message";
        public static final String UNIQUE_ID = "unique_id";

    }

    public static class Method {
        public static final int GET_SCENEMARKS_MANIFEST = 1;
        public static final int GET_EVENT_DATES = 2;
        public static final int GET_NICEITEMTYPES_LIST = 3;
        public static final int GET_ALLTYPES_LIST = 12;
        public static final int GET_LIVE_SCENEMARKS = 4;
        public static final int GET_CURATION_LIVE = 5;
        public static final int ADD_TOKEN_TO_SERVER = 6;
        public static final int REMOVE_TOKEN_FROM_SERVER = 7;
        public static final int GET_NODE_LIST = 8;
        public static final int GET_PRIVACY_OBJECT = 9;
        public static final int ADD_DEVICE_TOKEN = 10;
        public static final int GET_DEVICE_TOKEN = 11;
    }
}
