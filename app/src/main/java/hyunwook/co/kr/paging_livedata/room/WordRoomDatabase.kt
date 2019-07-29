package hyunwook.co.kr.paging_livedata.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hyunwook.co.kr.paging_livedata.entity.Word
import hyunwook.co.kr.paging_livedata.entity.WordDao

@Database(entities = { Word.class}, version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private val INSTANCE = WordRoomDatabase

        fun getDatabase(context: Context): Companion {
            if (INSTANCE == null) {
                    synchronized (WordRoomDatabase::class.java) {
                        if (INSTANCE == null) {
                            val INSTANCE = Room.databaseBuilder(context.applicationContext,
                                    WordRoomDatabase::class.java, "word_database")
                                .fallbackToDestructiveMigration()
                                .build()
                        }
                    }
                }
                return INSTANCE
        }
    }
}