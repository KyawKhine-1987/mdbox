package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import com.witts.mdbox.R;
import com.witts.mdbox.fragments.MenuFragment;


public class MenuActivity extends BasedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, MenuFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
