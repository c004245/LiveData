package hyunwook.co.kr.paging_livedata.room

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hyunwook.co.kr.paging_livedata.entity.Word
import hyunwook.co.kr.paging_livedata.entity.WordDao

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordRoomDatabase::class.java, "word_database"
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE).execute()
            }
        }
        class PopulateDbAsync constructor(db: WordRoomDatabase?) : AsyncTask<Void, Void, Void>() {
            private val mDao = db?.wordDao()
            private var words = arrayOf("dolphin", "crocodile", "cobra")

            override fun doInBackground(vararg params: Void?): Void? {
                mDao?.deleteAll()

                for (i in 0 until words.size) {
                    val word = Word(words[i])
                    mDao?.insert(word)
                }
                return null
            }
        }
    }


    
}