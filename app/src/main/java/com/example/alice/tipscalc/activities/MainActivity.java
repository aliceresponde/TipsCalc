package com.example.alice.tipscalc.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alice.tipscalc.R;
import com.example.alice.tipscalc.fragments.TipHistoryListFragment;
import com.example.alice.tipscalc.fragments.TipHistoryListFragmentListener;

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
    @Bind(R.id.guideline)
    Guideline guideline;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrese)
    Button btnDecrese;
    @Bind(R.id.separator)
    View separator;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.btnClear)
    Button btnClear;

    //instancia del listener
    private TipHistoryListFragmentListener fragmentListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Vincular el fragmento desde el support  fragment
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true); //conserve su estado
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }




    @OnClick({R.id.btnSubmit, R.id.btnIncrease, R.id.btnDecrese, R.id.btnClear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                break;
            case R.id.btnIncrease:
                break;
            case R.id.btnDecrese:
                break;
            case R.id.btnClear:
                break;
        }
    }
}
