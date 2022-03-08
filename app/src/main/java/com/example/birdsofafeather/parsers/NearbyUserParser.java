package com.example.birdsofafeather.parsers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.birdsofafeather.db.AppDatabase;
import com.example.birdsofafeather.db.course.Course;
import com.example.birdsofafeather.db.user.User;
import com.example.birdsofafeather.db.user.UserWithCourses;

public class NearbyUserParser implements Parser {
    private String fieldSeparator = ",,,,";

    @Override
    public void parse(Context context, String message) {
        AppDatabase db = AppDatabase.singleton(context);
        String[] fields = message.split(fieldSeparator);
        if(fields.length <= 3) {
            Log.d("NearbyUserParser", "Nearby User Message was missing fields");
            return;
        }
        String uuid = fields[0].replaceAll("\n", "");
        String name = fields[1].replaceAll("\n", "");
        String pic_url = fields[2].replaceAll("\n", "");
        User user = new User(name, "", pic_url);
        long userId = db.userWithCoursesDao().insert(user);

        String[] courses = fields[3].split("\n");
        for(String course : courses) {
            String[] course_fields = course.split(",");
            Log.d("dafk;", String.format("%o",course_fields.length));
            if(course_fields.length <= 4) {
                Log.d("NearbyUserParser", "Nearby User Message was missing fields for a course");
                continue;
            }
            String year = course_fields[0];
            String quarter = course_fields[1];
            String parsed_quarter = "";
            if(quarter.equals("FA")) {
                parsed_quarter = "FALL";
            } else if(quarter.equals("WI")) {
                parsed_quarter = "WINTER";
            } else if(quarter.equals("SP")) {
                parsed_quarter = "SPRING";
            } else if(quarter.equals("SM1")) {
            parsed_quarter = "SUMMER1";
            } else if(quarter.equals("SM2")) {
                parsed_quarter = "SUMMER2";
            }
            String department = course_fields[2];
            String course_number = course_fields[3];
            String size = course_fields[4];
            Course new_course = new Course(userId, Integer.parseInt(year), parsed_quarter, department, Integer.parseInt(course_number), size);
            db.coursesDao().insert(new_course);
        }
    }
}
