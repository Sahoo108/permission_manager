## Managing Permissions in Android

To add the Permission Manager library to your project, follow the steps below:

### Step 1: Add JitPack Repository

Add the JitPack repository in your root `build.gradle` at the end of `repositories` block:

```gradle
// root/build.gradle

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

// app/build.gradle

dependencies {
    implementation 'com.github.Sahoo108:permission_manager:1.1'
}
