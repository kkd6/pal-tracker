package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InMemoryTimeEntryRepository  implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<Long, TimeEntry>();
    long idCtr = 0;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = new TimeEntry(++idCtr,
                timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        timeEntryHashMap.put(idCtr, createdTimeEntry);
        return createdTimeEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else return false;
        //timeEntryHashMap
        //.forEach((k,v) -> v.equals(o));
    }

    @Override
    public int hashCode() {

        return Objects.hash(timeEntryHashMap, idCtr);
    }

    public TimeEntry find(Long id) {

        return timeEntryHashMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntryHashMap.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntryHashMap.replace(id, updatedEntry);
        return updatedEntry;
    }

    public void delete(Long id) {
        timeEntryHashMap.remove(id);
    }


}
