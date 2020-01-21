package com.example.lpkkk.correxhelper;

/**
 * Created by lpkkk on 10/05/17.
 */

/**
 * Created by lpkkk on 04/10/16.
 */
public class Pallet {
    private int pid;
    private String pname;
    private int pw;
    private int pl;
    private String ploc;

    public Pallet(){

    }

    public Pallet(int nid,String nname, int nw, int nl, String nloc){
        this.pid = nid;
        this.pname = nname;
        this.pw = nw;
        this.pl = nl;
        this.ploc = nloc;
    }

    public int getId(){
        return this.pid;
    }

    public String getName(){
        return this.pname;
    }

    public int getW(){
        return this.pw;
    }

    public int getL(){
        return this.pl;
    }

    public String getLoc(){
        return this.ploc;
    }

    public void setId(int nid){
        this.pid = nid;
    }

    public void setName(String nname){
        this.pname = nname;
    }

    public void setW(int nw){
        this.pw = nw;
    }

    public void setL(int nl){
        this.pl = nl;
    }

    public void setLoc(String nloc){
        this.ploc = nloc;
    }

    public String toString(){
        return "" + pid + " Name:" + pname + " Width:" + pw + " Lenght:" + pl + " Loc:" + ploc;
    }
    public String toString2(){
        return " Name:" + pname + " Width:" + pw + " Lenght:" + pl;
    }
}
