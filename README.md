# BrainFuckSI3

Polytech'Nice SI3 group PolyStirN's BrainFuck project.

## What is this ?
This is an interpreter for the BrainFuck language (Mül93).
It can read textual files (\*.bf) and bitmaps (\*.bmp).

## Features
* Can interpret a BrainFuck textual file (\*.bf) or a bitmap (\*.bmp) using `-p` argument.
* Supports all default BrainFuck instructions in both short and long syntaxes :
    * \+ | INCR
    * \- | DECR
    * \< | LEFT
    * \> | RIGHT
    * \. | OUT, use the argument `-o` to specify an output file
    * \, | IN, use the argument `-i` to specify an input file
    * \[ | JUMP
    * \] | BACK <br> 
 
    [More details here](doc/brainfuck.md)
* Can rewrite a long syntax-written program into a short syntax written-program using `--rewrite`. <br>
The results are printed on stdout.
* Can translate a textual program into an image-based one using `--translate`. <br>
The results are printed on stdout, you can redirect with `>` in command line to a bmp file to create the picture.
* Can check if a program is well-formed (loops well formed and no wrong instructions) <br>
The results are printed on stdout.
* Show metrics for an execution of a program (shown in stdout, after the program results), [more details here](doc/metrics.md).
* Logging step to trace execution using `--trace`, creates a .log file where the program is to help the debug.
* Supports the code comments & indentation, you can use # (like in shell) to create your comments, tabulations, space and line breaks are supported.
* Supports macros, [more details here](doc/macros.md).
* Supports functions & procedures, [more details here](doc/procedures&functions.md).
* Can generate C code from any program using `--cgen`, creates a .c file in the program directory.

    
## Requirements
Maven and the JDK 8 are required to build the project.

## How to launch
### UNIX Systems
`./bfck`

### WINDOWS
`mvn install` <br>
`mvn -q exec:java -Dexec.mainClass="Main" -Dexec.args="[args]"`

---

## Upcoming Demos

Démo #3:
Semaine #2, 4 Janvier 2017, 14:25 (E-108) <br>

## TODO

- [ ] S21
- [ ] Script démo 3

---
## Done

### Week 50-01

- [x] End level 4 (S18->S20)
- [x] Tweaks

### Week 46-49

- [x] Oral presentation 3
- [x] End level 3 (S12->S17)
- [x] Rapport level 3
- [x] Keep calm and step back (L3)

### Week 41-45

- [x] Oral presentation 2
- [x] End level 2
- [x] Rapport level 2
- [x] Keep calm and step back (L2)
- [x] Adapt the main to be able to handle every flags

### Week 39-40

- [x] Start Level 2, do s5 and s6
- [x] Do the "Take a step back" review + justify all your choices
- [x] Do some scheduling

### Week 37-38

- [x] Learn to use Git
- [x] Decide and create the structure of the project on a separate branch
- [x] Read the entire pdf, and understand it.
- [x] Finish first slice s0 with Javadoc associated
- [x] Finish s1, s2, s3, s4 with exception handling and Javadoc associated
- [x] Unit tests