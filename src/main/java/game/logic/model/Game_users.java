package game.logic.model;

import java.util.Date;

public class Game_users {
	/**
	 * 用户id，主键
	 */
    private Integer numid;
    
    /**
     * 用户名
     */
    private String userid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户密码
     */
    private String passwd;
    /**
     * 用户性别
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private Integer head;

    /**
     * 设备号
     */
    private String devid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 权限id
     */
    private Integer right;
    /**
     * 游戏币
     */
    private Long gold;
    /**
     * 钻石数
     */
    private Long diamond;
    /**
     * 区域id
     */
    private Integer areaid;
    /**
     * 渠道id
     */
    private Integer channelid;
    /**
     * 登陆时间
     */
    private Date ftime;
    /**
     * 用户类型
     */
    private Integer usertype;
    /**
     * 老的用户名
     */
    private String olduserid;

    public Integer getNumid() {
        return numid;
    }

    public void setNumid(Integer numid) {
        this.numid = numid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid == null ? null : devid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getDiamond() {
        return diamond;
    }

    public void setDiamond(Long diamond) {
        this.diamond = diamond;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Date getFtime() {
        return ftime;
    }

    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getOlduserid() {
        return olduserid;
    }

    public void setOlduserid(String olduserid) {
        this.olduserid = olduserid == null ? null : olduserid.trim();
    }
}