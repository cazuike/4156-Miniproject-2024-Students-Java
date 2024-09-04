package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains unit tests for Course.java.
 *
 * <p> Unit tests involve setting up an environment for testing and conducting the
 * necessary tests to ensure functionality. </p>
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {
  /** The test course instance used for testing. */
  public static Course testCourse;

  /** Sets up a test course for the various unit tests. */
  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold",
            "417 IAB", "11:40-12:55", 250);
  }

  /** Test the toString() method of Course. */
  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  /** Test the getter methods of Course. */
  @Test
  public void getterTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());

    expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());

    expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  /** Test the setter methods of Course. */
  @Test
  public void setterTest() {
    String expectedResult = "451 CSB";
    testCourse.reassignLocation(expectedResult);
    assertEquals(expectedResult, testCourse.getCourseLocation());

    expectedResult = "1:10-2:25";
    testCourse.reassignTime(expectedResult);
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());

    expectedResult = "Gail Kaiser";
    testCourse.reassignInstructor(expectedResult);
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  /** Test the enrollStudents() and dropStudents() methods of Course. */
  @Test
  public void enrollStudentsTest() {
    int studentLimit = 250;

    assertTrue(testCourse.enrollStudent());
    assertFalse(testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(studentLimit);
    assertTrue(testCourse.isCourseFull());
    assertFalse(testCourse.enrollStudent());

    for (int i = studentLimit; i > 0; i--) {
      assertTrue(testCourse.dropStudent());
    }

    assertFalse(testCourse.dropStudent());
  }

}

