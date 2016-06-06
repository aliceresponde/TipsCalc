package com.example.alice.tipscalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alice.tipscalc.R;
import com.example.alice.tipscalc.activities.TipDetailActivity;
import com.example.alice.tipscalc.adapters.OnItemClickListener;
import com.example.alice.tipscalc.adapters.TipAdapter;
import com.example.alice.tipscalc.models.TipRecord;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener , OnItemClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);

        //inject UI dependences on  Fragment
        ButterKnife.bind(this, view);

        initAdapter();
        initRecyclerView();
        return view;
    }

    /*
       init RecyclerView  adapter, with an  empty list of <TipRecord>
     */
    private void initAdapter() {
        if (adapter == null) {
            adapter = new TipAdapter(getActivity(), this);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void action(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTipToList(TipRecord tip) {
        adapter.add(tip);
    }

    @Override
    public void clearTipList() {
        adapter.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClicked(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormat());
        startActivity(intent);

//        Toast.makeText(getActivity(), tipRecord.getDateFormat(), Toast.LENGTH_SHORT).show();
    }
}
