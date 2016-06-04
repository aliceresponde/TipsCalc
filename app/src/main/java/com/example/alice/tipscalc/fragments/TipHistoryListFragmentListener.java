package com.example.alice.tipscalc.fragments;

/**
 * Created by alice on 6/3/16.
 * Forma de comunicar actividades con fragmentos
 * Estos metodos deben ser implentados en el fragmento TipHistoryListFragment
 *  -add
 *  -Clean
 *
 */

public interface TipHistoryListFragmentListener {

    void  action(String str);
}
