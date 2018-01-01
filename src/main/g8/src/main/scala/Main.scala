import cocoa.foundation._
import cocoa.uikit.UIKit

object Main {
  def main(args: Array[String]): Unit = {
    AppDelegate.__cls  // register AppDelegate ObjC class
    ViewController.__cls
    UIKit.UIApplicationMain(0,null,null,ns"AppDelegate")
  }
}
