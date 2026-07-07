package com.schmoll.tlkw.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName eap_material_data
 */
@TableName(value ="eap_material_data")
@Data
public class EapMaterialData implements Serializable {
    /**
     * 唯一ID：自动增长
     */
    @TableId
    private Long id;

    /**
     * 料号
     */
    private String product;

    /**
     * 批次号
     */
    private String container;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 资料类型，分三种类型：参数(para)，文件路径(path)，配方代码(recipecode)
     */
    private String materialcode;

    /**
     * 资料名称代码：资料对应的名称代码
     */
    private String itemcode;

    /**
     * 资料描述
     */
    private String itemdes;

    /**
     * 单位
     */
    private String uom;

    /**
     * 资料值
     */
    private String itemvalue;

    /**
     * 组号：EAP对每一组资料下发数据产生一个唯一组号
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
        EapMaterialData other = (EapMaterialData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProduct() == null ? other.getProduct() == null : this.getProduct().equals(other.getProduct()))
            && (this.getContainer() == null ? other.getContainer() == null : this.getContainer().equals(other.getContainer()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getMaterialcode() == null ? other.getMaterialcode() == null : this.getMaterialcode().equals(other.getMaterialcode()))
            && (this.getItemcode() == null ? other.getItemcode() == null : this.getItemcode().equals(other.getItemcode()))
            && (this.getItemdes() == null ? other.getItemdes() == null : this.getItemdes().equals(other.getItemdes()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getItemvalue() == null ? other.getItemvalue() == null : this.getItemvalue().equals(other.getItemvalue()))
            && (this.getGroupnum() == null ? other.getGroupnum() == null : this.getGroupnum().equals(other.getGroupnum()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProduct() == null) ? 0 : getProduct().hashCode());
        result = prime * result + ((getContainer() == null) ? 0 : getContainer().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getMaterialcode() == null) ? 0 : getMaterialcode().hashCode());
        result = prime * result + ((getItemcode() == null) ? 0 : getItemcode().hashCode());
        result = prime * result + ((getItemdes() == null) ? 0 : getItemdes().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getItemvalue() == null) ? 0 : getItemvalue().hashCode());
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
        sb.append(", product=").append(product);
        sb.append(", container=").append(container);
        sb.append(", count=").append(count);
        sb.append(", materialcode=").append(materialcode);
        sb.append(", itemcode=").append(itemcode);
        sb.append(", itemdes=").append(itemdes);
        sb.append(", uom=").append(uom);
        sb.append(", itemvalue=").append(itemvalue);
        sb.append(", groupnum=").append(groupnum);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}