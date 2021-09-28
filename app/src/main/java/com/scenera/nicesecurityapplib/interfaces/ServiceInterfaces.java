package com.scenera.nicesecurityapplib.interfaces;


import com.scenera.nicesecurityapplib.models.response.AddFaceResponse;
import com.scenera.nicesecurityapplib.models.response.AppConrolObjectResponse;
import com.scenera.nicesecurityapplib.models.response.EncryptedCMFResponse;
import com.scenera.nicesecurityapplib.models.response.GetDevicesResponse;
import com.scenera.nicesecurityapplib.models.response.GetFaceDataResponse;
import com.scenera.nicesecurityapplib.models.response.GetPrivaceObjectResponse;
import com.scenera.nicesecurityapplib.models.response.GetSceneMarkManifestResponse;
import com.scenera.nicesecurityapplib.models.response.SceneMarkResponseCMF;
import com.scenera.nicesecurityapplib.utilities.Constants;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */
public class ServiceInterfaces {

    public interface GetNiceItemTypeList {

        @Headers("Content-Type:application/json")
        @GET("/{Version}/"+ Constants.ServiceType.GET_NICE_ITEM_TYPES_LIST)
        Call<ArrayList<String>> getNiceItemTypes(@Header("Authorization") String auth,
                                                 @Path("Version") String version);

    }

    public interface GetAppControlObject {

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/management/"+ Constants.ServiceType.GET_APP_CONTROL)
        Call<AppConrolObjectResponse> getAppControlObject(@Header("Authorization") String auth,
                                                          @Path("Version") String version,
                                                          @Path("EndPointID") String EndPointID,
                                                          @Query("code") String code,
                                                          @Body RequestBody requestBody);

    }

    public interface GetAppControlObjectEncrypted {

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/management/"+ Constants.ServiceType.GET_APP_CONTROL)
        Call<EncryptedCMFResponse> getAppControlObject(@Header("Authorization") String auth,
                                                       @Path("Version") String version,
                                                       @Path("EndPointID") String EndPointID,
                                                       @Query("code") String code,
                                                       @Body RequestBody requestBody);

    }
    public interface GetPrivacyObject {

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/management/"+ Constants.ServiceType.GET_PRIVACY_OBJECT)
        Call<GetPrivaceObjectResponse> getPrivacyObject(@Header("Authorization") String auth,
                                                        @Path("Version") String version,
                                                        @Path("EndPointID") String EndPointID,
                                                        @Body RequestBody requestBody);

    }

    public interface GetPrivacyObjectEncrypted {

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/management/"+ Constants.ServiceType.GET_PRIVACY_OBJECT)
        Call<EncryptedCMFResponse> getPrivacyObject(@Header("Authorization") String auth,
                                                    @Path("Version") String version,
                                                        @Path("EndPointID") String EndPointID,
                                                        @Body RequestBody requestBody);

    }
    public interface GetAccountNode{

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/management/"+ Constants.ServiceType.GET_ACCOUNT_NODE)
        Call<GetDevicesResponse> getAccountNode(@Header("Authorization") String auth,
                                                @Path("Version") String version,
                                                @Path("EndPointID") String EndPointID,
                                                @Body RequestBody requestBody);
    }
    public interface GetSceneMarks {
        @Headers("Content-Type:application/json")
        @GET()
        Call<SceneMarkResponseCMF> getSceneMarks(@Header("Authorization") String auth, @Url String url);
    }


    public interface GetSceneMarkManifest {

        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/data/{NodeID}/{PortID}/"+ Constants.ServiceType.GET_SCENEMARK_MANIFEST)
        Call<GetSceneMarkManifestResponse> getSceneMarkManifest(@Header("Authorization") String auth,
                                                                @Path("Version") String version,
                                                                @Path("EndPointID") String EndPointID,
                                                                @Path("NodeID") String NodeID,
                                                                @Path("PortID") String PortID,
                                                                @Body RequestBody requestBody);


    }

    public interface GetEventDates {
        @Headers("Content-Type:application/json")
        @POST("/{Version}/{EndPointID}/data/{NodeID}/{PortID}/"+ Constants.ServiceType.GET_SCENEMARK_MANIFEST)
        Call<GetSceneMarkManifestResponse> getEventDates(@Header("Authorization") String auth,
                                                         @Path("Version") String version,
                                                         @Path("EndPointID") String EndPointID,
                                                         @Path("NodeID") String NodeID,
                                                         @Path("PortID") String PortID,
                                                         @Body RequestBody requestBody);
    }


    public interface AddDeviceToken {

        @Headers("Content-Type:application/json")
        @POST("/{Api}/{Version}/{EndPointID}/"+ Constants.ServiceType.ADD_DEVICE_TOKEN)
        Call<ResponseBody> addDeviceToken(@Path("Api") String api,
                                          @Path("Version") String version,
                                          @Path("EndPointID") String EndPointID,
                                          @Body RequestBody requestBody);
    }
    public interface DeleteDeviceToken {

        @Headers("Content-Type:application/json")
        @POST("/{Api}/{Version}/{EndPointID}/"+ Constants.ServiceType.DELETE_DEVICE_TOKEN)
        Call<ResponseBody> deleteDeviceToken(@Path("Api") String api,
                                             @Path("Version") String version,
                                             @Path("EndPointID") String EndPointID,
                                             @Body RequestBody requestBody);
    }

    public interface AddFaceDatabaseApi {

        @Multipart
        @POST("https://scenera-faceapp.azurewebsites.net/create_identity")
        Call<AddFaceResponse> addFaceDatabaseApi(@Part MultipartBody.Part file, @PartMap() Map<String,
                RequestBody> partMap);
    }

    public interface GetFaceDatabaseApi {

        @Headers("Content-Type:application/json")
        @POST("https://scenera-faceapp.azurewebsites.net/Show_person")
        Call<GetFaceDataResponse> getFaceDatabaseApi(@Body RequestBody requestBody);
    }

    public interface ChangeFaceDatabaseApi {

        @Headers("Content-Type:application/json")
        @POST("https://scenera-faceapp.azurewebsites.net/Change_status")
//        @POST("https://scenera-faceapp.azurewebsites.net/Change_status")
        Call<AddFaceResponse> changeFaceDatabaseApi(@Body RequestBody requestBody);
    }


}
