import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class JetstarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}
