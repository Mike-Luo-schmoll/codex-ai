package com.schmoll.tlkw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName eap_signal_status
 */
@TableName(value ="eap_signal_status")
@Data
public class EapSignalStatus implements Serializable {
    /**
     * 唯一ID：自动增长
     */
    @TableId
    private Long id;

    /**
     * 信号代码
     */
    private String signalcode;

    /**
     * 信号描述
     */
    private String signaldes;

    /**
     * 信号值
     */
    private String signalvalue;

    /**
     * 时间:yyyy-mm-dd hh:mm:ss
     */
    private Date createtime;

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
        EapSignalStatus other = (EapSignalStatus) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSignalcode() == null ? other.getSignalcode() == null : this.getSignalcode().equals(other.getSignalcode()))
            && (this.getSignaldes() == null ? other.getSignaldes() == null : this.getSignaldes().equals(other.getSignaldes()))
            && (this.getSignalvalue() == null ? other.getSignalvalue() == null : this.getSignalvalue().equals(other.getSignalvalue()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSignalcode() == null) ? 0 : getSignalcode().hashCode());
        result = prime * result + ((getSignaldes() == null) ? 0 : getSignaldes().hashCode());
        result = prime * result + ((getSignalvalue() == null) ? 0 : getSignalvalue().hashCode());
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
        sb.append(", signalcode=").append(signalcode);
        sb.append(", signaldes=").append(signaldes);
        sb.append(", signalvalue=").append(signalvalue);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}