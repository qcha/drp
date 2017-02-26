## DRP
Dsv Resource Processing

### What is DSV?
Shortly: Delimiter Separated Values

More about: [`DSV`](https://en.wikipedia.org/wiki/Delimiter-separated_values)

### What's about DRP?
`DRP` - is a library which can work with `DSV`-resources. It's a extract level of `ETL`.

Resources can be:
* Common files - plain files
* Archive files - with compression or not.

And can storing as:
* Local
* Remote:
    * FTP
    * SFTP
    * FTPS

All what you need is resource [`URI`](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier).

### Why you should use it?
When you have a problem with extract level of `ETL`, you always work with files, and in 90% of cases - you work with `DSV` files. And writing your own extractor and parser for resources, which can be stored on ftp or can be archived - is not good idea. 
You can simply use `drp` library.
### What's inside?
* Java 8 
* CSV Parser: `super-csv`
* Working with archives: `commons-compress`
* Testing: `junit`
* Other: `lombok`, `guava`


[![Java 8](https://img.shields.io/badge/java-8-brightgreen.svg)](#java-8)
