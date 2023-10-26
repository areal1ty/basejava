/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        if (size > 0) {
          for (int i = 0; i < size; i++) {
              storage[i] = null;
          }
          size = 0;
        }
    }


    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Ошибка. Хранилище заполнено!");
        }
    }

    Resume get(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            int res = uuid.compareTo(storage[i].uuid);
            if (res == 0) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        if (size > 0) {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - index);
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    int size() {
        return size;
    }
}
