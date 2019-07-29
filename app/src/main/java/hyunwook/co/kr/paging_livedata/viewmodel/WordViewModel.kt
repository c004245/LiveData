package hyunwook.co.kr.paging_livedata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import hyunwook.co.kr.paging_livedata.entity.Word
import hyunwook.co.kr.paging_livedata.repository.WordRepository

class WordViewModel constructor(application: Application): AndroidViewModel(application) {

    private val mRepository = WordRepository(application)
    val allWords: LiveData<List<Word>> = mRepository.getAllWords()

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}