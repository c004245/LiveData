package hyunwook.co.kr.paging_livedata.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (@PrimaryKey private val id: Int, private val name: String, private val lastName: String)
