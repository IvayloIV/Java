Lab: Sets and Maps Advanced
===========================

This document defines the **lab** for [[\"Java Advanced\" course @
Software
University]{.underline}](https://softuni.bg/modules/59/java-advanced).
Please submit your solutions (source code) of all below described
problems in
[[Judge]{.underline}](https://judge.softuni.bg/Contests/1462/Sets-And-Maps-Advanced-Lab).

Sets
----

### Parking Lot

Write program that:

-   Record **car number** for every car that enter in **parking lot**

-   Remove **car number** when the car go out

-   Input will be string in format **\[direction, carNumber\]**

-   input end with string **\"END\"**

Print the output with all car numbers which are in parking lot

#### Examples

+-------------------+--------------------------+
| **Input**         | **Output**               |
+===================+==========================+
| **IN, CA2844AA**  | **CA2822UU**             |
|                   |                          |
| **IN, CA1234TA**  | **CA2844AA**             |
|                   |                          |
| **OUT, CA2844AA** | **CA9999TT**             |
|                   |                          |
| **IN, CA9999TT**  | **CA9876HH**             |
|                   |                          |
| **IN, CA2866HI**  |                          |
|                   |                          |
| **OUT, CA1234TA** |                          |
|                   |                          |
| **IN, CA2844AA**  |                          |
|                   |                          |
| **OUT, CA2866HI** |                          |
|                   |                          |
| **IN, CA9876HH**  |                          |
|                   |                          |
| **IN, CA2822UU**  |                          |
|                   |                          |
| END               |                          |
+-------------------+--------------------------+
| **IN, CA2844AA**  | **Parking Lot is Empty** |
|                   |                          |
| **IN, CA1234TA**  |                          |
|                   |                          |
| **OUT, CA2844AA** |                          |
|                   |                          |
| **OUT, CA1234TA** |                          |
|                   |                          |
| **END**           |                          |
+-------------------+--------------------------+

#### Hints

-   Car numbers are **unique**

-   Use the methods **isEmpty()**

### SoftUni Party

There is a party in SoftUni. Many guests are invited and they are two
types: **VIP** and **regular**. When guest come check if he/she
**exist** in any of two reservation lists.

All reservation numbers will be with **8 chars.**

All **VIP** numbers start with **digit.**

There will be 2 command lines. First is **\"PARTY\"** - party is on and
guests start coming.\
Second is **\"END\"** -- then party is over and no more guest will come.

Output shows all guests, who didn\'t come to the party (**VIP** must be
first).

#### Examples

+--------------+--------------+--------------+--------------+
| **Input**    | **Output**   | **Input**    | **Output**   |
+==============+==============+==============+==============+
| **7IK9Yo0h** | **2**        | **m8rfQBvl** | **2**        |
|              |              |              |              |
| **9NoBUajQ** | **7IK9Yo0h** | **fc1oZCE0** | **MDzcM9ZK** |
|              |              |              |              |
| **Ce8vwPmE** | **tSzE5t0p** | **UgffRkOn** | **xys2FYzn** |
|              |              |              |              |
| **SVQXQCbc** |              | **7ugX7bm0** |              |
|              |              |              |              |
| **tSzE5t0p** |              | **9CQBGUeJ** |              |
|              |              |              |              |
| **PARTY**    |              | **2FQZT3uC** |              |
|              |              |              |              |
| **9NoBUajQ** |              | **dziNz78I** |              |
|              |              |              |              |
| **Ce8vwPmE** |              | **mdSGyQCJ** |              |
|              |              |              |              |
| **SVQXQCbc** |              | **LjcVpmDL** |              |
|              |              |              |              |
| **END**      |              | **fPXNHpm1** |              |
|              |              |              |              |
|              |              | **HTTbwRmM** |              |
|              |              |              |              |
|              |              | **B5yTkMQi** |              |
|              |              |              |              |
|              |              | **8N0FThqG** |              |
|              |              |              |              |
|              |              | **xys2FYzn** |              |
|              |              |              |              |
|              |              | **MDzcM9ZK** |              |
|              |              |              |              |
|              |              | **PARTY**    |              |
|              |              |              |              |
|              |              | **2FQZT3uC** |              |
|              |              |              |              |
|              |              | **dziNz78I** |              |
|              |              |              |              |
|              |              | **mdSGyQCJ** |              |
|              |              |              |              |
|              |              | **LjcVpmDL** |              |
|              |              |              |              |
|              |              | **fPXNHpm1** |              |
|              |              |              |              |
|              |              | **HTTbwRmM** |              |
|              |              |              |              |
|              |              | **B5yTkMQi** |              |
|              |              |              |              |
|              |              | **8N0FThqG** |              |
|              |              |              |              |
|              |              | **m8rfQBvl** |              |
|              |              |              |              |
|              |              | **fc1oZCE0** |              |
|              |              |              |              |
|              |              | **UgffRkOn** |              |
|              |              |              |              |
|              |              | **7ugX7bm0** |              |
|              |              |              |              |
|              |              | **9CQBGUeJ** |              |
|              |              |              |              |
|              |              | **END**      |              |
+--------------+--------------+--------------+--------------+

### \"Voina\" - Number game

Write program that:

-   Read 20 numbers for both players

-   Numbers will be **Integer**, separated with **\" \"** **(single
    space).**

