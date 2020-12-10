# kotlinalgotrading
Basis for writing algo trading systems in Kotlin

## Installation

How to install everything required to run the code in this repo

### Windows

On Windows, like all platforms, you will need `git`, a JDK, `gradle` and Kotlin itself.  It's probably best to have Jetbrains IDEA too.
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

#### Initializing Gradle Build

In Git Bash, `cd` to the directory where you cloned `kotlinalgotrading` and run `./gradlew tasks`.  This will download Gradle (if necessary) and the required 
