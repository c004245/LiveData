package hyunwook.co.kr.paging_livedata.entity;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Update
    public void updateWords(Word... words);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM word_table WHERE word LIKE :word")
    public List<Word> findWord(String word);
}
