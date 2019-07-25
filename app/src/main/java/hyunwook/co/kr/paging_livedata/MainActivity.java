package hyunwook.co.kr.paging_livedata;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyunwook.co.kr.paging_livedata.R;
import hyunwook.co.kr.paging_livedata.entity.Word;
import hyunwook.co.kr.paging_livedata.entity.WordListAdapter;
import hyunwook.co.kr.paging_livedata.viewmodel.WordViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView mRecyclerView;
    private WordViewModel mWordViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WordListAdapter adapter = new WordListAdapter(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                Log.d(TAG, "onChange!");
                adapter.setWords(words);
            }
        });
    }

}
