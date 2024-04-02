package search;

public class Student {

    private String name;
    //private Integer age;
    private String favSubject;

    public Student() {
    }

    public Student(String name, String favSubject) {
        this.name = name;
        this.favSubject = favSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/

    public String getFavSubject() {
        return favSubject;
    }

    public void setFavSubject(String favSubject) {
        this.favSubject = favSubject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", favSubject='" + favSubject + '\'' +
                '}';
    }
}
