
package com.example.lxr_yfb.seekbarcustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SeekBarCustomeView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (SeekBarCustomeView) findViewById(R.id.seekbar);
        viewById.setSelecteItemTotalNumLisnter(31, 15, new SeekBarCustomeView.SelecteItemTotalNumLisnter() {
            @Override
            public void getSelectItemTotalNum(int totalNum) {
                Log.d("tag", " jj" + totalNum);
            }
        });

    }
}

