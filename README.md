# Connected
Connected Cities

Used to check if 2 specified cities (cityname1 and cityname2) are connected based on the list of connections provided via
a text file.

Note: Nothing is case sensitive as city names are supposed to be unique.
      If cityname1 == cityname2, we assume they are connected without reading the file.

Setup:

$ git clone https://github.com/parikhravish/Connected.git
$ cd Connected/src
$ javac Connected.java
$ java Connected CitiesTest.txt "Boston" "Hartford"

Usage: java Connected <<filename>> <<cityname1>> <<cityname2>> 