-   Every player can hold only **unique** numbers

-   Each Round both players get the **top number** from their own deck.
    Player with the bigger number get both numbers and add it on the
    **bottom** of his own sequence

-   Game ends after **50 rounds** or if any player **lose all** of his
    numbers

-   Output must be **\"First Player Win!\"**, **\"Second Player Win!\"**
    or **\"Draw!\"**

#### Examples

+-----------------------------------+-----------------------------------+
| **Input**                         | **Output**                        |
+===================================+===================================+
| 26 58 16 92 44 65 65 77 57 23 71  | **Second player win!**            |
| 57 7 52 85 44 32 70 38 23         |                                   |
|                                   |                                   |
| 43 95 33 51 62 93 57 55 0 31 32   |                                   |
| 95 68 34 30 51 37 32 11 97        |                                   |
+-----------------------------------+-----------------------------------+
| 74 78 82 42 19 39 29 69 20 42 31  | **First player win!**             |
| 77 57 36 76 26 4 9 83 42          |                                   |
|                                   |                                   |
| 15 43 80 71 22 88 78 35 28 30 46  |                                   |
| 41 76 51 76 18 14 52 47 38        |                                   |
+-----------------------------------+-----------------------------------+

#### Hints

-   Use **Iterator\<E\>** and **next()** for finding top number in decks

-   Think where to check if any player is **without** cards

-   When you find top number, be sure to **remove** it **immediately**

#### Solution

You might help yourself with the code below:

![](media/image1.png){width="5.747750437445319in"
height="2.6660509623797024in"}

Maps
----

### Count Real Numbers

Write a program that counts the occurrence of real **numbers**. The
input is a single line with real numbers separated by spaces. Print the
numbers in the order of appearance. All **numbers** must be formatted to
one digit after the decimal point.

#### Examples

+-----------------------------------+-----------------------------------+
| **Input**                         | **Output**                        |
+===================================+===================================+
| -2.5 4 3 -2.5 -5.5 4 3 3 -2.5 3   | **-2.5 -\> 3**                    |
|                                   |                                   |
|                                   | **4.0 -\> 2**                     |
|                                   |                                   |
|                                   | **3.0 -\> 4**                     |
|                                   |                                   |
|                                   | **-5.5 -\> 1**                    |
+-----------------------------------+-----------------------------------+
| 2.3 4.5 4.5 5.5 5.5 2.3 3.0 3.0   | 2.3 -\> 3                         |
| 4.5 4.5 3.0 3.0 4.0 3.0 5.5 3.0   |                                   |
| 2.3 5.5 4.5 3.0                   | 4.5 -\> 5                         |
|                                   |                                   |
|                                   | 5.5 -\> 4                         |
|                                   |                                   |
|                                   | 3.0 -\> 7                         |
|                                   |                                   |
|                                   | 4.0 -\> 1                         |
+-----------------------------------+-----------------------------------+

![](media/image2.png){width="5.223880139982502in"
height="2.2909416010498687in"}

### Average Students Grades

Write a program, which reads the **name** of a student and their
**grades** and **adds** them to the **student record**, then **prints**
**grades** along with their **average grade -- ordered the output by the
names of the students**.

#### Input

On the first line **N** -- the number of students, then on the next
**N** lines student name with grade.

#### Examples

