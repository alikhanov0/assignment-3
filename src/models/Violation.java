package models;

import java.sql.Date;

public class Violation {
    private int violationId;
    private int studentId;
    private Date violationDate;
    private String description;
    private double penalty;

    public Violation(int violationId, int studentId,
            Date violationDate, String description, double penalty) {

        this.violationId = violationId;
        this.studentId = studentId;
        this.violationDate = violationDate;
        setDescription(description);
        setPenalty(penalty);
    }

    public int getViolationId() {
        return violationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
