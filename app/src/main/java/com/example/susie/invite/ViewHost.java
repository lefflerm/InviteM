package com.example.susie.invite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ViewHost extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    DBHandler dbHandler;
    long id;
    Meetings meetingsAdapter;
    ListView meetingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_host);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this, null);
        //toolbar.setSubtitle("Total Cost: " + dbHandler.getTotalMeetings((int) id));

        meetingsListView = (ListView) findViewById(R.id.meetingsListView);

        meetingsAdapter = new Meetings(this, dbHandler.getMeetings((int) id), 0);

        meetingsListView.setAdapter(meetingsAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_invite :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add_host :
                intent = new Intent(this, AddHost.class);
                startActivity(intent);
                return true;
            case R.id.action_add_meeting :
                intent = new Intent(this, AddMeeting.class);
                intent.putExtra("_id", id);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    public void openAddMeeting(View view){
        intent = new Intent(this, AddMeeting.class);
        intent.putExtra("_id", id);
        startActivity(intent);
    }

}
