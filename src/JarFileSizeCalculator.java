import java.io.File;
import java.util.Scanner;

public class JarFileSizeCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the directory path from the user
        System.out.print("Enter the directory path where the JAR files are stored: ");
        String directoryPath = scanner.nextLine();

        File directory = new File(directoryPath);

        // Check if the directory exists and is actually a directory
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

            // If there are no JAR files, notify the user
            if (files == null || files.length == 0) {
                System.out.println("No JAR files found in the specified directory.");
                return;
            }

            long totalSize = 0;
            File largestFile = null;
            File smallestFile = null;
            long largestSize = 0;
            long smallestSize = Long.MAX_VALUE;

            // Iterate through each JAR file to calculate sizes and find largest and smallest
            for (File file : files) {
                long fileSize = file.length();
                totalSize += fileSize;

                // Check if this is the largest file
                if (fileSize > largestSize) {
                    largestFile = file;
                    largestSize = fileSize;
                }

                // Check if this is the smallest file
                if (fileSize < smallestSize) {
                    smallestFile = file;
                    smallestSize = fileSize;
                }
            }

            // Convert bytes to KB (divide by 1024)
            double totalSizeKB = totalSize / 1024.0;
            double largestSizeKB = largestSize / 1024.0;
            double smallestSizeKB = smallestSize / 1024.0;

            // Output the results in both bytes and KB
            System.out.println("\nTotal size of all JAR files: " + totalSize + " bytes (" + String.format("%.2f", totalSizeKB) + " KB)");
            System.out.println("Largest JAR file: " + largestFile.getName() + " (" + largestSize + " bytes, " + String.format("%.2f", largestSizeKB) + " KB)");
            System.out.println("Smallest JAR file: " + smallestFile.getName() + " (" + smallestSize + " bytes, " + String.format("%.2f", smallestSizeKB) + " KB)");

        } else {
            System.out.println("The specified path is not a valid directory.");
        }

        scanner.close();
    }
}
