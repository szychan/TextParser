# TextParser
TextParser parses text into sentences. output in specified by user format.
Currently application is capable of producing xml and csv formats.

## Prerequisites

Download and install following tools:

Git from [https://git-scm.com/downloads](https://git-scm.com/downloads)

Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

## Installation

Firstly clone project to Your directory by using 
```
git clone https://github.com/szychan/TextParser.git
```

build it with:
```
mvn install
```
this will create TextParser.jar file in project folder.

## Working with Application

To run the application type
```
java -jar TextParser.jar format
```
in command line. format is either "csv" or "xml"
Then Application will wait for input text to process.

## Tips
It is also possible to run the application by calling jar  with specified input file and output file by typing.
```
java -jar TextParser.jar xml < "input path" > "output path"
```
in CommandLine.

## Authors

* **Sebastian Ceronik** 
