package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import com.witts.mdbox.R;
import com.witts.mdbox.fragments.HotelRoomDetailFragment;

public class HotelRoomDetailActivity extends BasedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_room_detail);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, HotelRoomDetailFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
