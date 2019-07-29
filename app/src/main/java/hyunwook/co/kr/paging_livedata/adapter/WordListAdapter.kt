package hyunwook.co.kr.paging_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hyunwook.co.kr.paging_livedata.R
import hyunwook.co.kr.paging_livedata.entity.Word

class WordListAdapter constructor(context: Context): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val mInflater = LayoutInflater.from(context)
    private var mWords: List<Word>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.text = current.word
        } else {
            holder.wordItemView.text = "No Word"
        }
    }

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return if (mWords != null)
            mWords!!.size
        else 0
    }

    inner class WordViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView = itemView.findViewById<TextView>(R.id.textView)!!
    }
}