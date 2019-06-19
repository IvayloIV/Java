Lab: Functional Programming
===========================

This document defines the **lab** for [[\"Java Advanced\" course @
Software
University]{.underline}](https://softuni.bg/modules/59/java-advanced).
Please submit your solutions (source code) of all below described
problems in
[[Judge]{.underline}](https://judge.softuni.bg/Contests/1513/Functional-Programming-Lab).

Sort Even Numbers
-----------------

Write a program that reads one line of **Integers** separated by **\",
\"**.

-   Print the **even** numbers

-   **Sort** them in ascending order

-   Print them again.

Use 2 **Lambda Expresions** to do so.

### Examples

+-----------------------------------+--------------------+
| **Input**                         | **Output**         |
+===================================+====================+
| **4, 2, 1, 3, 5, 7, 1, 4, 2, 12** | **4, 2, 4, 2, 12** |
|                                   |                    |
|                                   | **2, 2, 4, 4, 12** |
+-----------------------------------+--------------------+
| 1, 3, 5                           | ***(no output)***  |
+-----------------------------------+--------------------+
| 2, 4, 6                           | 2, 4, 6            |
|                                   |                    |
|                                   | 2, 4, 6            |
+-----------------------------------+--------------------+

### Hints

-   It is up to you what type of data structures you will use to solve
    this problem

-   Try different ways, for solving this problem, for example:

    ![](media/image1.png){width="3.3852799650043743in"
    height="0.5604396325459318in"}

Sum Numbers
-----------

Write a program that reads one line of **Integers** separated by **\",
\"**. Print the **count** of the numbers and their **sum.**

Use a **Function\<String, Integer\>**

### Examples

+-----------------------------------+----------------+
| **Input**                         | **Output**     |
+===================================+================+
| **4, 2, 1, 3, 5, 7, 1, 4, 2, 12** | **Count = 10** |
|                                   |                |
|                                   | **Sum = 41**   |
+-----------------------------------+----------------+
| 2, 4, 6                           | **Count = 3**  |
|                                   |                |
|                                   | **Sum = 12**   |
+-----------------------------------+----------------+

### Hints

-   Use Function\<String, Integer\> for parsing integers after you split
    them to a String array

Count Uppercase Words
---------------------

Write a program that reads one line of **text** from the console. Print
the **count** of words that start with a **Uppercase letter**, after
that print all these **words** in the **same order,** like you found
them in the text.

Use a **Predicate\<String\>**

### Examples

+-----------------------------------+-----------------------------------+
| **Input**                         | **Output**                        |
+===================================+===================================+
| The following example shows how   | 2                                 |
| to use Predicate                  |                                   |
|                                   | **The**                           |
|                                   |                                   |
|                                   | **Predicate**                     |
+-----------------------------------+-----------------------------------+
| Write a program that reads one    | 3                                 |
| line of text from console. Print  |                                   |
| count of words that start with    | Write                             |
| Uppercase, after that print all   |                                   |
| those words in the same order     | Print                             |
| like you find them in text.       |                                   |
|                                   | Uppercase,                        |
+-----------------------------------+-----------------------------------+

### Hints

-   Use a **Predicate\<String\>** like an **if-condition**

Add VAT
-------

Write a program that reads one line of **Double** prices separated by
**\", \"**. Print the prices with added **VAT**s for all of them. Format
them to the **2^nd^** digit after the decimal point. The order of the
prices must remain the same.

Use an **UnaryOperator\<Double\>**

### Examples

+---------------------+----------------------+
| **Input**           | **Output**           |
+=====================+======================+
| **1.38, 2.56, 4.4** | **Prices with VAT:** |
|                     |                      |
|                     | **1.66**             |
|                     |                      |
|                     | **3.07**             |
|                     |                      |
|                     | **5.28**             |
+---------------------+----------------------+
| **1, 3, 5, 7**      | **Prices with VAT:** |
|                     |                      |
|                     | **1.20**             |
|                     |                      |
|                     | **3.60**             |
|                     |                      |
|                     | **6.00**             |
|                     |                      |
|                     | **8.40**             |
+---------------------+----------------------+

### Hints

![](media/image2.png){width="4.198856080489938in"
height="0.7291152668416448in"}

Filter by Age
-------------

Write a program that reads an integer **N** on the first line. And on
next **N** lines read pairs of **\"\[name\], \[age\]\".** Then read
three more lines with:

-   Condition - \"**younger**\" or \"**older**\"

-   Age - **Integer**

-   Format - \"**name**\", \"**age**\" or \"**name age**\"

Depending on the **condition,** print the **pairs** in the right
**format**.

**Don't use any build-in functionality. Write your own methods.**

### Examples

+------------+------------+--+------------+------------+--+------------+------------+
| **Input**  | **Output** |  | **Input**  | **Output** |  | **Input**  | **Output** |
+============+============+==+============+============+==+============+============+
| 5          | Pesho - 20 |  | 5          | Pesho      |  | 5          | 20         |
|            |            |  |            |            |  |            |            |
| Pesho, 20\ | Mimi - 29  |  | Pesho, 20\ | Gosho      |  | Pesho, 20\ | 18         |
| Gosho, 18\ |            |  | Gosho, 18\ |            |  | Gosho, 18\ |            |
| Mimi, 29\  | Ico - 31   |  | Mimi, 29\  | Simo       |  | Mimi, 29\  | 29         |
| Ico, 31\   |            |  | Ico, 31\   |            |  | Ico, 31\   |            |
| Simo, 16   |            |  | Simo, 16   |            |  | Simo, 16   | 31         |
|            |            |  |            |            |  |            |            |
| older      |            |  | younger    |            |  | younger    | 16         |
|            |            |  |            |            |  |            |            |
| 20         |            |  | 20         |            |  | 50         |            |
|            |            |  |            |            |  |            |            |
| name age   |            |  | name       |            |  | age        |            |
+------------+------------+--+------------+------------+--+------------+------------+

Find Evens or Odds
------------------

You are given a **lower** and an **upper** **bound** for a range of
integer numbers. Then a command specifies if you need to list all
**even** **or** **odd** numbers in the given range. Use **predicates**
that need to be **passed to a method.**

### Examples

+-----------+-------------------+
| **Input** | **Output**        |
+===========+===================+
| 1 10      | 1 3 5 7 9         |
|           |                   |
| odd       |                   |
+-----------+-------------------+
| 20 30     | 20 22 24 26 28 30 |
|           |                   |
| even      |                   |
+-----------+-------------------+
