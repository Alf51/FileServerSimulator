import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileServer {
    private int maxSizeStorage;
    private Set<String> setValidName = setDictionary(maxSizeStorage);
    private Set<String> setUserStorage = new HashSet<>(10);
    private int freeSpaseOnUserStorage = maxSizeStorage;

    public FileServer(int maxSizeStorage) throws Exception {
        if (maxSizeStorage < 1) {
            throw new Exception("Incorrect size");
        }
        this.maxSizeStorage = maxSizeStorage;
    }

    public void userInterface() {

    }

    public boolean add(String fileName) {
        boolean isValidFileName = setValidName.contains(fileName);

        if (isValidFileName && freeSpaseOnUserStorage > 0) {
            return setUserStorage.add(fileName);
        } else {
            return false;
        }
    }


    private Set<String> setDictionary(int sizeStorage) {
        return IntStream.rangeClosed(1, sizeStorage)
                .mapToObj(element -> "file" + element)
                .collect(Collectors.toSet());
    }
}
