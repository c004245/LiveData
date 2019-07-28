package hyunwook.co.kr.paging_livedata.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@PrimaryKey @field:NonNull @ColumnInfo(name = "word") @NonNull val word: String)
