package hyunwook.co.kr.paging_livedata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Person(@PrimaryKey (autoGenerate = true) val uid: Int, @ColumnInfo (name = "first_name") val firstName: String, @ColumnInfo (name = "last_name") val lastName : String)