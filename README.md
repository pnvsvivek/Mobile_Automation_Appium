# Mobile_Automation_Appium
This repository mainly contains methods for writing test scripts for mobile automation for android applications using Appium server
Appium is a server which mainly facilitates the mobile automation testing for android and ios platforms. Initially node.js new version needs to get installed on our system. Once node.js installation is complete then open node.js command prompt then type 'npm install -g appium' which will install newer version of appium. After this, we have to set the IP and port number by using 'appium -a 0.0.0.0 -p 4723' in node.js command prompt so IP and Port number is set. anyways by default appium has this IP and port number. Now we have to come to Eclipse IDE and will have to set Desired Capabilties based on the platform and application which includes appPackage & appWaitActivity for native aplications and browserName for mobile browser applications. Followed TestNG for creating test scripts. Reporting we used is Extent Reports 2.0 and this HTML report can be seen with the help of 
