package com.schmoll.tlkw.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName pc_data_alarm
 */
@TableName(value ="pc_data_alarm")
@Data
public class PcDataAlarm implements Serializable {
    /**
     * 唯一ID：自动增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 报警代码
     */
    private String alarmcode;

    /**
     * 报警描述
     */
    private String alarmdes;

    /**
     * 报警值(0/1)：0复位，1报警
     */
    private String alarmvalue;

    /**
     * 时间:yyyy-mm-dd hh:mm:ss
     */
    private Date createtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public PcDataAlarm() {
    }

    public PcDataAlarm(Long id, String alarmcode, String alarmdes, String alarmvalue, Date createtime) {
        this.id = id;
        this.alarmcode = alarmcode;
        this.alarmdes = alarmdes;
        this.alarmvalue = alarmvalue;
        this.createtime = createtime;
    }

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
        PcDataAlarm other = (PcDataAlarm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmcode() == null ? other.getAlarmcode() == null : this.getAlarmcode().equals(other.getAlarmcode()))
            && (this.getAlarmdes() == null ? other.getAlarmdes() == null : this.getAlarmdes().equals(other.getAlarmdes()))
            && (this.getAlarmvalue() == null ? other.getAlarmvalue() == null : this.getAlarmvalue().equals(other.getAlarmvalue()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmcode() == null) ? 0 : getAlarmcode().hashCode());
        result = prime * result + ((getAlarmdes() == null) ? 0 : getAlarmdes().hashCode());
        result = prime * result + ((getAlarmvalue() == null) ? 0 : getAlarmvalue().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alarmcode=").append(alarmcode);
        sb.append(", alarmdes=").append(alarmdes);
        sb.append(", alarmvalue=").append(alarmvalue);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}