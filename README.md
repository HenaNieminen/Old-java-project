# Contacts-List-project-work-3014

By: Henri Nieminen

Screencast link (Dropbox):

https://www.dropbox.com/scl/fi/iunvgayj3peorrybl03l9/Screencast-Henri-Nieminen.mp4?rlkey=1rseoe4p0hnyjqwj4jv693gcx&dl=0

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
umlauted characters are not accepted, etc.


Fixed after screencast:

-Phone validation

-SSN validation no longer accepts non-existant days or months. Will not take into attention leap years or specific day amounts within a month 

-User can now safely store people with same given names.


Updated self evaluation:

I'd bump up my intial self evaluation from the intial 1-2 to 2-3 after these fixes.