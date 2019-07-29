package hyunwook.co.kr.paging_livedata.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import hyunwook.co.kr.paging_livedata.entity.Word
import hyunwook.co.kr.paging_livedata.entity.WordDao
import hyunwook.co.kr.paging_livedata.room.WordRoomDatabase

class WordRepository constructor(application: Application) {

    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>
    
    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    companion object {
        class insertAsyncTask constructor(dao: WordDao): AsyncTask<Word, Void, Void>() {

            private val mAsyncTaskDao: WordDao = dao

            override fun doInBackground(vararg params: Word?): Void? {
                params[0]?.let { mAsyncTaskDao.insert(it) }
                return null
            }
        }
    }
}