package sachin.sdmp.com.application1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    protected Button buttonSF;
    protected Button buttonNY;
    protected static String sachinPermission = "edu.uic.cs478.f18.project3";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        buttonSF = (Button)findViewById(R.id.button_CA);
        buttonNY = (Button)findViewById(R.id.button_NY);
        buttonSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast("SF");
            }
        });
        buttonNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast("NY");
            }
        });
    }
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
}
