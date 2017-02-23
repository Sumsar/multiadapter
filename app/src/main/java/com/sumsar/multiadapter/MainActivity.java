package com.sumsar.multiadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sumsar.multiadapter.adapters.AdapterOne;
import com.sumsar.multiadapter.adapters.AdapterThree;
import com.sumsar.multiadapter.adapters.AdapterTwo;
import com.sumsar.multiadapter.adapters.ItemClickListener;
import com.sumsar.multiadapter.adapters.ItemOne;
import com.sumsar.multiadapter.adapters.ItemThree;
import com.sumsar.multiadapter.adapters.ItemTwo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MultiAdapter mMultiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMultiAdapter = new MultiAdapter();
        recyclerView.setAdapter(mMultiAdapter);
        initAdapter1();
        initAdapter2();
        initAdapter3();
    }

    private void initAdapter1() {
        final List<ItemOne> items = Collections.singletonList(new ItemOne());
        final AdapterOne adapterOne = new AdapterOne(new ItemClickListener<ItemOne>() {
            @Override
            public void onClick(View view, ItemOne item) {
                mMultiAdapter.removeAndNotify(item);
            }
        });
        mMultiAdapter.register(adapterOne);
        mMultiAdapter.addAndNotify(items);


    }

    private void initAdapter2() {
        AdapterTwo adapterTwo = new AdapterTwo(new ItemClickListener<ItemTwo>() {
            @Override
            public void onClick(View view, ItemTwo item) {
                mMultiAdapter.addAndNotify(Collections.singletonList(new ItemOne()));
            }
        });
        final List<ItemTwo> items = Collections.singletonList(new ItemTwo());
        mMultiAdapter.register(adapterTwo);
        mMultiAdapter.addAndNotify(items);
    }

    private void initAdapter3() {
        AdapterThree adapter = new AdapterThree();
        final List<ItemThree> items = Arrays.asList(new ItemThree("One"), new ItemThree("Two"), new ItemThree("Three"));
        mMultiAdapter.register(adapter);
        mMultiAdapter.addAndNotify(items);
    }
}