+---------------+----------------------------------------+
| **Input**     | **Output**                             |
+===============+========================================+
| 7             | Ivancho -\> 5.20 3.20 (avg: 4.20)      |
|               |                                        |
| Ivancho 5.20  | Mariika -\> 5.50 2.50 3.46 (avg: 3.82) |
|               |                                        |
| Mariika 5.50  | Stamat -\> 2.00 3.00 (avg: 2.50)       |
|               |                                        |
| Ivancho 3.20  |                                        |
|               |                                        |
| Mariika 2.50  |                                        |
|               |                                        |
| Stamat 2.00   |                                        |
|               |                                        |
| Mariika 3.46  |                                        |
|               |                                        |
| Stamat 3.00   |                                        |
+---------------+----------------------------------------+
| 4             | Petko -\> 3.00 3.66 (avg: 3.33)        |
|               |                                        |
| Vladimir 4.50 | Vladimir -\> 4.50 5.00 (avg: 4.75)     |
|               |                                        |
| Petko 3.00    |                                        |
|               |                                        |
| Vladimir 5.00 |                                        |
|               |                                        |
| Petko 3.66    |                                        |
+---------------+----------------------------------------+
| 5             | Gosho -\> 6.00 5.50 6.00 (avg: 5.83)   |
|               |                                        |
| Gosho 6.00    | Ivan -\> 4.40 (avg: 4.40)              |
|               |                                        |
| Gosho 5.50    | Petko -\> 3.30 (avg: 3.30)             |
|               |                                        |
| Gosho 6.00    |                                        |
|               |                                        |
| Ivan 4.40     |                                        |
|               |                                        |
| Petko 3.30    |                                        |
+---------------+----------------------------------------+

#### Hints

-   Use a **TreeMap** (**String** **ArrayList\<Double\>**)

-   Check if the name **exists** before adding the grade. If it doesn't,
    add it to the map.

-   Pass through all **key-value pairs** in the map and print the
    results.

-   Think of way to get the average grades for each student.

<!-- -->

-   You can do that with an ordinary loop or with **Stream API**

### Product Shop

Write a program that prints information about food shops in Sofia and
the products they store. Until the \"**Revision**\" command you will
receive an input in the format: **\"{shop}, {product}, {price}\"**

Take in mind that if you receive a shop you already have received, you
must collect its product information.

Your output must be ordered by shop name and must be in the format:

{shop}-\>

Product: {product}, Price: {price}

The price should be formated to one digit after the decimal point.

#### Examples

+--------------------------+--------------------------------+
| **Input**                | **Output**                     |
+==========================+================================+
| lidl, juice, 2.30        | fantastico-\>                  |
|                          |                                |
| fantastico, apple, 1.20  | Product: apple, Price: 1.2     |
|                          |                                |
| kaufland, banana, 1.10   | Product: grape, Price: 2.2     |
|                          |                                |
| fantastico, grape, 2.20  | kaufland-\>                    |
|                          |                                |
| Revision                 | Product: banana, Price: 1.1    |
|                          |                                |
|                          | lidl-\>                        |
|                          |                                |
|                          | Product: juice, Price: 2.3     |
+--------------------------+--------------------------------+
| tmarket, peanuts, 2.20   | GoGrill-\>                     |
|                          |                                |
| GoGrill, meatballs, 3.30 | Product: meatballs, Price: 3.3 |
|                          |                                |
| GoGrill, HotDog, 1.40    | Product: HotDog, Price: 1.4    |
|                          |                                |
| tmarket, sweets, 2.20    | tmarket-\>                     |
|                          |                                |
| Revision                 | Product: peanuts, Price: 2.2   |
|                          |                                |
|                          | Product: sweets, Price: 2.2    |
+--------------------------+--------------------------------+

### Cities by Continent and Country

Write a program to read **continents**, **countries** and their
**cities**, put them in a **nested map** and **print** them in the order
of first appearance.

#### Examples

