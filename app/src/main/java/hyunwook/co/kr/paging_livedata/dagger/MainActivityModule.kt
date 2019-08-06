package hyunwook.co.kr.paging_livedata.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hyunwook.co.kr.paging_livedata.MainActivity

@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity
}