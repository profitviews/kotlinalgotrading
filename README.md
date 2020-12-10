# kotlinalgotrading
Basis for writing algo trading systems in Kotlin

## Installation

How to install everything required to run the code in this repo

On all platforms, you will need `git`, a JDK, `gradle` and Kotlin itself.  It's probably best to have [Jetbrains IDEA](https://www.jetbrains.com/idea/) too.

### Windows

There are various security settings that are typically defaulted to in a Windows installation.  You may need to turn some of these off, for example any restriction on downloading files.  It is possible to do this on a domain-by-domain basis, and this is encouraged.
If you wish to generally allow file downloads, you can do it (on Windows Server 2019) by:
1. Click the lower left corner "Window" symbol "Start" menu.
2. Choose "Settings" and search for "Change Security Settings"
3. Choose the "Security" tab and select the zone "Internet"
4. Un-check the "Enable Protected Mode"
5. Choose "Custom" and scroll down to "Downloads"
6. On "File download" choose "Enable" and click "Okay" boxes to exit the dialog

#### Git

Go to https://git-scm.com/download/win and click the appropriate download button.  Click to run the downloaded install file.
In the options generally choose the defaults.  Make sure you choose to enable **Git Bash**

#### GitHub

Go to https://github.com and create an account.  Run Git Bash (go to the Windows "start" menu and find it or search for it there).
The `git` installation will have created a directory `.ssh` in your home directory (typically `C:\Users\Username`)
In Git Bash, do `cd` (to go to your home directory) and `cd .ssh`.  Then run `ssh-keygen -r rsa` and choose all the defaults.
This will generate `id_rsa` and `id_rsa.pub` in that directory
Open `id_rsa.pub` and copy its complete text
While logged in to your GitHub account, go to https://github.com/settings/keys and in the **SSH keys** section click the "New SSH key" button.  Give it a title and then paste the contents of `id_rsa.pub` into the "Key" field and click "Add SSH key".

Now in Git Bash choose a directory in which you wish to place this `kotlinalgotrading` directory, `cd` to it and do:
`git clone git@github.com:profitviews/kotlinalgotrading.git`
This should copy this repo into your chosen directory.

#### Java

Find a Java vendor and download and install a recent JDK version.  It must be version 8 or later (or whatever version required by [Gradle](#Gradle)).
[OpenJDK](https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot) will work fine.  If you choose the [.msi](https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_windows_hotspot_15.0.1_9.msi), after download you need only to click it to install.  During installation choose to have it set `JDK_HOME` and the Registry variables.

#### Kotlin

Go to https://kotlinlang.org/docs/tutorials/command-line.html and under Downloading the compiler click "GitHub Releases".  Go down to "Assets" and choose the latest `kotlin-compiler-x.y.z.zip` (not the `native` versions) and download.  Unzip to a directory of your choice.

#### Gradle

In https://gradle.org/install/ go to [Installing manually](https://gradle.org/install/#manually) and follow the instructions for "Microsoft Windows users"

### Linux and MacOS

Since both operating systems are derived from Unix, the installations are similar.

#### Git

Open a terminal window.  Use your package manager to install `git`.  On MacOS this will be `brew install git` and on Debian derivatives including Ubuntu, `apt install git`.  See [Git's Linux page](https://git-scm.com/download/linux) for [PPAs](https://itsfoss.com/ppa-guide/) for the latest versions.

#### GitHub

Go to https://github.com and create an account.  In a terminal do:
```shell
cd ~/.ssh
ssh-keygen -t RSA # Press enter for all the defaults
```
This will generate an SSH key pair `id_rsa` and `id_rsa.pub` in `.ssh`
Open `id_rsa.pub` and copy its complete text.
While logged in to your GitHub account, go to https://github.com/settings/keys and in the **SSH keys** section click the "New SSH key" button.  Give it a title and then paste the contents of `id_rsa.pub` into the "Key" field and click "Add SSH key".

Choose a directory in which you wish to place this `kotlinalgotrading` directory, `cd` to it and do:
`git clone git@github.com:profitviews/kotlinalgotrading.git`
This should copy this repo into your chosen directory.

#### Java

Find a Java vendor and download and install a recent JDK version.  It must be version 8 or later (or whatever version required by [Gradle](#Gradle)).
[OpenJDK](https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot) will work fine.  Download and install and set do 
```shell
export JDK_HOME=/the/directory/you/installed/it
```
Note that the directory you supply in this line should be the one immediately **above** the `bin` directory.

#### Kotlin

Go to https://kotlinlang.org/docs/tutorials/command-line.html and under Downloading the compiler click "GitHub Releases".  Go down to "Assets" and choose the latest `kotlin-compiler-x.y.z.zip` (not the `native` versions) and download.  Unzip to a directory of your choice.

#### Gradle

First make sure you have `curl` installed from your package manager e.g. `sudo apt install` (Debian/Ubuntu) or `brew install curl` (MacOS). Then:
```shell
curl -s "https://get.sdkman.io" | bash
```
and follow the instructions to install [`sdk`](http://sdkman.io/) which is `gradle`'s package manager.
Finally `sdk install gradle` to install Gradle.  On MacOS you can do `brew install gradle` if you prefer.

## Initializing Gradle Build

In Terminal (or Git Bash on Windows), `cd` to the directory where you cloned `kotlinalgotrading` and run `./gradlew tasks`.  This will download Gradle (if necessary) and the required libraries.

Then `./gradlew build` will build the system.

If you check [build.gradle.kts](https://github.com/profitviews/kotlinalgotrading/blob/main/build.gradle.kts) you will see it is configured to run the first example from the webinar - though you must first set your BitMEX API key and secret with
```shell
export $bitmex_api_key=YourApiKey
export $bitmex_secret=YourSecretString
```
Then you can run `./gradlew run` which will buy $10 worth of Bitcoin equivalent for you!  You can change it to run the other examples.

## Jetbrains IntelliJ IDEA

[Jetbrains](http://jetbrains.com/) created Kotlin and continue to manage its development.  Therefore you can count on their IDE being well adapted to the language.  We recommend you use [IntelliJ IDEA](https://www.jetbrains.com/idea/).  The installation is straightforward on all the platforms.
