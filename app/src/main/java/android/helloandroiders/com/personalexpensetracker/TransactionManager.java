package android.helloandroiders.com.personalexpensetracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilotpal on 24/09/16.
 */
public class TransactionManager {
    private Map<String,IMessageParser> transactionParserList = new HashMap();
    private ArrayList<TransactionDetails> transactionDetailList = new ArrayList();

    private static TransactionManager ourInstance = new TransactionManager();

    private TransactionManager() {
    }

    public void RegisterParser(String transactionProviderType,IMessageParser tParser) {

        transactionParserList.put(transactionProviderType,tParser);
    }

    public ArrayList<TransactionDetails> getTransactionDetails() {

        Collections.sort(transactionDetailList, new Comparator<TransactionDetails>() {
            @Override
            public int compare(TransactionDetails details2, TransactionDetails details1) {

                return details1.transactionDate.compareTo(details2.transactionDate);
            }
        });

        return transactionDetailList;
    }

    public void extractTransactions(Map<String,ArrayList<SmsMessage>> messageList) {

        for(String address : messageList.keySet()) {

            if(address.contains(TransactionProviderType.ICICI)) {

                transactionParserList.get(TransactionProviderType.ICICI).parse(messageList.get(address),transactionDetailList);

            } else if(address.contains(TransactionProviderType.HDFC)) {

                transactionParserList.get(TransactionProviderType.HDFC).parse(messageList.get(address),transactionDetailList);

            } else {

                transactionParserList.get(TransactionProviderType.Default).parse(messageList.get(address),transactionDetailList);
            }
        }

    }

    public static TransactionManager getInstance() {
        return ourInstance;
    }
}
