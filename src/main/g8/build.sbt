
lazy val commonSettings = Seq(
  scalaVersion := "$scala_version$",
  name := "$name$",
  organization := "$organization$", 
  resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  nativeLinkingOptions := Seq(
    "-lobjc",
    "-framework","Foundation",
    "-framework","UIKit"
    ),
  nativeGC := "none",
  libraryDependencies ++= Seq(
    "de.surfice" %%% "scalanative-cocoa-uikit" % "$scalanative_cocoa_version$"
  )
)

lazy val sdk            = settingKey[String]("Name of the SDK to be used")
lazy val bundlePath     = settingKey[File]("Path to the target bundle directory")
lazy val xcodeBuildPath = settingKey[File]("Path to the Xcode build directory")
lazy val build          = taskKey[File]("Creates the executable for the simulator")

lazy val sim = project
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings:_*)
  .settings(
    sourceDirectory := baseDirectory.value / ".." / "src",
    bundlePath := baseDirectory.value / ".." / "xcode" / "DerivedData" / "Build" / "Products" / "Debug-iphonesimulator" / (name.value + ".app"),
    artifactPath in nativeLink := bundlePath.value / name.value,
    xcodeBuildPath := baseDirectory.value / "xcode" / "DerivedData" / "Build" / "Products" / "Debug" / (name.value + ".app"),
    nativeLinkingOptions ++= Seq(
      "-o",(artifactPath in nativeLink).value.getAbsolutePath,
      "-mios-simulator-version-min=10.2",
      "-isysroot","/Applications/Xcode.app/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/"
      ),
    nativeCompileOptions ++= Seq(
      "-mios-simulator-version-min=10.2",
      "-isysroot","/Applications/Xcode.app/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/"
      ),
    build := {
      IO.createDirectory((artifactPath in nativeLink).value.getParentFile)
      (nativeLink in Compile).value
    }
  )


