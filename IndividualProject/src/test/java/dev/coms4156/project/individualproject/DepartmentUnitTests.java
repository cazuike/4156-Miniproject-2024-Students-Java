package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;




/**
 * This class contains unit tests for Department.java
 * Unit tests involve setting up an environment for testing and conducting the
 * necessary tests to ensure functionality.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {
  /** The test course instance used for testing. */
  public static Department testDepartment;

  private static HashMap<String, Course> getTestCourseMapping() {
    HashMap<String, Course> courses = new HashMap<>();
    Course coms1004 = new Course("Adam Cannon", "JOHN JAY", "11:40-12:55", 400);
    coms1004.setEnrolledStudentCount(249);
    courses.put("1004", coms1004);
    Course coms3134 = new Course("Brian Borowski", "LERNER", "11:40-12:55", 250);
    coms3134.setEnrolledStudentCount(242);
    courses.put("3134", coms3134);
    Course coms3157 = new Course("Jae Lee", "451 CSB", "11:40-12:55", 400);
    coms3157.setEnrolledStudentCount(311);
    courses.put("3157", coms3157);
    Course coms3203 = new Course("Ansaf Salleb-Aouissi", "501 NWC", "11:40-12:55", 250);
    coms3203.setEnrolledStudentCount(215);
    courses.put("3203", coms3203);
    Course coms3261 = new Course("Josh Alman", "501 NWC", "11:40-12:55", 150);
    coms3261.setEnrolledStudentCount(140);
    courses.put("3261", coms3261);
    Course coms3251 = new Course("Tony Dear", "402 CHANDLER", "1:10-3:40", 125);
    coms3251.setEnrolledStudentCount(99);
    courses.put("3251", coms3251);
    Course coms3827 = new Course("Daniel Rubenstein", "207 Math", "11:40-12:55", 300);
    coms3827.setEnrolledStudentCount(283);
    courses.put("3827", coms3827);
    Course coms4156 = new Course("Gail Kaiser", "501 NWC", "11:40-12:55", 120);
    coms4156.setEnrolledStudentCount(109);
    courses.put("4156", coms4156);

    return courses;
  }

  @BeforeAll
  public static void setupDepartmentForTesting() {
    testDepartment = new Department("1337", getTestCourseMapping(), "Chuck Norris", 10);
  }

  @Test
  public void stringNotNullTest() {
    assertNotNull(testDepartment.toString());
  }

  @Test
  public void getInstructorTest() {
    String expectedResult = "Chuck Norris";
    assertEquals(expectedResult, testDepartment.getDepartmentChair());
  }

  @Test
  public void addCourseTest() {
    Course testCourse = new Course(
            "Griffin Newbold", "417 IAB", "11:40-12:55", 250);
    int previousSize = testDepartment.getCourseSelection().size();
    testDepartment.addCourse("6000", testCourse);
    int currentSize = testDepartment.getCourseSelection().size();
    assertNotEquals(previousSize, currentSize);
  }

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

