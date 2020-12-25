package com.adel.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="studentId", unique=true, nullable = false)
    private int gradeId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    //@JoinColumn(name = "studentId", insertable = false, updatable = false)
    private Student student;

    @OneToOne
    private Course course;

    //@NotBlank
    @DecimalMin(value="0", inclusive=true, message="Mark value must be greater or equal 0")
    @DecimalMax(value="100", inclusive=true, message="Mark value must be less or equal 100")
    private int mark;


    private int semester;

    private String year;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    @Transient
    public String getGrade(){
        String note;

        if(mark <=100 && mark >=96){
            note = "A+";
        }else if(mark <96 && mark >=91){
            note = "A";
        }else if(mark <91 && mark >=86){
            note = "A-";
        }else if(mark <86 && mark >=81){
            note = "B+";
        }else if(mark <81 && mark >=76){
            note = "B";
        }else if(mark <76 && mark >=71){
            note = "B-";
        }else if(mark <71 && mark >=66){
            note = "C+";
        }else if(mark <66 && mark >=61){
            note = "C";
        }else if(mark <61 && mark >=56){
            note = "C-";
        }else{
            note = "D";
        }

        return note;
    }

}
