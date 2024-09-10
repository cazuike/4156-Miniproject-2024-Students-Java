package dev.coms4156.project.individualproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class contains unit and integration tests for RouteController.java.
 *
 * <p> Unit tests involve setting up an environment for testing and conducting the
 * necessary tests to ensure functionality. </p>
 */
@WebMvcTest(RouteController.class)
public class RouteControllerTest {

  @Autowired
  private MockMvc mockMvc;
  private MyFileDatabase myFileDatabase;

  /**
   * Sets up the testing environment to make mock api calls and ensure functionality
   * of routeController.
   */
  @BeforeEach
  public void setUp() {
    Map<String, Course> courses = new HashMap<>();
    courses.put("1004", new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400));
    Department comsDept = new Department("COMS", courses, "Luka Doncic", 999);

    Map<String, Department> departmentMapping = new HashMap<>();
    departmentMapping.put("COMS", comsDept);

    myFileDatabase = new MyFileDatabase(1, "./testdata.txt");
    myFileDatabase.setMapping(departmentMapping);

    IndividualProjectApplication.overrideDatabase(myFileDatabase);
  }

  /** Validate home message returns expected message. */
  @Test
  public void homeMessageTest() throws Exception {
    mockMvc.perform(get("/index"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                    "Welcome, in order to make an API call direct your browser"
                    + " or Postman to an endpoint "
                    + "\n\n This can be done using the following format: \n\n http:127.0.0"
                    + ".1:8080/endpoint?arg=value"));
  }

  /** Expects not found upon receiving invalid department. */
  @Test
  public void departmentNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveDept").param("deptCode", "NBA"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Department Not Found"));
  }

  /** Expects department info upon receiving valid input. */
  @Test
  public void departmentFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveDept").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("COMS 1004: \n"
                    + "Instructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55\n"));
  }

  /** Expects course info upon receiving valid input. */
  @Test
  public void courseFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveCourse").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("\nInstructor: Adam Cannon;"
                    + " Location: 417 IAB;"
                    + " Time: 11:40-12:55"));
  }

  /** Expects false upon requesting course info. */
  @Test
  public void courseNotFullTest() throws Exception {
    mockMvc.perform(get("/isCourseFull").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
  }

  /** Expects 999 when requesting for major count. */
  @Test
  public void majorCountTest() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 999 majors in the department"));
  }

  /** Expects Luka Doncic when requesting for department chair. */
  @Test
  public void idDeptChairTest() throws Exception {
    mockMvc.perform(get("/idDeptChair").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Luka Doncic is the department chair."));
  }

  /** Expects 417 IAB when requesting for course location. */
  @Test
  public void courseLocationTest() throws Exception {
    mockMvc.perform(get("/findCourseLocation").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("417 IAB is where the course "
                    + "is located."));
  }

  /** Expects Adam Cannon when requesting for course instructor. */
  @Test
  public void courseInstructorTest() throws Exception {
    mockMvc.perform(get("/findCourseInstructor").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Adam Cannon is the instructor for"
                    + " the course."));
  }

  /** Expects 11:40-12:55 when requesting for course time. */
  @Test
  public void courseTimeTest() throws Exception {
    mockMvc.perform(get("/findCourseTime").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("The course meets at: 11:40-12:55"));
  }

  /** Expects success message upon request, confirms change. */
  @Test
  public void addMajorTest() throws Exception {
    mockMvc.perform(patch("/addMajorToDept").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attribute was updated successfully"));
    mockMvc.perform(get("/getMajorCountFromDept").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 1000 majors in the department"));
  }

  /** Expects success message upon request. */
  @Test
  public void removeMajorTest() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept").param("deptCode", "COMS"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attribute was updated or is at minimum"));
  }

  /** Expects bad request message upon request. */
  @Test
  public void dropStudentTest() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Student has not been dropped."));
  }

  /** Expects success message upon request. */
  @Test
  public void setEnrollmentTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount").param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("count", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
  }

  /** Set enrollment to 10 and expects success message upon dropStudent request, confirm change. */
  @Test
  public void dropStudentAfterEnrollTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount").param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("count", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(patch("/dropStudentFromCourse").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Student has been dropped."));
  }

  /** Expects success message upon request, confirms change. */
  @Test
  public void changeTimeTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTime").param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("time", "1:10-2:25"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseTime").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("The course meets at: 1:10-2:25"));
  }

  /** Expects success message upon request, confirms change. */
  @Test
  public void changeTeacherTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher").param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("teacher", "Sunil Gulati"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseInstructor").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("Sunil Gulati is the instructor for"
                    + " the course."));
  }

  /** Expects success message upon request, confirms change. */
  @Test
  public void changeLocationTest() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation").param("deptCode", "COMS")
                    .param("courseCode", "1004")
                    .param("location", "451 CSB"))
            .andExpect(status().isOk())
            .andExpect(content().string("Attributed was updated successfully."));
    mockMvc.perform(get("/findCourseLocation").param("deptCode", "COMS")
                    .param("courseCode", "1004"))
            .andExpect(status().isOk())
            .andExpect(content().string("451 CSB is where the course "
                    + "is located."));
  }

  /** Expects internal server error message upon request. */
  @Test
  public void expectErrorTest() throws Exception {
    myFileDatabase.setMapping(null);
    mockMvc.perform(get("/retrieveDept").param("deptCode", "COMS"))
            .andExpect(status().isInternalServerError());
  }
}
