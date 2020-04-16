package com.example.yj.frag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yj.MainActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

public class one extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,null);
       // view.setBackgroundResource(R.drawable.backgroundfrag);
        if (MyUtility.shared==1)
        {
            Intent intent = new Intent(getActivity().getApplication(),MainActivity.class);
            startActivity(intent);
            finishFunction();
        }
        return view;
    }
    public void finishFunction() {
        Activity activity = (Activity)getContext();
        activity.finish();
    }
}
