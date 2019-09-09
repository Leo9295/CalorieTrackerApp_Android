package com.fit5046.calorietrackerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepRecordFragment extends Fragment implements View.OnClickListener {

    private View vStepRecord;

    List<HashMap<String, String>> listArray;
    SimpleAdapter stepNumAdapter;
    ListView listView;

    HashMap<String, String> map;
    String[] colHEAD = new String[]{"NUM OF STEPS", "UPDATE TIME"};
    int[] dataCell = new int[]{R.id.tv_step_num, R.id.tv_step_update_time};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vStepRecord = inflater.inflate(R.layout.fragment_step_record, container, false);

        listView = vStepRecord.findViewById(R.id.list_view);
        listArray = new ArrayList<HashMap<String, String>>();

        map = new HashMap<String, String>();
        map.put("NUM OF STEPS", "2221");
        map.put("UPDATE TIME", "13:08:00");
        listArray.add(map);

        stepNumAdapter = new SimpleAdapter(getActivity(), this.listArray, R.layout.list_view, colHEAD, dataCell);
        listView.setAdapter(stepNumAdapter);


        return vStepRecord;
    }

    @Override
    public void onClick(View v) {
    }
}
