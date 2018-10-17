package com.codefans.designpattern.prototype.cglib;

/**
 * Created by Administrator on 2017/3/11.
 */
public class BeanCopierTestBean extends CglibCloneable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "BeanCopierTestBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
