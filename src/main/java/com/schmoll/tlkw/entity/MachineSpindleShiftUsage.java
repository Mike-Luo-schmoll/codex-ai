package com.schmoll.tlkw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName machine_spindle_shift_usage
 */
@TableName(value ="machine_spindle_shift_usage")
@Data
public class MachineSpindleShiftUsage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String machineId;

    /**
     * 
     */
    private Date shiftStartTime;

    /**
     * 
     */
    private Date shiftEndTime;

    /**
     * 
     */
    private String shiftType;

    /**
     * 
     */
    private Integer spindleId;

    /**
     * 
     */
    private Integer startSpindleHour;

    /**
     * 
     */
    private Integer currentSpindleHour;

    /**
     * 
     */
    private Integer shiftUsedHour;

    /**
     * 
     */
    private Date lastCollectTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MachineSpindleShiftUsage other = (MachineSpindleShiftUsage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMachineId() == null ? other.getMachineId() == null : this.getMachineId().equals(other.getMachineId()))
            && (this.getShiftStartTime() == null ? other.getShiftStartTime() == null : this.getShiftStartTime().equals(other.getShiftStartTime()))
            && (this.getShiftEndTime() == null ? other.getShiftEndTime() == null : this.getShiftEndTime().equals(other.getShiftEndTime()))
            && (this.getShiftType() == null ? other.getShiftType() == null : this.getShiftType().equals(other.getShiftType()))
            && (this.getSpindleId() == null ? other.getSpindleId() == null : this.getSpindleId().equals(other.getSpindleId()))
            && (this.getStartSpindleHour() == null ? other.getStartSpindleHour() == null : this.getStartSpindleHour().equals(other.getStartSpindleHour()))
            && (this.getCurrentSpindleHour() == null ? other.getCurrentSpindleHour() == null : this.getCurrentSpindleHour().equals(other.getCurrentSpindleHour()))
            && (this.getShiftUsedHour() == null ? other.getShiftUsedHour() == null : this.getShiftUsedHour().equals(other.getShiftUsedHour()))
            && (this.getLastCollectTime() == null ? other.getLastCollectTime() == null : this.getLastCollectTime().equals(other.getLastCollectTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMachineId() == null) ? 0 : getMachineId().hashCode());
        result = prime * result + ((getShiftStartTime() == null) ? 0 : getShiftStartTime().hashCode());
        result = prime * result + ((getShiftEndTime() == null) ? 0 : getShiftEndTime().hashCode());
        result = prime * result + ((getShiftType() == null) ? 0 : getShiftType().hashCode());
        result = prime * result + ((getSpindleId() == null) ? 0 : getSpindleId().hashCode());
        result = prime * result + ((getStartSpindleHour() == null) ? 0 : getStartSpindleHour().hashCode());
        result = prime * result + ((getCurrentSpindleHour() == null) ? 0 : getCurrentSpindleHour().hashCode());
        result = prime * result + ((getShiftUsedHour() == null) ? 0 : getShiftUsedHour().hashCode());
        result = prime * result + ((getLastCollectTime() == null) ? 0 : getLastCollectTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", machineId=").append(machineId);
        sb.append(", shiftStartTime=").append(shiftStartTime);
        sb.append(", shiftEndTime=").append(shiftEndTime);
        sb.append(", shiftType=").append(shiftType);
        sb.append(", spindleId=").append(spindleId);
        sb.append(", startSpindleHour=").append(startSpindleHour);
        sb.append(", currentSpindleHour=").append(currentSpindleHour);
        sb.append(", shiftUsedHour=").append(shiftUsedHour);
        sb.append(", lastCollectTime=").append(lastCollectTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}