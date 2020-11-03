package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(3);
    private final List<AccidentType> accidentTypes = List.of(AccidentType.of(0, "Two cars"),
            AccidentType.of(1, "Car and human"),
            AccidentType.of(2, "Car and bike"));
    private final Set<Rule> rules = Set.of(Rule.of(1, "Rule1"),
            Rule.of(2, "Rule2"), Rule.of(3, "Rule3"));


    private AccidentMem() {
        Accident accident1 = new Accident("name1", "text1", "address1", accidentTypes.get(1));
        Accident accident2 = new Accident("name2", "text2", "address2", accidentTypes.get(0));
        Accident accident3 = new Accident("name3", "text3", "address3", accidentTypes.get(1));
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
        accident.setType(accidentTypes.get(accident.getType().getId()));
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

    public List<AccidentType> getAccidentTypes() {
        return accidentTypes;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public Set<Rule> getSelectedRules(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            for (Rule rule : rules) {
                if (rule.getId() == Integer.parseInt(ids[i])) {
                    rsl.add(rule);
                }
            }
        }
        return rsl;
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAllAccident() {
        return accidents.values();
    }
}
