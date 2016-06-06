package com.example.alice.tipscalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alice.tipscalc.R;
import com.example.alice.tipscalc.models.TipRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipDetailActivity extends AppCompatActivity {

    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    public final static String TIP_KEY = "tip" ;
    public final static String DATE_KEY = "timestamp" ;
    public final static String BILL_TOTAL_KEY = "total" ;
    public final static String RECORD_KEY = "record" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
//        TipRecord tipRecord = (TipRecord) intent.getSerializableExtra(RECORD_KEY);

        txtTimeStamp.setText(intent.getStringExtra(DATE_KEY));

        String formatedTotal =  String.format(getString(R.string.tipdetail_message_bill),
                                intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));

        txtBillTotal.setText(formatedTotal);

        String formatedTip =  String.format(getString(R.string.global_message_tip),
                intent.getDoubleExtra(TIP_KEY, 0d));

        txtTip.setText(formatedTip);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                 onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
