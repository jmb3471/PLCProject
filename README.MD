# Jott Compiler

## Phase 1
To build phase 1, call the JottTokenizer function with the desired file name. We have included the
JottTokenizerTester as well if it is desired to use that to build phase 1.

## Phase 2
To build phase 2, call the JottParse parse function with a list of tokens made from JottTokenizer,
We have included the JottParserTester as well if it is desired to use that to build phase 2

## Phase 3/4
To build phase 3 call the Jott Class main function with arguments input file, output file, language
So an example call would be 
```Java Jott input.jott output.language language
Java Jott input.jott output.lan Language

Java Jott helloWorld.jott helloWorld.py Python

```
