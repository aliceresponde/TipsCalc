package com.example.alice.tipscalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alice.tipscalc.App;
import com.example.alice.tipscalc.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link  UI and  views
        ButterKnife.bind(this);

        //Vincular el fragmento desde el support  fragment




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
