# File operations

## File system

int 32 bit = 4 bytes
1 byte = 8 bits

Types of files:

- text - human readable
- binary - requires less space on disk

*File system* - organizes data on disk into a hierarchical directory structure with files.

*Path* - clearly indicates the place of file/directory in the structure

Types of paths:

- absolute - starting from the root directory
- relative - starting from the current working directory

Access modes:

- read
- write - deletes existing content
- append - doesn't delete existing content, apped data at the end

## Streams

Java performs I/O through Streams. In general, a stream means continuous flow of data. 

Java defines two types of Stream:

- Byte Stream - for handling input and output of byte
- Character Stream - for handling input and output of characters (Unicode)

Streams can be combined into a chains to achieve more advanced input / output operations.

In java there two types of Stream: 
- Chain Stream - high level stream, depends on connection stream or another chained stream,
- Connection Stream - low level stream, represents a connection to the external resource like files, network etc.

F.ex. FileReader (a connection Stream), has method read() for reading chars one by one. If we don't want to read chars but whole lines, we need a higher level chain stream - BufferedReader.

### Byte streams

Byte stream is defined by using two abstract classes:

- Input Stream - Abstract class that describe byte stream input
- Output Stream - Abstract class that describe byte stream output

These two abstract classes have several concrete classes that handle various devices:

- *BufferedInputStream / BufferedOutputStream* - for Buffered Input/Output Stream
- *DataInputStream / DataOutputStream* - for reading / writing Java standard datatype
- *FileInputStream / FileOutputStream* - for reading/writing from/to a file
- *PrintStream*	- output Stream that contain print() and println() method

These classes define several key methods. Two most important are

- *read()* - reads byte of data.
- *write()* - writes byte of data.

### Character streams

Character stream is defined by using two abstract classes

- *Reader* - Abstract class that define character stream input
- *Writer* - Abstract class that define character stream output

Concrete clases:

- *BufferedReader / BufferedWriter* - handles buffered input/output stream
- *FileReader / FileWriter* - input/output stream that reads/writes from/to a file
- *InputStreamReader / OutputStreamReader* - input/output stream that translate byte to character
- *PrintWriter* - output Stream that contain print() and println() method

## Java files packages

- *File* - basic operations on a file
- *FileRader / FileWriter* -  reading/writing from/to a text file char by char
- *BufferedReader / BufferedWriter* - buffered reading/writing a text from a file line line by line
- *FileInputStream / FileOutputStream* - reading/writing binary from/to a file byte by byte
- *DataInputStream / DataOutputStream* - binary reading/writing of primitive types (f.ex. readInt()/writeInt())

### java.io.File

Represents a file stored on a hard drive without it's content. It doesn't contain methods for reading/writing data.
Object File represents name and path to the file but doesn't guarantee an access to it's content.

Example operations using File object

```java
// Creating File object which represents an existing file
File f = new File("file.txt");

// Creating a new directory
File d = new File("directory");
d.mkdir();

// Listing content of a directory
if (d.isDirectory()) {
    String[] ls = d.list();

    for (int i = 0; i < ls.length; i++) {
        System.out.println(ls[i]);
    }   
}

// Geting abolute path to a file or a directory
String p = d.getAbsolutePath();

// Removing a file or a directory
boolean isDeleted = f.delete();
```

### java.io.FileWriter

We can pass to a FileWriter both String or a File object.

```java
    // string
    FileWriter writer = new FileWriter("file.txt");

    // file (in this case we have to close both)
    File f = new File("file.txt");
    FileWriter writer = new FileWriter(f);

    // file (in this case we have to close only writer, file wil be closed automatically)
    FileWriter writer = new FileWriter(new File("file.txt"));

```

```java
try {
    // creating an output stream which writes data to a text file
    // if file doesn't exist, stream will create it
    FileWriter writer = new FileWriter("file.txt");
    writer.write("Some text content"); // writes to a file char by char
    writer.close(); // closes stream
} catch (IOException e) {
    e.pringStackTrace();
}
```

### java.io.BufferedWriter

Buffers are a place (in memory) where data can be temporary stored until a buffer reaches it's capacity limit.
It's used for improving performance of input/output operations (it reduces number of read/write operations).


## try-with-resources


Each time we have to remember to close resources.

```java
BufferedReader fileReader = null;

try {
     fileReader = new BufferedReader(new FileReader(inputPath));
     fileReader.readLine();
} catch (FileNotFoundException e) {
     e.printStackTrace();
} catch (IOException e) {
     e.printStackTrace();
} finally {
    if (fileReader != null) {
         try {
             fileReader.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
```

In try() block we can initialize variables which can be automatically closed

```java
try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
    fileReader.readLine();
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

We can also initialize more variables. Eeach variable will be properly closed.

```java
try(
    BufferedReader fileReader = new BufferedReader(new FileReader(inputPath));
    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputPath))
) {
    String line = fileReader.readLine();
    fileWriter.write(line);
}
```

It works because BufferedWriter and BufferedReader impement java.lang.AutoCloseable interface.


## Custom context manager

```java
public class MyContextManager implements AutoCloseable {
    public MyContextManager() {
        System.out.println("starting");
    }

    public void doSomething() {
        System.out.println("doing something");
    }

    public void close() {
        System.out.println("closing");
    }
}

public class MyContextManagerMain {
    public static void main(String[] args) {
        try (MyContextManager manager = new MyContextManager()) {
            manager.doSomething();
        }
    }
}
```

