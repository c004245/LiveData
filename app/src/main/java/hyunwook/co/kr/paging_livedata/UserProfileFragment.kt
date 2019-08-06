/*
package hyunwook.co.kr.paging_livedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hyunwook.co.kr.paging_livedata.viewmodel.UserProfileViewModel

class UserProfileFragment : Fragment() {

    companion object {
        private val UID_KEY = "uid"
    }
    private lateinit var viewModel: UserProfileViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userId = arguments?.getString(UID_KEY)
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        viewModel.init(userId)
        viewModel.getUser().observe(this, Observer {
            user -> {
        }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview_item, container, false)
    }
}*/
