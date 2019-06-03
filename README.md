# File operations

## File system

int 32 bit = 4 bytes
1 byte = 8 bits

Types of files:

- text - human readable
- binary - requires less space on disk

File system - organizes data on disk into a hierarchical directory structure with files.

Path - clearly indicates the place of file/directory in the structure

Types of paths:

- absolute - starting from the root directory
- relative - starting from the current working directory

Access modes:

- read
- write - deletes existing content
- append - doesn't delete existing content, apped data at the end

## Java files packages

- *File* - basic operations on a file
- *FileRader/FileWriter* - reading/writing a text file
- *BufferedReader/BufferedWriter* - reading/writing a text line line by line
- *FileInputStream/FileOutputStream* - reading/writing binary file byte by byte
- *DataInputStream/DataOutputStream* - binary reading/writing of primitive types (f.ex. readInt()/writeInt())

