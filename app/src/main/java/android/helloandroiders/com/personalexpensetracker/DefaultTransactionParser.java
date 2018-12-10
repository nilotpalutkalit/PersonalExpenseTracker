package android.helloandroiders.com.personalexpensetracker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nilotpal on 25/09/16.
 */
public class DefaultTransactionParser implements IMessageParser {

    public void parse(ArrayList<SmsMessage> messageList,ArrayList<TransactionDetails> tDetails) {

        for(SmsMessage each_sms : messageList) {

            TransactionDetails tDetail = parseSms(each_sms);
            if(tDetail.transactionValue>0.0)
                tDetails.add(tDetail);

        }

    }

    public TransactionDetails parseSms(SmsMessage smsMessage) {

        TransactionDetails tDetail = new TransactionDetails();

        tDetail.transactionMessage = smsMessage;

        tDetail.transactionDate = smsMessage.date;

        int start_index = -1;
        int end_index = -1;

        if(
                (smsMessage.message.contains("withdrawn") ||
                smsMessage.message.contains("debited") ||
                smsMessage.message.contains("spent")
                ) && (
                        smsMessage.message.contains("will be") == false
                )
        ) {

            start_index = smsMessage.message.indexOf("Rs.");
            if(start_index==-1)
                start_index = smsMessage.message.indexOf("INR");

            tDetail.transactionType = TransactionType.Debit;
            tDetail.transactionName = "Other";

        } else if(smsMessage.message.contains("credited") && (smsMessage.message.contains("will be") == false)) {

            start_index = smsMessage.message.indexOf("Rs.");
            if(start_index==-1)
                start_index = smsMessage.message.indexOf("INR");

            tDetail.transactionType = TransactionType.Credit;
            tDetail.transactionName = "Other";
        }

        if(start_index==-1 /*|| end_index ==-1*/)
            return tDetail;

        //start_index+=19;
        //end_index-=1;

        String value = (smsMessage.message.substring(start_index)).trim();
        value = value.replace(",","");

        String regex = "^[^0-9]*([0-9[.]]*).*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        matcher.matches();
        value = matcher.group(1);

        try {

            tDetail.transactionValue = Double.parseDouble(value);
            tDetail.transactionProviderType = TransactionProviderType.Default;

        } catch(Exception e) {

            return tDetail;
        }

        return  tDetail;

    }
}
