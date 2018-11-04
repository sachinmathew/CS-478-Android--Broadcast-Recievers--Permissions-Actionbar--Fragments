package sachin.sdmp.com.application1;

/*
  Developed by Sachin Mathew
  This the MainActivity class of Application1.
  This application sends broadcast and specifies the custom permission required for
  broadcast receivers.
*/
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected Button buttonSF;
    protected Button buttonNY;
    //Custom Permission name
    protected static String sachinPermission = "edu.uic.cs478.f18.project3";
    static Boolean  flag = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        buttonSF = (Button)findViewById(R.id.button_CA);
        buttonNY = (Button)findViewById(R.id.button_NY);
        checkingPermission();
        buttonSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true)
                    checkPermissionAndBroadcast("SF");
                else {
                    checkingPermission();
                }

            }
        });
        buttonNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true)
                    checkPermissionAndBroadcast("NY");
                else {
                    checkingPermission();
                }

            }
        });
    }
    // checks if permission is recieved from user
    public void checkingPermission() {
        if(ContextCompat.checkSelfPermission(this, sachinPermission)== PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{sachinPermission},0) ;
        else
            flag = true;
    }
    // Sends Broadcast
    private void checkPermissionAndBroadcast(String city) {
        Log.i("Application A1","Sending Broadcast for "+city);
        Intent aIntent;
        if(city.equals("SF"))
            aIntent = new Intent("sachin.sdmp.com.application1.SF.showToast") ;
        else
            aIntent = new Intent("sachin.sdmp.com.application1.NY.showToast") ;
        aIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendOrderedBroadcast(aIntent,sachinPermission) ;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            flag = true;
        }
        else {
            Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show() ;
        }
    }
}
