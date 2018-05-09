package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            CounterService counter,
            GaugeService gauge
    ) {
        this.timeEntryRepository = timeEntriesRepo;
        this.counter = counter;
        this.gauge = gauge;
    }

    //public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    //    this.timeEntryRepository = timeEntryRepository;
    //}

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry te = this.timeEntryRepository.create(timeEntryToCreate);

        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        return new ResponseEntity((TimeEntry)te, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry te = this.timeEntryRepository.find(id);
        if (te == null) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity((TimeEntry)te, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity((TimeEntry) te, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = this.timeEntryRepository.list();
        if (timeEntries == null) {
            timeEntries = new ArrayList<TimeEntry>();
        }
        counter.increment("TimeEntry.listed");
        return new ResponseEntity((List<TimeEntry>) timeEntries, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = this.timeEntryRepository.update(id, timeEntry);
        if (updatedTimeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity((TimeEntry)updatedTimeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        this.timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
