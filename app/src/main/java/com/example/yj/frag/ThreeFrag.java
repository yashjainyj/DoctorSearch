package com.example.yj.frag;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yj.MainActivity;
import com.example.yj.doctorsearch.R;
import com.example.yj.myutility.MyUtility;

public class ThreeFrag extends android.support.v4.app.Fragment {

        private Button button;
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_three,null);
           // view.setBackgroundResource(R.drawable.backgroundfrag3);
            button=(Button)view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplication(),MainActivity.class);
                    startActivity(intent);
                    MyUtility.shared=1;
                    finishFunction();
                }
            });
            return view;
        }
   public void finishFunction() {
        Activity activity = (Activity)getContext();
        activity.finish();
    }

}
