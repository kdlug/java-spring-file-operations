package com.github.kdlug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // java.io.File - basic operations on file
        // java.io.FileReader - text access to file char by char, takes File as a parameter
        // java.io.BufferedReader - text access line by line, takes FileReader as a parameter

        //new BufferedReader(new FileReader(new File("../resources/file.txt")));

        // shortcut
        // new BufferedReader(new FileReader("../resources/file.txt"));


        String textFilePath = "src/main/resources/text-file.txt"; // resources/file.txt
        String binaryFilePath = "src/main/resources/binary-file.txt"; // resources/file.txt

        int number;

        /**
         * Writing text to file
         */
        number = 1234567;
        FileWriter fileWriter = null; // class responsible for writing to text file

        try {
            fileWriter = new FileWriter(textFilePath); // opens file to write
            fileWriter.write(Integer.toString(number)); // write text to the buffer
        } finally {
            if (fileWriter != null) { // checks if our writer was initialized
                // closes accesss to a file - important (OS have limits to allow to open f.ex. 2048 files at the same time)
                // data are stored in buffer, so if we don't close a file wthey can be lost
                fileWriter.close();
            }
        }

        /**
         * Reading text from file
         */

        number = 0;
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader(textFilePath)); // opens file to read
            String numberAsString = fileReader.readLine(); // read line
            number = Integer.parseInt(numberAsString); // parse string and save as int
            System.out.println(numberAsString);
        } finally {
            if (fileReader != null) { // if there is no more data in file, readLine() method returns null
                fileReader.close();
            }
        }

        /**
         * Saving binary date
         */
        number = 1234567;
        DataOutputStream outputStream = null;

        try {
            // DataOutputStream provides an interface which allows to binary writing of primitive types
            // FileOutputStream allows to writing data bite by byte
            outputStream = new DataOutputStream(new FileOutputStream(binaryFilePath));
            outputStream.writeInt(number); // writeInt write primitive type as binary
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        /**
         * Reading binary date
         */
        number = 0;
        DataInputStream inputStream = null;

        try {
            // DataInputStream allows to read binary files
            inputStream = new DataInputStream(new FileInputStream(binaryFilePath));
            number = inputStream.readInt();
            System.out.println(Integer.toString(number));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        /**
         * try-with-resources
         */
        // in try() block we can initialize variables which can be automatically closed
        try(BufferedReader bufferedFileReader = new BufferedReader(new FileReader(textFilePath))) {
            String line = bufferedFileReader.readLine();
            System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

