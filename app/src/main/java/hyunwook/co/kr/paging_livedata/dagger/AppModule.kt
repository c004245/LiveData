package hyunwook.co.kr.paging_livedata.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private var application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }
}