package com.example.birdsofafeather.db.user;

import com.example.birdsofafeather.db.course.Course;

import java.util.List;

public interface IUser {
    long getId();
    String getName();
    String getEmail();
    String getProfilePictureUrl();
    List<Course> getCourses();

    void addCourse(Course course);
    void setName(String name);
    void setEmail(String email);
}
