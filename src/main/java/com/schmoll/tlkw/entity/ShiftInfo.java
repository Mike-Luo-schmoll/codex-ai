package com.schmoll.tlkw.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShiftInfo {
    private Date shiftStart;
    private Date shiftEnd;
    private String shiftType;
}
