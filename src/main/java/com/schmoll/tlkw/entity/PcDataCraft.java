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
 * @TableName pc_data_craft
 */
@TableName(value ="pc_data_craft")
@Data
public class PcDataCraft implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 批次号
     */
    private String container;

    /**
     * 版号
     */
    private String pnlidorsetid;

    /**
     * 采集项目代码
     */
    private String itemcode;

    /**
     * 采集项目描述
     */
    private String itemdes;

    /**
     * 单位
     */
    private String uom;

    /**
     * 采集值
     */
    private String itemvalue;

    /**
     * 合格标志
     */
    private String isok;

    /**
     * 组号：设备对每一次数据采集数据产生一个唯一组号时间转换成yyMMddhhmmssff
     */
    private Long groupnum;

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
        PcDataCraft other = (PcDataCraft) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContainer() == null ? other.getContainer() == null : this.getContainer().equals(other.getContainer()))
            && (this.getPnlidorsetid() == null ? other.getPnlidorsetid() == null : this.getPnlidorsetid().equals(other.getPnlidorsetid()))
            && (this.getItemcode() == null ? other.getItemcode() == null : this.getItemcode().equals(other.getItemcode()))
            && (this.getItemdes() == null ? other.getItemdes() == null : this.getItemdes().equals(other.getItemdes()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getItemvalue() == null ? other.getItemvalue() == null : this.getItemvalue().equals(other.getItemvalue()))
            && (this.getIsok() == null ? other.getIsok() == null : this.getIsok().equals(other.getIsok()))
            && (this.getGroupnum() == null ? other.getGroupnum() == null : this.getGroupnum().equals(other.getGroupnum()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContainer() == null) ? 0 : getContainer().hashCode());
        result = prime * result + ((getPnlidorsetid() == null) ? 0 : getPnlidorsetid().hashCode());
        result = prime * result + ((getItemcode() == null) ? 0 : getItemcode().hashCode());
        result = prime * result + ((getItemdes() == null) ? 0 : getItemdes().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getItemvalue() == null) ? 0 : getItemvalue().hashCode());
        result = prime * result + ((getIsok() == null) ? 0 : getIsok().hashCode());
        result = prime * result + ((getGroupnum() == null) ? 0 : getGroupnum().hashCode());
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
        sb.append(", pnlidorsetid=").append(pnlidorsetid);
        sb.append(", itemcode=").append(itemcode);
        sb.append(", itemdes=").append(itemdes);
        sb.append(", uom=").append(uom);
        sb.append(", itemvalue=").append(itemvalue);
        sb.append(", isok=").append(isok);
        sb.append(", groupnum=").append(groupnum);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}