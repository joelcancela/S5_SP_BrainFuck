## Macros

Macros must be declared at the beginning of the program with the prefix `$DEFINE`, one macro by line.<br>
A macro can call others macros, they must be separated by `/`. <br>
Example :

`
$DEFINE f ++++++++++
$DEFINE E f/f
$DEFINE r f/f/E/E/E/f/f
r
\---
.#affiche a
`
 
 Here the macros f and E are declared.<br>
 `f` means ten increments (+), note that you can also write long syntax instructions, you only have to put `/` before them.<br>
 The macro `E` calls the `f` macro twice.
 