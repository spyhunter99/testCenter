# testCenter
Test Center - An experimental distributed testing solution for Java/Android applications


## Requirements

- From a central location, be able to execute a series of tests against remote devices, gather and aggregate the results and produce a test report
- Continous integration friendly
- Support Android application testing similiar to running connectedCheck against a device
- Support device to device interactions such as
 - Device 1 clicks a button, which propigates a change somehow to Device #2
 - Device 2 waits for the change, if the change is received and the view is updated, success. If change has not occurred after a given period of time, mark the test as a failure and continue.


## Base assumptions

- Remove devices are accessible via network to the central location

## An example scenario for Android apps (how it could work)

Project structure (app to be tested)

 - Distributed Client base - an AAR/JAR
   - UDP receiver and broadcaster, contains all the base messaging protocols and parsers
   - TestedAppInterface that relays information to the application being tested
 - Server - contains the orchestration mechanisms to automate the testing procedures
 - Application
   - Debug flavor - implements the TestedAppInterface
   - Release flavor - has the same API as the debug version but is NOOP

Each device emmits a UDP broadcast heart beat
