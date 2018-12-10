package android.helloandroiders.com.personalexpensetracker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nilotpal on 24/09/16.
 */
public interface IMessageParser {

    public void parse(ArrayList<SmsMessage> messageList,ArrayList<TransactionDetails> tDetails);
}
