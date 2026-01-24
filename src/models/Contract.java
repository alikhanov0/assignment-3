package models;

import java.sql.Date;

public class Contract {
    private int contractId;
    private int studentId;
    private Date startDate;
    private Date endDate;
    private double monthlyFee;

    public Contract(int contractId, int studentId,
            Date startDate, Date endDate, double monthlyFee) {

        this.contractId = contractId;
        this.studentId = studentId;
        this.startDate = startDate;
        this.endDate = endDate;
        setMonthlyFee(monthlyFee);
    }

    public int getContractId() {
        return contractId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
