import cocoa.foundation._
import cocoa.uikit._
import de.surfice.smacrotools.debug
import scalanative.native._

@ScalaObjC
class ViewController(self: UIViewController) extends UIViewController {
  var labelView: UILabel = _

  /* Actions */
  def takeClick(id: NSObject): Unit = {
    labelView.setText(ns"Hello, Scala Native!")
  }

  override def viewDidLoad(): Unit = {
    \$super(self)(_.viewDidLoad())
    labelView.setText(ns"" )
  }
  
}
