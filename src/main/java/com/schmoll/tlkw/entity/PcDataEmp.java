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
 * @TableName pc_data_emp
 */
@TableName(value ="pc_data_emp")
@Data
public class PcDataEmp implements Serializable {
    /**
     * 唯一ID：自动增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 员工工号（必填项）
     */
    private String empno;

    /**
     * 员工姓名（可为空）
     */
    private String empname;

    /**
     * 操作代码(上机代码：INTIME下机代码：OUTTIME)
     */
    private String empcode;

    /**
     * 操作名称
     */
    private String empdes;

    /**
     * 操作时间
yyyy-mm-dd hh:mm:ss
     */
    private Date emptime;

    /**
     * 时间 yyyy-mm-dd hh:mm:ss
     */
    private Date createtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public PcDataEmp() {
    }

    public PcDataEmp(Long id, String empno, String empname, String empcode, String empdes, Date emptime, Date createtime) {
        this.id = id;
        this.empno = empno;
        this.empname = empname;
        this.empcode = empcode;
        this.empdes = empdes;
        this.emptime = emptime;
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
        PcDataEmp other = (PcDataEmp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmpno() == null ? other.getEmpno() == null : this.getEmpno().equals(other.getEmpno()))
            && (this.getEmpname() == null ? other.getEmpname() == null : this.getEmpname().equals(other.getEmpname()))
            && (this.getEmpcode() == null ? other.getEmpcode() == null : this.getEmpcode().equals(other.getEmpcode()))
            && (this.getEmpdes() == null ? other.getEmpdes() == null : this.getEmpdes().equals(other.getEmpdes()))
            && (this.getEmptime() == null ? other.getEmptime() == null : this.getEmptime().equals(other.getEmptime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmpno() == null) ? 0 : getEmpno().hashCode());
        result = prime * result + ((getEmpname() == null) ? 0 : getEmpname().hashCode());
        result = prime * result + ((getEmpcode() == null) ? 0 : getEmpcode().hashCode());
        result = prime * result + ((getEmpdes() == null) ? 0 : getEmpdes().hashCode());
        result = prime * result + ((getEmptime() == null) ? 0 : getEmptime().hashCode());
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
        sb.append(", empno=").append(empno);
        sb.append(", empname=").append(empname);
        sb.append(", empcode=").append(empcode);
        sb.append(", empdes=").append(empdes);
        sb.append(", emptime=").append(emptime);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}