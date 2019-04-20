package Repository;

import Domain.Entity;

import java.util.List;
import java.util.Map;

public interface IRepository<T extends Entity> {

    /**
     * Gets an entity based on its ID.
     * @param id is the ID of the entity to find.
     * @return the entity with the given ID.
     */

    T findById(String id);

    /**
     * Adds an entity.
     * @param entity is the entity to be added based on its ID.
     */

    void insert(T entity);

    /**
     * Gets a list of all entities.
     * @return the list with all the entities.
     */

    List<T> getAll();

    Map<String, T> getStorage();

}
