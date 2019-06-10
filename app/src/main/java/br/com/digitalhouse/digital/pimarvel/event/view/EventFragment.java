package br.com.digitalhouse.digital.pimarvel.event.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.base.view.BaseActivity;

public class EventFragment extends Fragment {


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*((BaseActivity)getActivity()).replaceFragment();*/
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

}
