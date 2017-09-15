## DRP
Dsv Resource Processing is a library for working with `DSV`-resources.

It's a extract level in `ETL`.

### What is DSV?
Shortly: Delimiter Separated Values

More about: [`DSV`](https://en.wikipedia.org/wiki/Delimiter-separated_values)

### When i should use it?
Sometimes Business store data in files and archives. For example - billing systems store therir billing data in archives with a lot of `CSV`-files - `DSV`-resources.

Resources can be:
* Common files - plain files
* Archive files - with compression or not.

And can be storing as:
* Local
* Remote:
    * FTP
    * SFTP
    * FTPS

And if you are not working with local files - you need to write own code for parsing archives and getting it from remote places.

To facilitate these actions, you can use `DRP`.

### How i can try it?
//todo

### What's inside?
* Java 8 
* CSV Parser: `super-csv`
* Working with archives: `commons-compress`
* Testing: `junit`
* Other: `lombok`, `guava`


[![Java 8](https://img.shields.io/badge/java-8-brightgreen.svg)](#java-8)
