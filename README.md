# Assignment Name

SOFT2412 Group project Assignment 2 – Scrum Software Development (2021)

## Project Name

Development of a Movie Ticket Booking Management System for Fancy Cinemas

## Group Members

 - Imran Issa
 - Matthew Karko
 - Alexander Marrapese
 - William Qiu
 - Raymond Ton


# Description

Use Git or check-out with SVN using this URL:
[Booking System for Fancy Cinemas](https://github.sydney.edu.au/SOFT2412-2021S2/Assignment__2.git)

The code is written in Java, spread across six files:
 - Account.java
 - Cinema.java
 - GiftCard.java
 - Movie.java
 - PasswordMasker.java
 - Transaction.java

The above are supplied with .csv files 'movies.csv' as the database for movies being shown at the cinema, 'giftcards.csv' as the database for valid gift cards used in payments, and 'accounts.csv' as the database for valid customer, staff and manager accounts. Valid credit cards can be found in 'credit_cards.json' for payment as well.

Account.java contains a class 'Account' used to keep track of each user's account details and use of the system, particularly regarding their transactions, via getter and setter methods.

Cinema.java holds most of the functionality required for use of the cinema's booking system, namely the processes of viewing and booking movies, and system management and control by admin. These are accessed through the application's run method.

GiftCard.java contains a class 'GiftCard' which simply holds a gift card's number and a value indicating whether it has been used in transaction via getter and setter methods.

Movie.java contains a class 'Movie' that holds a movie's details each as an atttribute with getter and setter methods.

PasswordMasker.java contains two classes run in unison via the use of threads to mask a user's input for passwords and card numbers, one which reads the input and one that replaces each character with whitespace.

Lastly, Transaction.java contains the details of each user's transaction when booking a movie ticket, each as an attribute with getter and setter methods.

The testing code of the system is found in the following files:
 - TestAccount.java
 - TestCinema.java
 - TestMovie.java
 - TestTransaction.java

Each of the above test files are supplied with test .csv databases 'accounts_test.csv', 'giftcards_test.csv' and 'movies_test.csv'. All contain unit cases to test each method of their respective src/main/java files and verify correct behaviour.

## Software Development Approach and Tools

Our team employed Scrum practices during the development of this application. The system was developed over 3 sprints, each successfully building on what had been developed previously and further enhancing its functionality. Daily scrums were conducted to ensure consistent contribution and manage the development of each user story. 

The tools used include GitHub, Gradle, Junit, JaCoCo and Jenkins. Each member was entitled to use any IDE they wished, as long as they were able to contribute code. A single shared Git repository was used between us, allowing us to work and commit from branches that were created for each ticket. Gradle was essential for build automation and JUnit for automated testing. The integration of JaCoCo was intended for understanding test code coverage. Jenkins, via webhooks and ngrok, enabled continuous integration with every commit made and test reports following. 

# Running the Application

The following instructions assumes Gradle 6.7 and Java are installed on your PC.

The ATM application can be run by entering the following in a Linux-supported terminal from the directory of the 'build.gradle' file:

```bash
gradle build
```

If there are errors, please raise the issue immediately with the development team.
Otherwise, commence with the following from within the same directory:

```bash
gradle run
```

For a cleaner output, use this command instead:
```bash
gradle --console plain run
```
or in combination:
```bash
gradle clean build --console plain run
```

When the system runs, simply then follow the instructions prompted on the screen.

# Testing the Application

The following instructions assumes Gradle and Java are installed on your PC.

The ATM application can be tested by entering the following in a Linux-supported terminal from the directory of the 'build.gradle' file:

```bash
gradle test
```

If there are errors, please raise the issue immediately with the development team.

# Contribution and Collaboration

Pull requests are welcome. However, pushing any commits must be first discussed with the group members. This is a private project, thus it is required to raise any concerns and/or opportunities with the team regarding the application. Once you have been granted approval for changes, be sure to update test cases appropriately.

Distribution of this project is strictly prohibited.
