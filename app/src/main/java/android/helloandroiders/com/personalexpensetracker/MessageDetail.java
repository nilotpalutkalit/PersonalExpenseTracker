package android.helloandroiders.com.personalexpensetracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MessageDetail extends AppCompatActivity {

    AppController appController = new AppController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().commit();
        }

        Toolbar titleToolbar = (Toolbar) findViewById(R.id.titleToolbar);
        titleToolbar.setTitle("Message Detail");
        setSupportActionBar(titleToolbar);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView messageView = (TextView) findViewById(R.id.messageView);
        messageView.setText(message);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
