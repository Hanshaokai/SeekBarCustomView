
package com.example.lxr_yfb.seekbarcustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SlidingBlockView viewById;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (SlidingBlockView) findViewById(R.id.seekbar);

        viewById.setSelecteItemTotalNumLisnter(10, 10, new SlidingBlockView.SelecteItemTotalNumLisnter() {
            @Override
            public void getSelectItemTotalNum(int totalNum) {
                Log.d("tag", " jj" + totalNum);
            }
        });

    }
}

