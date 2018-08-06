A [Giter8][g8] template for iOS applications written in Scala using [scalanative-cocoa](https://github.com/jokade/scalanative-cocoa).

1. Apply the template
```
# g8 jokade/scalanative-ios-seed.g8
```
For now, accept the default settings (especially the name "App", since otherwise the Xcode project configuration has errors).

2. Build the executable for iPhone simulator:
```
# cd app
# sbt sim/build
```

3. Open project in Xcode and run app in simulator
```
# open xcode/App.xcodeproj
```

Template license
----------------
Written in 2018 by jokade.

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
