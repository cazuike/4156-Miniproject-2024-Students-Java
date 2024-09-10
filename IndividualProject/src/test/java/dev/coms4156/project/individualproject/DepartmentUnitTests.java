package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains unit tests for Department.java.
 *
 * <p> Unit tests involve setting up an environment for testing and conducting the
 * necessary tests to ensure functionality. </p>
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {
  /** The test department instance used for testing. */
  public static Department testDepartment;

  /** Provides a test course mapping used for testing. */
  private static Map<String, Course> getTestCourseMapping() {
    Map<String, Course> courses = new HashMap<>();
    Course coms1004 = new Course("Adam Cannon",
            "JOHN JAY",
            "11:40-12:55",
            400);
    coms1004.setEnrolledStudentCount(249);
    courses.put("1004", coms1004);
    Course coms3134 = new Course("Brian Borowski",
            "LERNER",
            "11:40-12:55",
            250);
    coms3134.setEnrolledStudentCount(242);
    courses.put("3134", coms3134);
    return courses;
  }

  /** Sets up department for unit tests. */
  @BeforeEach
  public void setupDepartmentForTesting() {
    testDepartment = new Department("1337",
            getTestCourseMapping(),
            "Chuck Norris",
            10);
  }

  /** Tests if .toString() method returns non-null expected string */
  @Test
  public void toStringTest() {
    assertNotNull(testDepartment.toString());
    assertEquals("1337 1004: \n"
        + "Instructor: Adam Cannon; Location: JOHN JAY; Time: 11:40-12:55\n"
        + "1337 3134: \n"
        + "Instructor: Brian Borowski; Location: LERNER; Time: 11:40-12:55\n",
        testDepartment.toString());
  }

  /** Tests if getInstructor returns the expected value. */
  @Test
  public void getInstructorTest() {
    String expectedResult = "Chuck Norris";
    assertEquals(expectedResult, testDepartment.getDepartmentChair());
  }

  /** Validates functionality of addCourse. */
  @Test
  public void addCourseTest() {
    Course testCourse = new Course(
            "Griffin Newbold",
            "417 IAB",
            "11:40-12:55",
            250);
    int previousSize = testDepartment.getCourseSelection().size();
    testDepartment.addCourse("6000", testCourse);
    int currentSize = testDepartment.getCourseSelection().size();
    assertNotEquals(previousSize, currentSize);
  }

  /** Validates functionality of add, drop, and count of majors. */
  @Test
  public void majorsTest() {
    assertEquals(10, testDepartment.getNumberOfMajors());
    testDepartment.addPersonToMajor();
    assertEquals(11, testDepartment.getNumberOfMajors());
    testDepartment.dropPersonFromMajor();
    assertEquals(10, testDepartment.getNumberOfMajors());
    for (int i = 15; i > 0; i--) {
      testDepartment.dropPersonFromMajor();
    }
    assertEquals(0, testDepartment.getNumberOfMajors());
  }
}

