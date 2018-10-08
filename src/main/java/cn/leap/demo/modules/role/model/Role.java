package cn.leap.demo.modules.role.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author tan123
 * @since 2018-09-18
 */
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色序号
     */
    @TableId(value = "RoleId", type = IdType.AUTO)
    private Long RoleId;
    /**
     * 角色名称
     */
    private String RoleName;
    /**
     * 描述信息
     */
    private String Description;
    /**
     * 默认角色标记
            0：非默认角色；
            1：默认角色；
     */
    private Boolean DefaultFlag;
    /**
     * 状态
            0：删除        1：无效       2：冻结        100：有效
     */
    private Integer State;
    /**
     * 创建时间
     */
    private Date CreateTime;
    /**
     * 是否系统数据
            0：非系统数据；
            1：系统数据
     */
    private Boolean SysFlag;
    /**
     * 创建者
     */
    private Long Creater;
    /**
     * 编辑人
     */
    private Long Editer;
    /**
     * 编辑时间
     */
    private Date EditTime;
    /**
     * 数据版本号
     */
    private Long Version;


    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long RoleId) {
        this.RoleId = RoleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Boolean getDefaultFlag() {
        return DefaultFlag;
    }

    public void setDefaultFlag(Boolean DefaultFlag) {
        this.DefaultFlag = DefaultFlag;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Boolean getSysFlag() {
        return SysFlag;
    }

    public void setSysFlag(Boolean SysFlag) {
        this.SysFlag = SysFlag;
    }

    public Long getCreater() {
        return Creater;
    }

    public void setCreater(Long Creater) {
        this.Creater = Creater;
    }

    public Long getEditer() {
        return Editer;
    }

    public void setEditer(Long Editer) {
        this.Editer = Editer;
    }

    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    public Long getVersion() {
        return Version;
    }

    public void setVersion(Long Version) {
        this.Version = Version;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", RoleId=" + RoleId +
        ", RoleName=" + RoleName +
        ", Description=" + Description +
        ", DefaultFlag=" + DefaultFlag +
        ", State=" + State +
        ", CreateTime=" + CreateTime +
        ", SysFlag=" + SysFlag +
        ", Creater=" + Creater +
        ", Editer=" + Editer +
        ", EditTime=" + EditTime +
        ", Version=" + Version +
        "}";
    }
}
