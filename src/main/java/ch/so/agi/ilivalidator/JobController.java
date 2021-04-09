package ch.so.agi.ilivalidator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.sql.DataSource;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class JobController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    private JobScheduler jobScheduler;
    
    @Autowired
    private AmazonS3StorageService storageService;

    @Autowired
    private SampleJobService sampleJobService;
    
    @RequestMapping(value = "/version.txt", method = RequestMethod.GET)
    public String version() {
        return "version.txt";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam(name = "allObjectsAccessible", required = false) String allObjectsAccessible,
            @RequestParam(name = "configFile", required = false) String configFile,
            @RequestParam(name = "disableAreaValidation", required = false) String disableAreaValidation,
            @RequestParam(name = "file", required = true) MultipartFile uploadFile) throws IOException {
        
        
        log.info("fubar");
        
        // Key muss man ausserhalb kennen.
        
        storageService.store(uploadFile, "key");
        
        
        return null;
    }

    
    
    @GetMapping("/run-job")
    public String runJob(
            @RequestParam(value = "name", defaultValue = "Hello World") String name) {

        jobScheduler.enqueue(() -> sampleJobService.execute(name));
        return "Job is enqueued.";

    }

    @GetMapping("/schedule-job")
    public String scheduleJob(
            @RequestParam(value = "name", defaultValue = "Hello World") String name,
            @RequestParam(value = "when", defaultValue = "PT3H") String when) {

        jobScheduler.schedule(() ->
                        sampleJobService.execute(name),
                Instant.now().plus(Duration.parse(when))
        );

        return "Job is scheduled.";
    }

}
