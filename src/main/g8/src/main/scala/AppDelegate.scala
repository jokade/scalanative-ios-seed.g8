import cocoa.foundation._
import cocoa.uikit.{UIApplication, UIApplicationDelegate}
import de.surfice.smacrotools.debug

import scalanative.native._

@ScalaObjC
@debug
class AppDelegate(self: NSObject) extends NSObject with UIApplicationDelegate {

  def application(application: UIApplication, didFinishLaunchingWithOptions: NSDictionary[NSObject,NSObject]): BOOL = {
    true
  }

  /* Outlets */
  private var _window: NSObject = _
  def window: NSObject = _window
  def window_=(w: NSObject): Unit = {
    w.retain()
    _window = w
  }
}

