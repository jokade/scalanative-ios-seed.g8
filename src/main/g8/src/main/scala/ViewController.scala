import cocoa.foundation._
import cocoa.uikit.{UIViewController, UIViewControllerClass}
import de.surfice.smacrotools.debug
import objc.ScalaObjC

@ScalaObjC
class ViewController(self: UIViewController) extends UIViewController {
  override def viewDidLoad(): Unit = {
    Foundation.NSLog(ns"loading")
    \$super(self)(_.viewDidLoad())
  }
}

//object ViewController extends UIViewControllerClass {
//  override type InstanceType = UIViewController
//}
