package sachin.sdmp.com.app3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// Brodcast Receiver for App3

public class App3Reciever extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String activityNum = intent.getAction();
        if(activityNum.equals("sachin.sdmp.com.application1.NY.showToast"))
            Toast.makeText(context, "App3 : Under Construction", Toast.LENGTH_SHORT).show();
        else{
            Activity1.get().callBack();
        }
    }
}

