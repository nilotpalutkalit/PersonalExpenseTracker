package android.helloandroiders.com.personalexpensetracker;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilotpal on 24/09/16.
 */
public class SmsReader {

    public void ReadInbox(Activity activity, Map<String,ArrayList<SmsMessage>> sms) {
        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = activity.getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);
        //cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            String date = cursor.getString(2);

            Long timestamp = Long.parseLong(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date finalDate = calendar.getTime();
            //String smsDate = new SimpleDateFormat("MM/dd/yyyy").format(finalDate);

            SmsMessage smsMessage   =   new SmsMessage();
            smsMessage.address      =   cursor.getString(1);
            smsMessage.date         =   finalDate;
            smsMessage.message      =   cursor.getString(3);

            if(sms.containsKey(smsMessage.address )) {

                sms.get(smsMessage.address ).add(smsMessage);

            } else {

                ArrayList<SmsMessage> msgList = new ArrayList();
                msgList.add(smsMessage);

                sms.put(smsMessage.address,msgList);
            }
        }
    }
}
