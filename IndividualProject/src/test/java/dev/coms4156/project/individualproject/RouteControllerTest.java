package dev.coms4156.project.individualproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


/**
 * This class contains integration testing for RouteController.java
 * Integration tests involve setting up an environment for testing and conducting the
 * necessary tests to integrated parts work properly.
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
    HashMap<String, Course> courses = new HashMap<>();
    courses.put("1004", new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400));
    Department comsDept = new Department("COMS", courses, "Luka Doncic", 999);

    HashMap<String, Department> departmentMapping = new HashMap<>();
    departmentMapping.put("COMS", comsDept);

    myFileDatabase = new MyFileDatabase(1, "./testdata.txt");
    myFileDatabase.setMapping(departmentMapping);

    IndividualProjectApplication.overrideDatabase(myFileDatabase);
  }


  @Test
  public void departmentNotFoundTest() throws Exception {
    mockMvc.perform(get("/retrieveDept").param("deptCode", "NBA"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Department Not Found"));
  }
}
