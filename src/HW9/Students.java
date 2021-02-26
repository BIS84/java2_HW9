package HW9;

import java.util.ArrayList;
import java.util.List;

class Students implements Student {
    private String name;
    private List<Course> courses = new ArrayList<Course>();

    public String getName(){ return name; }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        courses.add(course);
    }

    @Override
    public List<Course> getAllCourses(){
        return courses;
    }
}
