package com.omiomi.exercises.neo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.omiomi.exercises.neo.config.AppConfig;

/**
 * Defines an abstract test class
 * @author omi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public abstract class AbstractTest {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
