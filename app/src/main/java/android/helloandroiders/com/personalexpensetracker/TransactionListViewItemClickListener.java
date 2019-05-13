package android.helloandroiders.com.personalexpensetracker;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nilotpal on 27/09/16.
 */
public class TransactionListViewItemClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.transactionName));
        String listItemText = textViewItem.getText().toString();
        String listItemId = textViewItem.getTag().toString();
        String message = TransactionManager.getInstance().getTransactionDetails().get(position).transactionMessage.message;
        Intent myIntent = new Intent(view.getContext(), MessageDetail.class);
        myIntent.putExtra("message", message); //Optional parameters
        view.getContext().startActivity(myIntent);

        //Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, message , Toast.LENGTH_SHORT).show();

    }
}
