package com.scenera.nicesecurityapplib;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.scenera.nicesecurityapplib.utilities.Constants;
import com.scenera.nicesecurityapplib.utilities.PreferenceHelper;
import com.scenera.nicesecurityapplib.utilities.Utils;

/**
 * Created by Ekta Bhatt on 21-11-2019.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "BaseActivity";


    public PreferenceHelper pHelper;
    public LinearLayout llBack;
    public boolean isNetDialogShowing, isReceiverMainRegistered;
    private AlertDialog internetDialog;
    public String fragmentTag = null;
  //  private PushBroadcastReceiver pushBroadcastReceiver;
   // private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pHelper = PreferenceHelper.getInstance(this);
        Constants.setPreference(this);

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(internetConnectionReceiver,
                    new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        else {
            registerReceiver(internetConnectionReceiver, new IntentFilter(
                    "android.net.conn.CONNECTIVITY_CHANGE"));
        }
        isReceiverMainRegistered = true;
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        getFirebaseToken();
        initReceiver();*/
    }
   /* private void initReceiver() {

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(Constants.PushAction.REGISTER_FCM);

        pushBroadcastReceiver = new PushBroadcastReceiver();
        localBroadcastManager.registerReceiver(pushBroadcastReceiver, intentFilter);
    }*/

   /* private void getFirebaseToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG + "Fcm Token: ", token);
                        //Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public class PushBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }

    }
*/
    /*public BroadcastReceiver internetConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("internet","onReceive called");
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetInfo != null) {
                Log.i("baseActi","internet receiver if ****** called");
                if (activeNetInfo.isConnected()) {
                    Log.i("baseActi","internet receiver if if ****** called");
//                    if(TextUtils.isEmpty(pHelper.getDeviceToken())) {
//                        isReceiverRegister = true;
//                        registerGcmReceiver(mHandleMessageReceiver);
//                    }
                    removeInternetDialog();
                }
                else {
                    Log.i("baseActi","internet receiver if else****** called");
                    if (isNetDialogShowing) {
                        return;
                    }
                    showInternetDialog();
                }
            }
            else {
                Log.i("baseActi","internet receiver else ****** called");
                if (isNetDialogShowing) {

                    return;
                }
                showInternetDialog();
            }
        }
    };*/

    public void removeInternetDialog() {
        if (internetDialog != null && internetDialog.isShowing()) {
            internetDialog.dismiss();
            isNetDialogShowing = false;
            internetDialog = null;
        }
    }

    public void showInternetDialog() {
        Utils.removeCustomProgressDialog();
        isNetDialogShowing = true;
        AlertDialog.Builder internetBuilder = new AlertDialog.Builder(this);
        internetBuilder.setCancelable(false);
        internetBuilder
                .setTitle(getString(R.string.text_dialog_no_internet))
                .setMessage(getString(R.string.text_dialog_no_inter_message))
                .setPositiveButton(getString(R.string.text_dialog_enable_3g),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent intent = new Intent(
                                        Settings.ACTION_SETTINGS);
                                startActivity(intent);
                                removeInternetDialog();
                            }
                        })
                .setNeutralButton(getString(R.string.text_dialog_enable_wifi),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                startActivity(new Intent(
                                        Settings.ACTION_WIFI_SETTINGS));
                                removeInternetDialog();
                            }
                        })
                .setNegativeButton(getString(R.string.text_dialog_exit),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                removeInternetDialog();
                                finish();
                            }
                        });
        internetDialog = internetBuilder.create();
        internetDialog.show();
    }

    //    @Override
//    protected void onStop() {
//        super.onStop();
//        if(isReceiverMainRegistered) {
//            unregisterReceiver(internetConnectionReceiver);
//        }
//    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
      /*  if(isReceiverMainRegistered) {
            unregisterReceiver(internetConnectionReceiver);
        }
        localBroadcastManager.unregisterReceiver(pushBroadcastReceiver);*/

    }

    @Override
    public void onClick(View view) {
       /* switch (view.getId()){
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.ivBackVid:
                onBackPressed();
                break;
            case R.id.llBack:
                AppLog.Log("BaseActivity => ","BAck Pressed...");
                onBackPressed();
                break;
        }*/
    }

}
