package sachin.sdmp.com.app2;

/*
  Developed by Sachin Mathew
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RecieverNY extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Dynamic receiver from App2 in action!- NewYork ",
                Toast.LENGTH_LONG).show() ;
    }
}
