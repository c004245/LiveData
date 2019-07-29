package hyunwook.co.kr.paging_livedata.viewmodel

import androidx.lifecycle.ViewModel
import hyunwook.co.kr.paging_livedata.entity.User

class UserProfileViewModel : ViewModel() {

    private lateinit var userId: String
    private lateinit var user: User

    fun init(userId: String) {
        this.userId = userId
    }

    fun getUser(): User {
        return user
    }
}