lazy val xcodePath       = settingKey[String]("Path to Xcode bundle")
lazy val sysroot         = settingKey[String]("Path to the sysroot dir used for includes")
lazy val bundlePath      = settingKey[File]("Path to the target bundle directory")
lazy val iosVersion      = settingKey[String]("Minimum iOS target version")
lazy val iosArch         = settingKey[String]("CPU architecture of the iOS device")
lazy val sncocoaVersion  = settingKey[String]("Version of scalanative-cocoa")
//lazy val xcodeBuildPath = settingKey[File]("Path to the Xcode build directory")
lazy val build           = taskKey[File]("Creates the executable for the simulator")
lazy val prepareBundle   = taskKey[File]("Prepare target bundle directory")

lazy val frameworks = Seq(
  "Foundation",
  "UIKit"
)

lazy val config = Seq(
  scalaVersion := "$scala_version$",
  name         := "$name$",
  organization := "$organization$", 
  xcodePath    := "/Applications/Xcode.app/",
  iosVersion   := "10.2",
  iosArch      := "arm64",
  sncocoaVersion := "$scalanative_cocoa_version$",
  nativeGC     := "none"
)


lazy val commonSettings = Seq(
  resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  nativeLinkingOptions := Seq("-lobjc"),
  nativeLinkingOptions ++= frameworks.flatMap( fw => Seq("-framework",fw) ),
  libraryDependencies ++= frameworks.map( fw => "de.surfice" %%% s"scalanative-cocoa-\${fw.toLowerCase}" % sncocoaVersion.value ),
  prepareBundle := {
    IO.createDirectory((artifactPath in nativeLink).value.getParentFile)
    bundlePath.value
  }
)

lazy val root = project.in(file("."))
  .enablePlugins(ScalaNativePlugin)
  .settings(config ++ commonSettings:_*)
  .settings(
    bundlePath := baseDirectory.value / ".." / "xcode" / "DerivedData" / "Build" / "Products" / "Debug-iphoneos" / (name.value + ".app"),
    artifactPath in nativeLink := bundlePath.value / name.value,
    sysroot := xcodePath.value + "/Contents/Developer/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS.sdk/",
    nativeLinkingOptions ++= Seq(
      "-o",(artifactPath in nativeLink).value.getAbsolutePath,
      "-arch",iosArch.value,
      s"-mios-version-min=\${iosVersion.value}",
      "-isysroot",sysroot.value
      ),
    nativeCompileOptions ++= Seq(
      "-arch",iosArch.value,
      s"-mios-version-min=\${iosVersion.value}",
      "-isysroot",sysroot.value
      ),
    build := {
      prepareBundle.value
      (nativeLink in Compile).value
    }
  )

lazy val sim = project
  .enablePlugins(ScalaNativePlugin)
  .settings(config ++ commonSettings:_*)
  .settings(
    sourceDirectory := baseDirectory.value / ".." / "src",
    bundlePath := baseDirectory.value / ".." / "xcode" / "DerivedData" / "Build" / "Products" / "Debug-iphonesimulator" / (name.value + ".app"),
    artifactPath in nativeLink := bundlePath.value / name.value,
    sysroot := xcodePath.value + "/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/",
  //  xcodeBuildPath := baseDirectory.value / "xcode" / "DerivedData" / "Build" / "Products" / "Debug" / (name.value + ".app"),
    nativeLinkingOptions ++= Seq(
      "-o",(artifactPath in nativeLink).value.getAbsolutePath,
      s"-mios-simulator-version-min=\${iosVersion.value}",
      "-isysroot",sysroot.value
      ),
    nativeCompileOptions ++= Seq(
      s"-mios-simulator-version-min=\${iosVersion.value}",
      "-isysroot",sysroot.value
      ),
    build := {
      prepareBundle.value
      (nativeLink in Compile).value
    }
  )


