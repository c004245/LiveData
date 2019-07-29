package hyunwook.co.kr.paging_livedata

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import hyunwook.co.kr.paging_livedata.adapter.WordListAdapter
import hyunwook.co.kr.paging_livedata.entity.Word
import hyunwook.co.kr.paging_livedata.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mWordViewModel: WordViewModel
    private lateinit var myAquarium: Aquarium

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.allWords.observe(this, Observer { words ->
            adapter.setWords(words)
        })


        floatingBtn.setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

        myAquarium = Aquarium(this.application, lifecycle)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordViewModel.insert(word)
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    class Aquarium internal constructor(val app: Application, lifecycle: Lifecycle) {
        init {
            lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun start() {
                    Toast.makeText(app, "LIGHT ON", Toast.LENGTH_LONG).show()
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun stop() {
                    Toast.makeText(app, "LIGHT OFF", Toast.LENGTH_LONG).show()
                }
            })
        }

    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }


}