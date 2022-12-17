package com.example.finapp.domain.dtos;

import java.util.Date;

public class PesquisaDto {
    private Date startDate;
    private Date endDate;
    private int filterOperation;

    public PesquisaDto(Date startDate, Date endDate, int filterOperation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.filterOperation = filterOperation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFilterOperation() {
        return filterOperation;
    }

    public void setFilterOperation(int filterOperation) {
        this.filterOperation = filterOperation;
    }
}
