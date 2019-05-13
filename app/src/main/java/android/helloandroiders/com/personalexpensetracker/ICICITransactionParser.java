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
