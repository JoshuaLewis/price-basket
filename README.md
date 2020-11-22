# price-basket

This application can be packaged using SBT
The reference manual for SBT can be found [here](https://www.scala-sbt.org/1.x/docs/index.html)
And a nice installation guide can be found [here](https://docs.scala-lang.org/getting-started/sbt-track/getting-started-with-scala-and-sbt-on-the-command-line.html#installation)
Once set up
The easiest way to run the application is to use sbt from the command line:
(which should work on Mac/Windows/linux - tested on Mac only)

Git clone this repo.
from the created repo (e.g. /price-basket)

Example:
```
sbt "run apples apples soup"
```

This should run the application with the arguments following run (apples apples soup)

Alternatively you can build the jar using:
```
sbt assembly
```
This will create the jar in the folder target/scala-2.13/
Which will include all the of the scala dependencies so will run via java -jar, eg.
```
java -jar <name>.jar apples apples apples
```