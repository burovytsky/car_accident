package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private static final AccidentMem INST = new AccidentMem();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);


    private AccidentMem() {
        Accident accident1 = new Accident(1, "name1", "text1", "address1");
        Accident accident2 = new Accident(2, "name2", "text2", "address2");
        Accident accident3 = new Accident(3, "name3", "text3", "address3");
        accidents.put(1, accident1);
        accidents.put(2, accident2);
        accidents.put(3, accident3);
    }

    public static AccidentMem instOf() {
        return INST;
    }

    public void add(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public boolean remove(int id) {
        return accidents.remove(id) != null;
    }

    public Collection<Accident> getAllAccident() {
        return accidents.values();
    }
}
