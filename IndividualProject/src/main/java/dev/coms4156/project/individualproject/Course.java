package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents courses with the provided instructor name, course location,
 * time slot, and capacity.
 */
public class Course implements Serializable {

  @Serial
  private static final long serialVersionUID = 123456L;
  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String instructorName;
  private String courseTimeSlot;

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * <p> This constructor initializes the course with the provided instructor name, course location,
   * time slot, and capacity. </p>
   *
   * @param instructorName     The name of the instructor teaching the course.
   * @param courseLocation     The location where the course is held.
   * @param timeSlot           The time slot of the course.
   * @param capacity           The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    this.courseLocation = courseLocation;
    this.instructorName = instructorName;
    this.courseTimeSlot = timeSlot;
    this.enrollmentCapacity = capacity;
    this.enrolledStudentCount = 0;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public boolean enrollStudent() {
    if (isCourseFull()) {
      return false;
    }
    enrolledStudentCount++;
    return true;
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public boolean dropStudent() {
    if (enrolledStudentCount == 0) {
      return false;
    }
    enrolledStudentCount--;
    return true;
  }

  /**
   * Returns the course location.
   *
   * @return a string representing the location of the course.
   */
  public String getCourseLocation() {
    return this.courseLocation;
  }

  /**
   * Returns the name of course instructor.
   *
   * @return a string representing the name of course instructor.
   */
  public String getInstructorName() {
    return this.instructorName;
  }

  /**
   * Returns the time slot of the course.
   *
   * @return a string representing the time slot of the course.
   */
  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }

  /**
   * Returns a string representation of the course object.
   *
   * @return a formatted string containing course info.
   */
  public String toString() {
    return "\nInstructor: " + instructorName +  "; Location: "  + courseLocation
            +  "; Time: " + courseTimeSlot;
  }

  /**
   * Reassign the course instructor.
   *
   *  @param newInstructorName  The name of the new instructor teaching the course.
   */
  public void reassignInstructor(String newInstructorName) {
    this.instructorName = newInstructorName;
  }

  /**
   * Reassign the location of the course.
   *
   *  @param newLocation  The name of the new location of the course.
   */
  public void reassignLocation(String newLocation) {
    this.courseLocation = newLocation;
  }

  /**
   * Reassign the course time.
   *
   *  @param newTime  The name of the new time of the course.
   */
  public void reassignTime(String newTime) {
    this.courseTimeSlot = newTime;
  }

  /**
   * Reassign the enrolled student count.
   *
   *  @param count  The new count of enrolled students.
   */
  public void setEnrolledStudentCount(int count) {
    this.enrolledStudentCount = count;
  }

  /** Returns a boolean indicating if the course is full.
   *
   *
   *  @return true if the course is full, false otherwise.
   */
  public boolean isCourseFull() {
    return enrollmentCapacity <= enrolledStudentCount;
  }
}
