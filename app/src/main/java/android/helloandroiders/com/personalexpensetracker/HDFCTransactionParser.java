package android.helloandroiders.com.personalexpensetracker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nilotpal on 25/09/16.
 */
public class HDFCTransactionParser implements IMessageParser {

    public void parse(ArrayList<SmsMessage> messageList,ArrayList<TransactionDetails> tDetails) {

        for(SmsMessage each_sms : messageList) {

            TransactionDetails tDetail = parseSms(each_sms);
            if(tDetail.transactionValue>0.0)
                tDetails.add(tDetail);

        }

    }

    private TransactionDetails parseSms(SmsMessage smsMessage) {

        TransactionDetails tDetail = new TransactionDetails();

        tDetail.transactionMessage = smsMessage;

        tDetail.transactionDate = smsMessage.date;

        int start_index = -1;
        int end_index = -1;

        if(smsMessage.message.contains("withdrawn") || smsMessage.message.contains("debited") || smsMessage.message.contains("spent")) {

            start_index = smsMessage.message.lastIndexOf("Rs.");
            if(start_index==-1)
                start_index = smsMessage.message.lastIndexOf("INR");

            //end_index = smsMessage.message.indexOf("was withdrawn using");
            tDetail.transactionType = TransactionType.Debit;
            tDetail.transactionName = "HDFC Debit";

        } else if(smsMessage.message.contains("credited")) {

            start_index = smsMessage.message.lastIndexOf("Rs.");
            if(start_index==-1)
                start_index = smsMessage.message.lastIndexOf("INR");

            //start_index = smsMessage.message.lastIndexOf("is credited with INR");
            //end_index = smsMessage.message.indexOf("on");
            tDetail.transactionType = TransactionType.Credit;
            tDetail.transactionName = "HDFC Credit";
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
            tDetail.transactionProviderType = TransactionProviderType.HDFC;

        } catch(Exception e) {

            return tDetail;
        }

        return  tDetail;

    }
}
