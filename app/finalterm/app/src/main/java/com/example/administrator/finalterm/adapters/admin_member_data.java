package com.example.administrator.finalterm.adapters;

public class admin_member_data {
    public String name;
    public String id;
    public String identity;
    public admin_member_data(String membername,String memberid,String memberidentity){
        this.name = membername;
        this.id = memberid;
        this.identity = memberidentity;
    }

    public String getMembername() { return name; }

    public String getMemberid() { return id; }

    public String getMemberidentity() { return identity; }
}
