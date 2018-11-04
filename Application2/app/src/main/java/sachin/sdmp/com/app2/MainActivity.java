package sachin.sdmp.com.app2;

/*
  Developed by Sachin Mathew
  This the MainActivity class of Application2.
*/

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected Button button;
    protected static String sachPermission ="edu.uic.cs478.f18.project3";
    BroadcastReceiver sf;
    BroadcastReceiver ny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        button =(Button)findViewById(R.id.buttonPermission);

        // checks if permission is recieved from user on click of button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkingPermission();
            }
        });
        IntentFilter intentFilterSF = new IntentFilter();
        IntentFilter intentFilterNY = new IntentFilter();

        //Setting up dynamic receivers
        intentFilterSF.addAction("sachin.sdmp.com.application1.SF.showToast");
        intentFilterNY.addAction("sachin.sdmp.com.application1.NY.showToast");
        intentFilterSF.setPriority(10);
        intentFilterNY.setPriority(15);
        sf = new RecieverSF();
        ny = new RecieverNY();

        //registers receivers
        registerReceiver(sf,intentFilterSF);
        registerReceiver(ny,intentFilterNY);

    }
    // checks if permission is recieved from user
    public void checkingPermission() {
        if(ContextCompat.checkSelfPermission(this, sachPermission)==PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{sachPermission},0) ;
        else
            Toast.makeText(this, "Permission already present", Toast.LENGTH_LONG).show() ;
    }
    //Unregisters receivers to prevent memory leakage
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sf);
        unregisterReceiver(ny);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // Permission granted
            Log.i("BroadcastReceiver", "The user granted permission!!!") ;
        }
        else {
            Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show() ;
        }
    }
}
