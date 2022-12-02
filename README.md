<font size=4 color=#00008B>**UI/API testing framework**</font>
_________
<font size=3 color=#000033><p style="font-variant:inherit">This project is a simple framework for performing automated UI/API tests of websites. It used design patterns:

   -	Page Object 

   -	Singleton <sub>for a driver instance</sub> 

   -	Factory Method <sub>for choosing a web browser</sub></p></font>
____

<font size=3 color=#000033><p style="font-variant:inherit">At first you need to add values to the “url” and “browserName” in the configdata.json package project.data. To select a web browser, you need to fix browserName <small>(“chrome” to run tests on Google Chrome or “firefox” to run tests on Mozilla Firefox).</small>

To create the driver instance in your test method use 
`DriverSingleton.getDriver()`

To clear the driver instance in your test method use 
`DriverSingleton.clearSingleton()`

To work with pages or forms you need to create class and extend it from the BaseForm class.

To read the data correctly, you need to store your data in the project.data package.</p>
</font>
