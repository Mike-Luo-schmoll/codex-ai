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
 * @TableName pc_data_scan
 */
@TableName(value ="pc_data_scan")
@Data
public class PcDataScan implements Serializable {
    /**
     * 唯一ID：主键增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 批次号
(Lot No)
     */
    private String container;

    /**
     * 父级码
     */
    private String parentid;

    /**
     * 码类型(PCSID/SETID/PNLID/DRILLID)
     */
    private String drctype;

    /**
     * 码内容
     */
    private String pnlidorsetid;

    /**
     * 建立人(可以为设备代码)
     */
    private String createuser;

    /**
     * 时间:yy-mm-dd hh:mm:ss
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
        PcDataScan other = (PcDataScan) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContainer() == null ? other.getContainer() == null : this.getContainer().equals(other.getContainer()))
            && (this.getParentid() == null ? other.getParentid() == null : this.getParentid().equals(other.getParentid()))
            && (this.getDrctype() == null ? other.getDrctype() == null : this.getDrctype().equals(other.getDrctype()))
            && (this.getPnlidorsetid() == null ? other.getPnlidorsetid() == null : this.getPnlidorsetid().equals(other.getPnlidorsetid()))
            && (this.getCreateuser() == null ? other.getCreateuser() == null : this.getCreateuser().equals(other.getCreateuser()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContainer() == null) ? 0 : getContainer().hashCode());
        result = prime * result + ((getParentid() == null) ? 0 : getParentid().hashCode());
        result = prime * result + ((getDrctype() == null) ? 0 : getDrctype().hashCode());
        result = prime * result + ((getPnlidorsetid() == null) ? 0 : getPnlidorsetid().hashCode());
        result = prime * result + ((getCreateuser() == null) ? 0 : getCreateuser().hashCode());
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
        sb.append(", container=").append(container);
        sb.append(", parentid=").append(parentid);
        sb.append(", drctype=").append(drctype);
        sb.append(", pnlidorsetid=").append(pnlidorsetid);
        sb.append(", createuser=").append(createuser);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}