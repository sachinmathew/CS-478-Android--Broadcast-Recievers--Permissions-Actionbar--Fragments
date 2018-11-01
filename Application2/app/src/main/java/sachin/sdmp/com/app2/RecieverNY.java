package sachin.sdmp.com.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RecieverNY extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Static receiver1 in action! ",
                Toast.LENGTH_LONG).show() ;
    }
}
