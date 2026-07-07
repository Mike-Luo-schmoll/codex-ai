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
 * @TableName pc_data_manualmaterial
 */
@TableName(value ="pc_data_manualmaterial")
@Data
public class PcDataManualmaterial implements Serializable {
    /**
     * 唯一ID：自动增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 批次号
     */
    private String container;

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
     * 上一个参数值
     */
    private String olditemvalue;

    /**
     * 修改后参数值
     */
    private String newitemvalue;

    /**
     * 合格标志
     */
    private String isok;

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
        PcDataManualmaterial other = (PcDataManualmaterial) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContainer() == null ? other.getContainer() == null : this.getContainer().equals(other.getContainer()))
            && (this.getItemcode() == null ? other.getItemcode() == null : this.getItemcode().equals(other.getItemcode()))
            && (this.getItemdes() == null ? other.getItemdes() == null : this.getItemdes().equals(other.getItemdes()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getOlditemvalue() == null ? other.getOlditemvalue() == null : this.getOlditemvalue().equals(other.getOlditemvalue()))
            && (this.getNewitemvalue() == null ? other.getNewitemvalue() == null : this.getNewitemvalue().equals(other.getNewitemvalue()))
            && (this.getIsok() == null ? other.getIsok() == null : this.getIsok().equals(other.getIsok()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContainer() == null) ? 0 : getContainer().hashCode());
        result = prime * result + ((getItemcode() == null) ? 0 : getItemcode().hashCode());
        result = prime * result + ((getItemdes() == null) ? 0 : getItemdes().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getOlditemvalue() == null) ? 0 : getOlditemvalue().hashCode());
        result = prime * result + ((getNewitemvalue() == null) ? 0 : getNewitemvalue().hashCode());
        result = prime * result + ((getIsok() == null) ? 0 : getIsok().hashCode());
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
        sb.append(", itemcode=").append(itemcode);
        sb.append(", itemdes=").append(itemdes);
        sb.append(", uom=").append(uom);
        sb.append(", olditemvalue=").append(olditemvalue);
        sb.append(", newitemvalue=").append(newitemvalue);
        sb.append(", isok=").append(isok);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}