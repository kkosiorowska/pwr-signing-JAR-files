# pwr-signing-JAR-files

## Command to verifying a signed JAR file is:
  
* jarsigner -verify jar-file
* jarsigner -verify -keystore ..\Key\knapsackstore sknapsackProblem.jar

## Generate Keys

* keytool -genkey -alias signFiles -keystore examplestore

## Sign the JAR File

* jarsigner -keystore examplestore -signedjar sCount.jar Count.jar signFiles 