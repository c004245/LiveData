package hyunwook.co.kr.paging_livedata;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import hyunwook.co.kr.paging_livedata.entity.Word;
import hyunwook.co.kr.paging_livedata.adapter.WordListAdapter;
import hyunwook.co.kr.paging_livedata.viewmodel.WordViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    RecyclerView mRecyclerView;
    FloatingActionButton floatingActionButton;
    private WordViewModel mWordViewModel;

    private Aquarium myAquarium;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingBtn);
        mRecyclerView = findViewById(R.id.recyclerView);
        final WordListAdapter adapter = new WordListAdapter(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, words -> {
            Log.d(TAG, "onChange!");
            adapter.setWords(words);
        });

        floatingActionButton.setOnClickListener(l -> {
            Intent intent = new Intent(this, NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        myAquarium = new Aquarium(this.getApplication(), getLifecycle());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    public class Aquarium {

        Aquarium(final Application app, Lifecycle lifecycle) {
            lifecycle.addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                public void start() {
                    Log.d("LifecycleListener", "LIGHT");
                    Toast.makeText(app, "LIGHTS ON", Toast.LENGTH_LONG).show();
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                public void stop() {
                    Log.d("LifecycleListener", "LiGHT OFF");
                    Toast.makeText(app, "Lights off", Toast.LENGTH_LONG).show();
                }


            });

        }
    }

}
