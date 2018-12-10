package android.helloandroiders.com.personalexpensetracker;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilotpal on 24/09/16.
 */
public class AppController {

    private Map<String,ArrayList<SmsMessage>>   smsList = new HashMap();
    private ArrayList<TransactionDetails>       transactionDetails;

    public void Initialize(Activity activity) {

        ReadAllMessage(activity);
        InitializeTransactionManager();

        TransactionManager.getInstance().extractTransactions(smsList);
        transactionDetails = TransactionManager.getInstance().getTransactionDetails();

    }


    private void InitializeTransactionManager() {

        TransactionManager transactionManager = TransactionManager.getInstance();
        transactionManager.RegisterParser(TransactionProviderType.ICICI,new ICICITransactionParser());
        transactionManager.RegisterParser(TransactionProviderType.HDFC,new HDFCTransactionParser());
        transactionManager.RegisterParser(TransactionProviderType.Default,new DefaultTransactionParser());

    }

    private void ReadAllMessage(Activity activity) {

        SmsReader sms_reader = new SmsReader();
        sms_reader.ReadInbox(activity,smsList);
    }
}
