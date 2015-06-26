package demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	final static Logger logger = LoggerFactory.getLogger(TestScheduler.class);

	@Scheduled(fixedDelay = 5000)
	public void testScheduler() {
		// logger.info("test scheduler");
	}
}
