import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ronaldo on 16/11/15.
 */

public class BroadcasterTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String incomingNumber = "";
        String incomingMessage = "";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                incomingNumber = msgs[i].getOriginatingAddress();
                incomingMessage = msgs[i].getMessageBody().toString();
            }
            //---display the new SMS message---
            Toast.makeText(context, "From: "+incomingNumber, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Message: "+incomingMessage, Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show();
    }
}
