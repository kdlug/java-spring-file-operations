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

