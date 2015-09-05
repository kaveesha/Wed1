package com.sep.wed1;

/**
 * Created by kavi on 9/4/2015.
 */
public class guest {
    int id;
    String name;
    String lastname;
    String side;
    String invite;
    String attending;
    String tbno;

    public guest(int id, String name, String lastname, String side, String invite, String attending, String tbno) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.side = side;
        this.invite = invite;
        this.attending = attending;
        this.tbno = tbno;
    }

    public guest(int id, String name, String lastname, String side, String invite, String attending) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.side = side;
        this.invite = invite;
        this.attending = attending;
    }

    public guest(int id, String name, String side, String invite, String attending) {
        this.id = id;
        this.name = name;
        this.side = side;
        this.invite = invite;
        this.attending = attending;
    }

    public String getTbno() {
        return tbno;
    }

    public void setTbno(String tbno) {
        this.tbno = tbno;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getAttending() {
        return attending;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }
}
