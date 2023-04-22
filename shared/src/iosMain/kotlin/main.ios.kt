import androidx.compose.ui.window.ComposeUIViewController // ktlint-disable filename
import di.initKoin
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController { App() }
}