+--------------------------+---------------------------------+
| **Input**                | **Output**                      |
+==========================+=================================+
| 9                        | Europe:                         |
|                          |                                 |
| Europe Bulgaria Sofia    | Bulgaria -\> Sofia, Plovdiv     |
|                          |                                 |
| Asia China Beijing       | Poland -\> Warsaw, Poznan       |
|                          |                                 |
| Asia Japan Tokyo         | Germany -\> Berlin              |
|                          |                                 |
| Europe Poland Warsaw     | Asia:                           |
|                          |                                 |
| Europe Germany Berlin    | China -\> Beijing, Shanghai     |
|                          |                                 |
| Europe Poland Poznan     | Japan -\> Tokyo                 |
|                          |                                 |
| Europe Bulgaria Plovdiv  | Africa:                         |
|                          |                                 |
| Africa Nigeria Abuja     | Nigeria -\> Abuja               |
|                          |                                 |
| Asia China Shanghai      |                                 |
+--------------------------+---------------------------------+
| 3                        | Europe:                         |
|                          |                                 |
| Europe Germany Berlin    | Germany -\> Berlin              |
|                          |                                 |
| Europe Bulgaria Varna    | Bulgaria -\> Varna              |
|                          |                                 |
| Africa Egypt Cairo       | Africa:                         |
|                          |                                 |
|                          | Egypt -\> Cairo                 |
+--------------------------+---------------------------------+
| 8                        | Africa:                         |
|                          |                                 |
| Africa Somalia Mogadishu | Somalia -\> Mogadishu           |
|                          |                                 |
| Asia India Mumbai        | Asia:                           |
|                          |                                 |
| Asia India Delhi         | India -\> Mumbai, Delhi, Nagpur |
|                          |                                 |
| Europe France Paris      | Europe:                         |
|                          |                                 |
| Asia India Nagpur        | France -\> Paris                |
|                          |                                 |
| Europe Germany Hamburg   | Germany -\> Hamburg, Danzig     |
|                          |                                 |
| Europe Poland Gdansk     | Poland -\> Gdansk               |
|                          |                                 |
| Europe Germany Danzig    |                                 |
+--------------------------+---------------------------------+

#### Hints

-   Use a **nested** **Map** (**String** (**Map ArrayList\<String\>)**)

-   Check if the continent **exists** before adding the country. If it
    doesn't, **add** it to the dictionary.

-   Check if the country **exists**, before **adding** the city. If it
    doesn't, add it to the dictionary.

![](media/image3.png){width="6.770138888888889in"
height="2.9402777777777778in"}

-   Pass through all **key-value pairs** in the Map and the values'
    key-value pairs and print the results.

### Academy Graduation

Write a program that:

-   Read from console **number** of students for a track

-   Read on **pair of rows**:

    -   First line is the **name** of student

    -   Second line is his **score** for different number of courses

-   Print on console "**{name}** is graduated with **{average scores)**"

#### Examples

+-----------------------------------+-----------------------------------+
| **Input**                         | **Output**                        |
+===================================+===================================+
| 3                                 | **Gosho is graduated with 4.375** |
|                                   |                                   |
| Gosho                             | **Mara is graduated with 5.125**  |
|                                   |                                   |
| 3.75 5                            | **Pesho is graduated with 5.25**  |
|                                   |                                   |
| Mara                              |                                   |
|                                   |                                   |
| 4.25 6                            |                                   |
|                                   |                                   |
| Pesho                             |                                   |
|                                   |                                   |
| 6 4.5                             |                                   |
+-----------------------------------+-----------------------------------+
| 5                                 | **Ganio is graduated with         |
|                                   | 4.09375**                         |
| Gruio                             |                                   |
|                                   | **Gruio is graduated with         |
| 4.36 5.50 3.30 5.63 2.57 5.75     | 4.351249999999999**               |
| 2.81 4.89                         |                                   |
|                                   | **Mite is graduated with          |
| Trendafilka                       | 4.11875**                         |
|                                   |                                   |
| 3.10 5.35 3.30 3.35 5.64 4.99     | **Roza is graduated with 3.3375** |
| 2.75 4.68                         |                                   |
|                                   | **Trendafilka is graduated with   |
| Mite                              | 4.145**                           |
|                                   |                                   |
| 3.45 3.23 3.03 5.42 5.46 4.15     |                                   |
| 2.26 5.95                         |                                   |
|                                   |                                   |
| Roza                              |                                   |
|                                   |                                   |
| 2.08 3.48 3.36 2.73 2.96 4.54     |                                   |
| 3.70 3.85                         |                                   |
|                                   |                                   |
| Ganio                             |                                   |
|                                   |                                   |
| 4.75 4.92 3.78 4.79 4.82 4.75     |                                   |
| 2.81 2.13                         |                                   |
+-----------------------------------+-----------------------------------+

#### Hints

-   Think about **proper type** of map

-   **Value** can be **array**

-   **Nested loop** and one more **variable** will be need for average
    score

#### Solution

You might help yourself with the code below:

![](media/image4.png){width="5.0974846894138235in"
height="2.583465660542432in"}
