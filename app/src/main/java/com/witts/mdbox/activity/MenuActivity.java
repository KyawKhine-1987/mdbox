package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import com.witts.mdbox.R;
import com.witts.mdbox.fragments.MenuFragment;

import java.util.ArrayList;


public class MenuActivity extends BasedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, MenuFragment.newInstance())
                .commit();


        List<Hotel> lstHotel = new ArrayList<>();

        Hotel hotel = new Hotel();
        hotel.setName("Hotel 1");
        hotel.setSomehting();

        lstHotel.add(hotel);

    }
}
