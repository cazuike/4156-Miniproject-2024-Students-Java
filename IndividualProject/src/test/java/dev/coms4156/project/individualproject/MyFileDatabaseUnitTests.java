package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


/**
 * This class contains unit tests for MyFileDatabase.java
 * Unit tests involve setting up an environment for testing and conducting the
 * necessary tests to ensure functionality.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTests {
  /** The test course instance used for testing. */
  public static MyFileDatabase testDatabase;

  @BeforeAll
  public static void setupDatabaseForTesting() {
    testDatabase = new MyFileDatabase(0, "./data.txt");
  }

  @Test
  public void stringNotNullTest() {
    assertNotNull(testDatabase.toString());
  }
}

