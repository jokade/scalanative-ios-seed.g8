import cocoa.foundation._
import cocoa.uikit.{UIApplication, UIApplicationDelegate, UIResponder}
import de.surfice.smacrotools.debug
import objc.ScalaObjC

@ScalaObjC
@debug
class AppDelegate(self: NSObject) extends UIResponder with UIApplicationDelegate {

  override def application(application: UIApplication, didFinishLaunchingWithOptions: NSDictionary[NSObject,NSObject]): BOOL = {
    true
  }

  //  private var _clickCount = 0
//
  /* Outlets */
//  var window: NSObject = _
  private var _window: NSObject = _
  def window: NSObject = _window
  def window_=(w: NSObject): Unit = {
    w.retain()
    _window = w
  }
//  var clickCountView: NSTextField = _
//
//  /* Actions */
//  def takeClick(id: NSObject): Unit = {
//    _clickCount += 1
//    updateView()
//  }
//
//  override def applicationDidFinishLaunching(notification: NSNotification): Unit = {
//    updateView()
//  }
//
//  private def updateView(): Unit = {
//    clickCountView.setIntegerValue(_clickCount)
//  }
}

