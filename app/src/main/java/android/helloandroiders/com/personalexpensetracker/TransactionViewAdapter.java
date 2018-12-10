package android.helloandroiders.com.personalexpensetracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by nilotpal on 27/09/16.
 */
public class TransactionViewAdapter extends ArrayAdapter<TransactionDetails> {

    Context mContext;
    int layoutResourceId;
    TransactionDetails data[] = null;

    public TransactionViewAdapter(Context context, int layoutResourceId, TransactionDetails[] data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId   =   layoutResourceId;
        mContext                =   context;
        this.data               =   data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        TransactionDetails item = data[position];

        TextView transactionName = (TextView) convertView.findViewById(R.id.transactionName);
        //transactionName.setText(item.getTransactionProviderName() + "   " + item.transactionValue + "   " + item.transactionDate + "    " + item.transactionName);
        transactionName.setText(item.transactionName);
        transactionName.setTag(item.transactionProviderType);

        TextView transactionAmount = (TextView) convertView.findViewById(R.id.transactionAmount);
        transactionAmount.setText("" + item.transactionValue);
        transactionAmount.setTag(item.transactionProviderType);

        TextView transactionDate = (TextView) convertView.findViewById(R.id.transactionDate);
        String smsDate = new SimpleDateFormat("dd MMM yyyy").format(item.transactionDate);
        transactionDate.setText(smsDate);
        transactionDate.setTag(item.transactionProviderType);

        ImageView transactionIcon = (ImageView) convertView.findViewById(R.id.transactionIcon);
        SetImage(transactionIcon,item.transactionProviderType);

        return convertView;
    }

    private void SetImage(ImageView view, String transactionProviderType) {

        if(transactionProviderType.compareTo(TransactionProviderType.ICICI)==0)
            view.setImageResource(R.drawable.icici_icon);

        else if(transactionProviderType.compareTo(TransactionProviderType.HDFC)==0)
            view.setImageResource(R.drawable.hdfc_icon);

        else if(transactionProviderType.compareTo(TransactionProviderType.Default)==0)
            view.setImageResource(R.drawable.other_transaction_icon);


    }
}
