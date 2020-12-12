How to install everything required to run the code in this repo.  Note that if you have **any issues** please join the [ProfitViewers Slack](https://join.slack.com/t/profitviewers/shared_invite/zt-k5kxhxb4-vyz~hTlFlrgAtPiQbq~Pcg) and ask in the **#kotlin** channel.

## BitMEX

You need an account with [BitMEX](https://www.bitmex.com/).  This is not available for residents of the United States or Hong Kong yet, unfortunately.

Signing up to BitMEX requires authentication.  You will need to enable your microphone and webcam (temporarily) as part of this process - they ask you to repeat some numbers and to obey some simple instructions.  You will need to send a photograph of a government issued id - driver's license or passport e.g.
This may take a few hours - or perhaps a day if there is some complication.

You need some Bitcoin to transfer to your BitMEX wallet.  I buy from [CoinBase](https://www.coinbase.com/), however you may have a preferred vendor.  Once you have bought Bitcoin you transfer it to the BitMEX wallet - click the [Deposit](https://www.bitmex.com/app/wallet) button and copy the wallet id to provide when you send from the vendor.

You need to generate **2** [API key pairs](https://www.bitmex.com/app/apiKeys), one with Key Permissions blank (-) which means **Read Only**, for ProfitView and one with Key Permissions "Order" for your algo so that it can trade.  Copy both these to a safe place (you won't be able to see the API Secret again unless you make a copy).  Make sure you distinguish which is which.

See [Crypto Trading From Scratch](https://profitview.net/blog/crypto-trading-from-scratch) for some more information and detail.

## ProfitView

You need a [ProfitView](https://profitview.net/) account (the free tier is adequate) - [Sign-up](https://profitview.net/register) and go to your email to confirm.  It will prompt you to enter your BitMEX API key/secret pair.  Make sure you enter the **Read Only** key here.

Go to the ProfitView "Settings" tab.  This is where you find your generated ProfitView API Key.  You will need this to retrieve real-time market data.

On all platforms, you will need `git`, a JDK, `gradle` and Kotlin itself.  It's probably best to have [Jetbrains IDEA](https://www.jetbrains.com/idea/) too.

See [ProfitView for Dummies](https://profitview.net/blog/profitview-for-dummies) for more detail.

## Windows

There are various security settings that are typically defaulted to in a Windows installation.  You may need to turn some of these off, for example any restriction on downloading files.  It is possible to do this on a domain-by-domain basis, and this is encouraged.
If you wish to generally allow file downloads, you can do it (on Windows Server 2019) by:
1. Click the lower left corner "Window" symbol "Start" menu.
2. Choose "Settings" and search for "Change Security Settings"
3. Choose the "Security" tab and select the zone "Internet"
4. Un-check the "Enable Protected Mode"
5. Choose "Custom" and scroll down to "Downloads"
6. On "File download" choose "Enable" and click "Okay" boxes to exit the dialog

### Git

Go to https://git-scm.com/download/win and click the appropriate download button.  Click to run the downloaded install file.
In the options generally choose the defaults.  Make sure you choose to enable **Git Bash**

### Clone this Repo

In Git Bash create a directory in which you wish to place this `kotlinalgotrading` repo, `cd` to it and do:
```git clone git@github.com:profitviews/kotlinalgotrading.git```
This will clone this repo into your chosen directory.

### Java

Go to [OpenJDK](https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot) and install JDK version 8 or greater. If you choose the [.msi](https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_windows_hotspot_15.0.1_9.msi), after download you need only to click it to install.  During installation choose to have it set `JDK_HOME` and the Registry variables.

### Kotlin

Go to [this](https://kotlinlang.org/docs/tutorials/command-line.html) webpage and under Downloading the compiler click "GitHub Releases".  Go down to "Assets" and choose the latest `kotlin-compiler-x.y.z.zip` (not the `native` versions) and download.  Unzip to a directory of your choice.

### Gradle

In https://gradle.org/install/ go to [Installing manually](https://gradle.org/install/#manually) and follow the instructions for "Microsoft Windows users"

## Linux and MacOS

Since both operating systems are derived from Unix, the installations are similar.

### Git

Open a terminal window. On MacOS run `brew install git` and on Debian derivatives including Ubuntu run `sudo apt install git`. 

### Clone this Repo

Create a directory in which you wish to place this `kotlinalgotrading` repo, `cd` to it and do:
```git clone git@github.com:profitviews/kotlinalgotrading.git```
This will clone this repo into your chosen directory.

### Java

Go to [OpenJDK](https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot) and install JDK version 8 or greater. For Mac OS you should be able to confirm the version you installed by running `java --version`. On Linux after installing you may need to update your `.bashrc` or equivalent to include the line
```shell
export JDK_HOME=<path to your install directory>/bin/java
```

### Kotlin

On Mac OS you can simply run `brew install kotlin`. For Linux go to [this](https://kotlinlang.org/docs/tutorials/command-line.html) webpage.  Go down to "Assets" and choose the latest `kotlin-compiler-x.y.z.zip` (not the `native` versions) and download.  Unzip to a directory of your choice.

### Gradle

For Mac OS install by running `brew install gradle`. On Linux run:
```shell
curl -s "https://get.sdkman.io" | bash
```
and follow the instructions to install [`sdk`](http://sdkman.io/) which is `gradle`'s package manager. Finally `sdk install gradle` to install Gradle.  

# Initializing Gradle Build

In Terminal (or Git Bash on Windows), `cd` to the directory where you cloned `kotlinalgotrading` and run `./gradlew tasks`.  This will download Gradle (if necessary) and the required libraries.

Then `./gradlew build` will build the system.

If you check [build.gradle.kts](https://github.com/profitviews/kotlinalgotrading/blob/main/build.gradle.kts) you will see it is configured to run the first example from the webinar - though you must first set your BitMEX API key and secret with
```shell
export bitmex_api_key=YourApiKey
export bitmex_secret=YourSecretString
```
If you're using Jetbrains IDEA, you can set these environment variable in a Run Configuration: Run menu -> Edit configuations... -> Environment variables.  Click the icon on the right of the text box to enter multiple variables.

Then you can run `./gradlew run` which will buy $10 worth of Bitcoin equivalent for you!  

To run the other examples, edit [build.gradle.kts](https://github.com/profitviews/kotlinalgotrading/blob/main/build.gradle.kts) in your cloned `kotlinalgotrading` directory, and change 
```kotlin
application {
    mainClass.set("webinar1.ExecuteExampleKt")
}
```
to
```kotlin
application {
    mainClass.set("webinar2.MarketDataExampleKt")
}
```
or similar with `webinar3.SimpleKt` or `webinar4.MeanReversionKt`.

Then execute `./gradlew run` again.

**NOTE**: to run `webinar2.MarketDataExampleKt`, `webinar3.SimpleKt` and `webinar4.MeanReversionKt` - or any algo - you will need your ProfitView API Key from above and write it to the `profitview_api` environment variable with:
```shell
export profitview_api_key=YourProfitViewAPIKey
```
or put it in your IDEA Run Configuration.

# Jetbrains IntelliJ IDEA

[Jetbrains](http://jetbrains.com/) created Kotlin and continue to manage its development.  Therefore you can count on their IDE being well adapted to the language.  We recommend you use [IntelliJ IDEA](https://www.jetbrains.com/idea/).  The installation is straightforward on all the platforms.
