# Contacts-List-project-work-3014

By: Henri Nieminen

This is my Contact management app programmed in java. It can store various information from a given subject. Including:

-ID (Finnish SSN)
-Given name
-Surname
-Phone number (+358 format only)
-Physical address (Optional, can be left as empty)
-Email address    (Optional)

The user interface is comprised solely of CLI and is navigated using prompts and numerical keys. You can view, add, delete
edit saved contacts. When a contact is added, it will generate a file into the source folder of the compiled program.

How to compile and run:
cd src/ && javac *.java && java ContactsApp

Known issues and bugs:

-The file generated from the program is very prone to corruption and IOexceptions. It is advised to not touch the file or edit it outside the program
-The program cannot handle duplicate given names very well. It uses this in the searching function and will take inputs for all instances
-There is no escape key for mistyped input selections. This can be very annoying at times
-The validation of the contact information is finicky at best. The SSN can accept non-sensical time frames eg. 39th of Duodecimber,
the phone number can be assigned to non-existant operators,
