package Repository;

import Domain.Entity;

import java.util.List;
import java.util.Map;

public interface IRepository<T extends Entity> {

    void insert(T entity);

    List<T> getAll();

    Map<String, T> getStorage();

}
