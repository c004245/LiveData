package hyunwook.co.kr.paging_livedata.entity;

import android.support.annotation.NonNull;

public class Word {
    private String mWord;

    private Word(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }
}
