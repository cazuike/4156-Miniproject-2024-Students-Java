package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class contains integration tests for IndividualProjectApplication.java.
 *
 * <p> Integration tests involve setting up an environment for testing and conducting the
 * necessary tests to integrated parts work properly.</p>
 */
@SpringBootTest
public class IntegrationTests {
  /** The test app and database instances used for testing. */
  @Autowired
  private IndividualProjectApplication app;
  private MyFileDatabase myFileDatabase;

  /**
  * Sets up the testing environment to work with the individual project app.
  */
  @BeforeEach
  public void setupCourseForTesting() {
    String[] args = {"setup"};
    app.run(args);

    myFileDatabase = new MyFileDatabase(0, "./data.txt");
  }

  /** Validate app initialization, also validate app, database are not null. */
  @Test
  public void initializeNotNullTest() {
    assertNotNull(app);
    assertNotNull(myFileDatabase);
  }
}
