package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(3);


    private AccidentMem() {
        Accident accident1 = new Accident( "name1", "text1", "address1");
        Accident accident2 = new Accident( "name2", "text2", "address2");
        Accident accident3 = new Accident( "name3", "text3", "address3");
        accident1.setId(1);
        accident2.setId(2);
        accident3.setId(3);
        accidents.put(1, accident1);
        accidents.put(2, accident2);
        accidents.put(3, accident3);
    }

    public void add(Accident accident) {
        int id = ACCIDENT_ID.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public void edit(Accident accident) {
        if (accidents.containsKey(accident.getId())) {
            accidents.replace(accident.getId(), accident);
        }
    }

    public boolean remove(int id) {
        return accidents.remove(id) != null;
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAllAccident() {
        return accidents.values();
    }
}
