We use the Google Java Formatter to automatically format our source code. Details of the formatter can be found [here](https://github.com/google/google-java-format). This is executed through the Spotless gradle plugin. Details of that can be found [here](https://github.com/diffplug/spotless)

# Gradle

To configure a microservice to use this you need to modify the `build.gradle` to include the following:

```
    dependencies {
        ...
        classpath('com.diffplug.spotless:spotless-plugin-gradle:3.2.0')
    }
...
apply plugin: 'com.diffplug.gradle.spotless'
...
spotless {
    java {
        googleJavaFormat()
    }
}
...

```

Now when you perform a `./gradlew build` it will check ALL source files for compliance and fail the build if there are any compliance mismatches.

To fix the issues you can issue a `./gradlew spotlessApply`. After this you should add/commit/push your changes and the issue a pull request to get the change into the master.

# Eclipse
[This page](https://github.com/google/google-java-format) contains information about the IDE plugin for Eclipse
# IntelliJ
[This page](https://github.com/google/google-java-format) contains information about the IDE plugin for IntelliJ
# Trouble shooting
If you find issues with spotless adding CR/LF changes when doing a git diff then execute the below command.

`git config --global core.autocrlf true`