package com.example.alice.tipscalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alice.tipscalc.App;
import com.example.alice.tipscalc.R;
import com.example.alice.tipscalc.fragments.TipHistoryListFragment;
import com.example.alice.tipscalc.fragments.TipHistoryListFragmentListener;
import com.example.alice.tipscalc.models.TipRecord;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPrecentage)
    EditText inputPrecentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrese)
    Button btnDecrese;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;

    //constants
    private final static int TIP_STEP_CHANGE=1;
    private final static int DEFAULT_TIP_STEP_PERCENTAGE=10;
    private final String TAG = MainActivity.class.getSimpleName();


    //instancia del listener
    private TipHistoryListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link  UI and  views
        ButterKnife.bind(this);

        //Vincular el fragmento desde el support  fragment
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        //fragmet guarde su instancia
        fragment.setRetainInstance(true);

        // casteo el  fragment al listener  de la interface implementada por el fragmento
        fragmentListener = (TipHistoryListFragmentListener) fragment;




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        App app = (App) getApplication();
        String url = app.getAboutUrl();
        lunchBrowserIn(url);
    }

    /**
     * Lunch url in browser
     *
     * @param url
     */
    private void lunchBrowserIn(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.btnSubmit)
    public void handleSubmitClick() {
        Log.i(TAG, "click");
        hideKeyBoard();

        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total*(tipPercentage/100d);


            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strFormatedTip = String.format(getString(R.string.global_message_tip),
                                                    tipRecord.getTip());
            fragmentListener.addTipToList(tipRecord);

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strFormatedTip);

        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleIncreaseClick(){
        Log.i(TAG, "click inclease");
        hideKeyBoard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrese)
    public void handleDecreaseClick(){
        Log.i(TAG, "click Decrease");
        hideKeyBoard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public  void handleClearClick(){
        fragmentListener.clearTipList();
    }

    /**
     * Define is the percentage  is  > 0 for set a positive value
     * @param change
     */
    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage > 0){
            inputPrecentage.setText(String.valueOf(tipPercentage));
        }
    }

    /**
     * Verify inpitTip to get this percentage, or deftaul tip_PERCENTAGE (10)
     * @return
     */
    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_STEP_PERCENTAGE;
        String strInputTipPercentage = inputPrecentage.getText().toString().trim();

        if (!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            inputPrecentage.setText(String.valueOf(tipPercentage));
        }

        return tipPercentage;
    }

    /**
     * Dismiss visible keyBoard
     */
    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try{
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (NullPointerException npe){
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }
}
