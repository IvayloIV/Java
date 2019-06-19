Lab: Defining Classes
=====================

This document defines the **lab** for [\"Java Advanced\" course @
Software University](https://softuni.bg/modules/59/java-advanced).
Please submit your solutions (source code) of all below described
problems in
[Judge](https://judge.softuni.bg/Contests/1517/Defining-Classes-Lab).

Part I: Defining Classes
========================

Car Info
--------

Create a class named **Car**.

The class should have **public** fields for:

-   Make: **String**

-   Model: **String**

-   Horse Power: **int**

Create a **new class** and ensure **proper naming**

![](media/image1.png){width="3.6645833333333333in"
height="1.3958333333333333in"}

Define the fields

![](media/image2.png){width="3.6443471128608924in"
height="1.4097222222222223in"}

Create a Test client in the **same** **package**

![](media/image3.png){width="3.6458333333333335in" height="1.40625in"}

You should be now able to use your class

![](media/image4.png){width="5.291666666666667in"
height="3.329488188976378in"}

### Private Fields

Change the access modifiers of all class fields to **private.**

When done go back to the main method you should have **compilation**
errors, like this:

![](media/image5.png){width="5.318627515310586in"
height="3.2083333333333335in"}

#### Getters and Setters

**Create getters** and **setters** for each class field.

**Getter** for the car make:

![](media/image6.png){width="3.763888888888889in"
height="1.0014785651793525in"}

**Setter** for the car make:

![](media/image7.png){width="3.8778762029746283in"
height="0.7152777777777778in"}

Do the same for **all** the fields.

#### Fix main method

You should **set** and **get** the **values** by using the correct
methods

![](media/image8.png){width="5.701388888888889in"
height="2.9312839020122485in"}

### Create Car Info Method

This method should return the info about any car object in the following
format:

**\"The car is: {made} {model} -- {horsePower} HP.\"**

You have to figure out how to create a method and to use it in the
outside code as shown below

![](media/image9.png){width="4.267483595800525in"
height="0.5694444444444444in"}

### Test The Program 

Read a cars objects, add them to collection of your chose and the print
each one on the console using the carInfo() method. The input consists
of single integer **N** the number of lines representing car objects.
One each line you will read car info in the following format **{make}
{model} {horsePower}** separated by single space.

#### Examples

+--------------------------+--------------------------------------------+
| **Input**                | **Output**                                 |
+==========================+============================================+
| **3**                    | **The car is: Chevrolet Impala - 390 HP.** |
|                          |                                            |
| **Chevrolet Impala 390** | **The car is: Luskava Jigula - 500 HP.**   |
|                          |                                            |
| **Luskava Jigula 500**   | **The car is: Golqma Kola - 49 HP.**       |
|                          |                                            |
| **Golqma Kola 49**       |                                            |
+--------------------------+--------------------------------------------+
| **5**                    | **The car is: This Car - 1 HP.**           |
|                          |                                            |
| **This Car 1**           | **The car is: Was Made - 2 HP.**           |
|                          |                                            |
| **Was Made 2**           | **The car is: Only For - 3 HP.**           |
|                          |                                            |
| **Only For 3**           | **The car is: Test Purposes - 4 HP.**      |
|                          |                                            |
| **Test Purposes 4**      | **The car is: No Way - 5 HP.**             |
|                          |                                            |
| **No Way 5**             |                                            |
+--------------------------+--------------------------------------------+

Part II: Constructors
=====================

Car Constructors
----------------

Make proper constructors for the Car class so you can create car objects
with different type of input information.

If you miss information about field of **type String** set the value to
**\"unknown\"**, and for **integer** field set **-1.**

First **declare** **constructor** which takes only the car make as
parameter:

![](media/image10.png){width="4.5077110673665794in"
height="1.1235028433945757in"}

Also create **constructor** which **sets** all the **fields**:

![](media/image11.png){width="4.5390551181102365in"
height="0.9552241907261593in"}

Read information about car the same way as the previous task, however
this time use **constructors** to create the objects, not creating
object with the **default** constructor. You should be able to handle
**different** **types** of input, the format will be the same as the
previous task, but this time some of the data may be missing. For an
example you can get only the car **make** -- which means you have to set
the car model to **\"unknown\"** and the Horse Power value to **-1**.
There will be lines with **complete** car data, declare constructor
which sets all the fields.

You have to add the car objects to a **collection** of your chose and
print the data as in the previous task. The input will **always** have
one or three elements on each line.

### Examples

+---------------------+--------------------------------------------+
| **Input**           | **Output**                                 |
+=====================+============================================+
| **2**               | **The car is: Chevrolet unknown - -1 HP.** |
|                     |                                            |
| **Chevrolet**       | **The car is: Golqma Kola - 49 HP.**       |
|                     |                                            |
| **Golqma Kola 49**  |                                            |
+---------------------+--------------------------------------------+
| **4**               | **The car is: Was unknown - -1 HP.**       |
|                     |                                            |
| **Was**             | **The car is: Only For - 3 HP.**           |
|                     |                                            |
| **Only For 3**      | **The car is: Test Purposes - 4 HP.**      |
|                     |                                            |
| **Test Purposes 4** | **The car is: No Way - 5 HP.**             |
|                     |                                            |
| **No Way 5**        |                                            |
+---------------------+--------------------------------------------+

Bank Account
------------

Create class **BankAccount**.

The class should have **private fields** for:

-   Id: **int** (Starts from **1** and **increments** for every **new**
    **account**)

-   Balance: **double**

-   Interest rate: **double** (Shared for all accounts. **Default value:
    0.02**)

The class should also have **public** methods for:

-   **setInterestRate(double interest):** **void (static)**

-   **getInterest(int Years):** **double**

-   **deposit(double amount):** **void**

Create a test client supporting the following commands:

-   **Create**

-   **Deposit {Id} {Amount}**

-   **SetInterest {Interest}**

-   **GetInterest {ID} {Years}**

-   **End**

### Examples

+-----------------------+-----------------------+-----------------------+
| **Input**             | **Output**            | **Comments**          |
+=======================+=======================+=======================+
| **Create**            | **Account ID1         |                       |
|                       | created**             |                       |
| **Deposit 1 20**      |                       |                       |
|                       | **Deposited 20 to     |                       |
| **GetInterest 1 10**  | ID1**                 |                       |
|                       |                       |                       |
| **End**               | **4.00**              |                       |
+-----------------------+-----------------------+-----------------------+
| **Create**            | **Account ID1         | **Sets the global     |
|                       | created**             | interest rate to 1.** |
| **Create**            |                       |                       |
|                       | **Account ID2         | **Prints interest for |
| **Deposit 1 20**      | created**             | bank account with id  |
|                       |                       | 1 for 1 year          |
| **Deposit 3 20**      | **Deposited 20 to     | period.**             |
|                       | ID1**                 |                       |
| **Deposit 2 10**      |                       |                       |
|                       | **Account does not    |                       |
| **SetInterest 1.5**   | exist**               |                       |
|                       |                       |                       |
| **GetInterest 1 1**   | **Deposited 10 to     |                       |
|                       | ID2**                 |                       |
| **GetInterest 2 1**   |                       |                       |
|                       | **30.00**             |                       |
| **GetInterest 3 1**   |                       |                       |
|                       | **15.00**             |                       |
| **End**               |                       |                       |
|                       | **Account does not    |                       |
|                       | exist**               |                       |
+-----------------------+-----------------------+-----------------------+

### Solution

Create the class as usual and create a **constant** for the default
interest rate

![](media/image12.png){width="6.208333333333333in"
height="0.8333333333333334in"}

Create the static and non-static fields, **all private**

![](media/image13.png){width="7.166666666666667in" height="1.9375in"}

Set the id of an account upon creation while **incrementing** the global
account count

![](media/image14.png){width="5.638888888888889in"
height="0.8561067366579178in"}

Create a setter for the global interest rate. Making the method
**static** will let you access it through the class name

![](media/image15.png){width="5.635416666666667in"
height="0.8958333333333334in"}

Implement **deposit()** and **getInterest()**

![](media/image16.png){width="5.694444444444445in"
height="1.2757031933508312in"}
