package com.ljt.bean;

public class Userlogin {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userlogin.userID
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userlogin.userName
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userlogin.password
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userlogin.role
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    private Integer role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userlogin.userID
     *
     * @return the value of userlogin.userID
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userlogin.userID
     *
     * @param userid the value for userlogin.userID
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userlogin.userName
     *
     * @return the value of userlogin.userName
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userlogin.userName
     *
     * @param username the value for userlogin.userName
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userlogin.password
     *
     * @return the value of userlogin.password
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userlogin.password
     *
     * @param password the value for userlogin.password
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userlogin.role
     *
     * @return the value of userlogin.role
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userlogin.role
     *
     * @param role the value for userlogin.role
     *
     * @mbg.generated Tue Sep 17 22:29:08 CST 2019
     */
    public void setRole(Integer role) {
        this.role = role;
    }
}