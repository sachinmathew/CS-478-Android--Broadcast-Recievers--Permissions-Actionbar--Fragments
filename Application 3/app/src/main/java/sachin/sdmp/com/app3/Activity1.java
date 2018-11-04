package sachin.sdmp.com.app3;

/*
  Developed by Sachin Mathew
  This the MainActivity class of Application3.
*/
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class Activity1 extends AppCompatActivity {

   // Static block to get current object of Activity1 so that callback function can be called
    private static Activity1 activity1;
    {
        activity1 = this;
    }

    public static Activity1 get() {
        return activity1;
    }

    //custom permission
    private String sachPermission="edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //myToolbar.setLogo(R.drawable.icon);
        myToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(myToolbar);
        checkPermission();
    }

    // checks if permission is there

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, sachPermission)== PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{sachPermission},0) ;
        else
            Toast.makeText(this, "Permission already present", Toast.LENGTH_LONG).show() ;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{sachPermission},0) ;

    }
    // calls the second Activity this is used by broadcast reciever class
    public void callBack(){
        Intent nyIntent = new Intent(this,Activity2.class);
        startActivity(nyIntent);
    }
}
