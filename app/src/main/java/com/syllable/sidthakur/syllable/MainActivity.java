package com.syllable.sidthakur.syllable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private final int CAMERA_PERMISSION_CODE = 1;
    private final int AUDIO_REC_PERMISSION_CODE = 2;
    private final int AUDIO_SET_PERMISSION_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","in OnCreate");
        checkForPermissions();

    }

    private void checkForPermissions() {

        if (ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this , Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("MainActivity","Permission not granted");
            //requestPermission(Manifest.permission.CAMERA ,CAMERA_PERMISSION_CODE );
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
            Log.d("Main activity ","after start intent");
        }
       else {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
        }
//        if(ContextCompat.checkSelfPermission(this , Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED)
//        {
//            requestPermission(Manifest.permission.RECORD_AUDIO , AUDIO_REC_PERMISSION_CODE);
//        }
//        if(ContextCompat.checkSelfPermission(this , Manifest.permission.MODIFY_AUDIO_SETTINGS)!=PackageManager.PERMISSION_GRANTED)
//        {
//            requestPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS , AUDIO_SET_PERMISSION_CODE);
//        }
    }

//    public void requestPermission(String permission,int permissionCode)
//    {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                permission)) {
//
//            // Show an explanation to the user *asynchronously* -- don't block
//            // this thread waiting for the user's response! After the user
//            // sees the explanation, try again to request the permission.
//
//        } else {
//
//            // No explanation needed; request the permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{permission},
//                   permissionCode);
//
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//       String needPermission="Granting these permissions are critical to app functions";
//        switch (requestCode)
//       {
//           case CAMERA_PERMISSION_CODE:
//           case AUDIO_REC_PERMISSION_CODE:
//           case AUDIO_SET_PERMISSION_CODE:
//                                        if(grantResults.length > 0
//                                                && grantResults[0] != PackageManager.PERMISSION_GRANTED)
//                                        {
//                                            Toast.makeText(this, needPermission ,Toast.LENGTH_LONG );
//                                        }
//                                        break;
//
//       }
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForPermissions();
    }
}
