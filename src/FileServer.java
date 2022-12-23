import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileServer {
    private int maxSizeStorage;
    //TODO Fix ! size == 0;
    private Set<String> setValidName;
    private Set<String> setUserStorage = new HashSet<>(maxSizeStorage);
    private int freeSpaseOnUserStorage = maxSizeStorage;

    public FileServer(int maxSizeStorage) throws Exception {
        if (maxSizeStorage < 1) {
            throw new Exception("Incorrect size");
        }
        this.maxSizeStorage = maxSizeStorage;
        setValidName = setDictionary(maxSizeStorage);
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] userInput = scanner.nextLine().split("\\s");
            String command = userInput[0];
            switch (command) {
                case "add" -> add(userInput[1]);
                case "get" -> get(userInput[1]);
                case "delete" -> deleted(userInput[1]);
                case "exit" -> System.exit(0);
                default -> System.out.println("Unknown command");
            }
        }
    }

    private boolean add(String fileName) {
        boolean isValidFileName = setValidName.contains(fileName);

        if (isValidFileName && freeSpaseOnUserStorage > 0) {
            System.out.printf("The file %s added successfully%n\n", fileName);
            return setUserStorage.add(fileName);
        } else {
            System.out.printf("Cannot add the file %s\n", fileName);
            return false;
        }
    }

    private boolean get(String fileName) {
        boolean isFileSuccess = setUserStorage.contains(fileName);
        if (isFileSuccess) {
            System.out.printf("The file %s was sent\n", fileName);
            return true;
        } else {
            System.out.printf("The file %s not found\n", fileName);
            return false;
        }
    }

    private boolean deleted(String fileName) {
        boolean isFileSuccess = setUserStorage.contains(fileName);
        if (isFileSuccess) {
            System.out.printf("The file %s was deleted\n", fileName);
            return setUserStorage.remove(fileName);
        } else {
            System.out.printf("The file %s not found\n", fileName);
            return false;
        }
    }

    private Set<String> setDictionary(int sizeStorage) {
        return IntStream.rangeClosed(1, sizeStorage)
                .mapToObj(element -> "file" + element)
                .collect(Collectors.toSet());
    }
}
