package com.moneydonationpool.schedular;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moneydonationpool.dao.CauseDao;
import com.moneydonationpool.entity.CauseEntity;

@Component
public class Scheduler {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	
	@Autowired
	CauseDao causeDao;
	
   @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight - 12am
   //@Scheduled(cron = "0 */1 * ? * *") // every minute - test purpose.
   public void cronJobSch() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date now = new Date();
      String todayDate = sdf.format(now);
     LOGGER.info("Java cron job executing at:: " + todayDate);
      
      List<CauseEntity> getTodaysCauses = causeDao.getTodaysCaused();
      LOGGER.info("getTodaysCauses" + getTodaysCauses.size());
      for (CauseEntity causeDetails : getTodaysCauses)
      {
    	  causeDetails.setIsActive(false);
    	  LOGGER.info("Cause : "+causeDetails);
    	  causeDao.updateCause(causeDetails);
      }
      LOGGER.info("Java cron job execution done at :: " + todayDate);
      
   }
}
