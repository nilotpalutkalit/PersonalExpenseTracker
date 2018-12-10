package android.helloandroiders.com.personalexpensetracker;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nilotpal on 24/09/16.
 */
public class ICICITransactionParser implements IMessageParser{

    public void parse(ArrayList<SmsMessage> messageList,ArrayList<TransactionDetails> tDetails) {

        Collections.sort(messageList,new Comparator<SmsMessage>() {
            @Override
            public int compare(SmsMessage lhs, SmsMessage rhs) {
                return rhs.date.compareTo(lhs.date);
            }
        });

        for(SmsMessage each_sms : messageList) {

            TransactionDetails tDetail = parseSms(each_sms);
            if(tDetail.transactionValue>0.0)
                tDetails.add(tDetail);

        }

    }

    private TransactionDetails parseSms(SmsMessage smsMessage) {

//        TransactionDetails tDetail = new TransactionDetails();
//
//        tDetail.transactionDate = smsMessage.date;
//
//        int start_index = -1;
//        int end_index = -1;
//
//        if(smsMessage.message.contains("debited")) {
//
//            start_index = smsMessage.message.lastIndexOf("is debited with INR");
//            end_index = smsMessage.message.indexOf("on");
//            tDetail.transactionType = TransactionType.Debit;
//            tDetail.transactionName = "ICICI Debit";
//
//        } else if(smsMessage.message.contains("credited")) {
//
//            start_index = smsMessage.message.lastIndexOf("is credited with INR");
//            end_index = smsMessage.message.indexOf("on");
//            tDetail.transactionType = TransactionType.Credit;
//            tDetail.transactionName = "ICICI Credit";
//        }
//
//            if(start_index==-1 || end_index ==-1)
//                return tDetail;
//
//            start_index+=19;
//            end_index-=1;
//
//            String value = (smsMessage.message.substring(start_index, end_index)).trim();
//            value = value.replace(",","");
//
//            String regex = "^[^0-9]*([0-9[.]]*).*$";
//
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(value);
//            matcher.matches();
//            value = matcher.group(1);
//
//            try {
//
//                tDetail.transactionValue = Double.parseDouble(value);
//                tDetail.transactionProviderType = TransactionProviderType.ICICI;
//
//            } catch(Exception e) {
//
//                 return tDetail;
//            }
//
//        return  tDetail;

        DefaultTransactionParser transactionParser = new DefaultTransactionParser();
        TransactionDetails tDetail =  transactionParser.parseSms(smsMessage);
        if(tDetail.transactionValue>0.0) {
            tDetail.transactionProviderType = TransactionProviderType.ICICI;
            tDetail.transactionName = "ICICI";
        }

        if(tDetail.transactionType==TransactionType.Debit)
            tDetail.transactionName += " Debit";

        else if(tDetail.transactionType==TransactionType.Credit)
            tDetail.transactionName += " Credit";

        return tDetail;

    }
}
