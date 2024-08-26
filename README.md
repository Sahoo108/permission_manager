Managing permission in Android


Add it in your root build.gradle at the end of repositories:

root/build.gradle
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

app/build.gradle
 dependencies {
	      implementation 'com.github.Sahoo108:permission_manager:1.1' 
	}
