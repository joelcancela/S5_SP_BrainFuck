## Macros

Macros must be declared at the beginning of the program with the prefix `$DEFINE`, one macro by line.<br>
A macro can call others macros, they must be separated by `/`. <br>
Example :

```
$DEFINE f ++++++++++
$DEFINE E f/f
$DEFINE r f/f/E/E/E/f/f
r
---
.#prints "a"
```
 
 Here the macros f and E are declared.<br>
 `f` means ten increments (+), note that you can also write long syntax instructions, you only have to put `/` before them.<br>
 The macro `E` calls the `f` macro twice.
 
### N-macros
 
 You can define macros with an argument using `()` after its name, this argument will be the number of times the macro will be executed when called.<br>
 Example :
 ```
 $DEFINE fgg() +++
 fgg 5
 ```

 In this case, fgg 5 will execute 5*3 increments.
 
### MULTI_DECR & TO_DIGIT
 
 There are two natives macros in our interpreter :
 MULTI_DECR & TO_DIGIT.
 
 * MULTI_DECR n: decrements the current memory cell n times.
 * TO_DIGIT: decrements the current memory cell 48 times (48 is the ascii code for 0)
 (calls MULTI_DECR 48)

### Notes
* If you call a macro with the name of a previous defined macro, it'll replace it.
* You can't name your macros with the same name of long-syntax instructions, functions, procedures