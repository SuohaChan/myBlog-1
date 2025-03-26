package com.tree.vo;


public class LinkVo {
    private int id;

    private String name;

    private String logo;//图像

    private String Description;//描述

    private String address;//友链

    public LinkVo() {
    }

    public LinkVo(int id, String name, String logo, String Description, String address) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.Description = Description;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取
     * @return Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * 设置
     * @param Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "LinkVo{id = " + id + ", name = " + name + ", logo = " + logo + ", Description = " + Description + ", address = " + address + "}";
    }
}
