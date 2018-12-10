package android.helloandroiders.com.personalexpensetracker;

import java.util.Date;

/**
 * Created by nilotpal on 24/09/16.
 */
public class TransactionDetails {

    public String                   transactionProviderType;
    public TransactionType          transactionType;
    public String                   transactionName;
    public double                   transactionValue;
    public Date                     transactionDate;
    SmsMessage                      transactionMessage;

    public String getTransactionProviderName() {
        String transactionProviderName = "";

        switch(transactionProviderType) {

            case TransactionProviderType.ICICI: transactionProviderName = "ICICI";
                break;

            case TransactionProviderType.HDFC: transactionProviderName = "HDFC";
                break;

            case TransactionProviderType.Default: transactionProviderName = "Other";
                break;
        }

        return transactionProviderName;
    }
}
