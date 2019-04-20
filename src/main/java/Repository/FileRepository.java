package Repository;

import Domain.Entity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRepository<T extends Entity> implements IRepository<T> {

    private String filename;
    private Type type;
    private Map<String, T> storage = new HashMap<>();

    /**
     * Constructs a file repository.
     * @param filename is the filename used to store objects.
     * @param type is the type of the stored objects (T).
     */

    public FileRepository(String filename, Type type) {
        this.filename = filename;
        this.type = type;
    }

    public void loadFromFile() {
        storage.clear();
        Gson gson = new Gson();
        try (FileReader in = new FileReader(filename)) {
            try (BufferedReader bufferedReader = new BufferedReader(in)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    T entity = gson.fromJson(line, type);
                    storage.put(entity.getId(), entity);
                }
            }
        } catch (Exception exception) {
            System.out.println("Error loading from file: " + exception.getMessage());
        }
    }

    public void writeToFile() {
        Gson gson = new Gson();
        try (FileWriter out = new FileWriter(filename)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {
                for (T entity : storage.values()) {
                    bufferedWriter.write(gson.toJson(entity));
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception exception) {
            System.out.println("Error writing to file: " + exception.getMessage());
        }
    }

    @Override
    public void insert(T entity) {
        loadFromFile();
        if (storage.containsKey(entity.getId()))
            throw new RepositoryException(String.format("An entity with the ID %s already exists!", entity.getId()));
        storage.put(entity.getId(), entity);
        writeToFile();
    }

    @Override
    public List<T> getAll() {
        loadFromFile();
        return new ArrayList<>(storage.values());
    }

    @Override
    public Map<String, T> getStorage() {
        loadFromFile();
        return storage;
    }

}
