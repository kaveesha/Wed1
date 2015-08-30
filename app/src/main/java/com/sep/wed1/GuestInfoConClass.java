package com.sep.wed1;

/**
 * Created by kavi on 6/19/2015.
 */
public class GuestInfoConClass
{
    int Id;
    String Fname;
    String Lname;
    String Side;
    int SeatNo;
    String InvitesSent;
    String Attending;

    public GuestInfoConClass()
    {

    }

    public GuestInfoConClass(int id, String fname, String lname, String side, int seatNo, String invitesSent, String attending) {
        Id = id;
        Fname = fname;
        Lname = lname;
        Side = side;
        SeatNo = seatNo;
        InvitesSent = invitesSent;
        Attending = attending;
    }

    public GuestInfoConClass(String fname, String lname, String side, int seatNo, String invitesSent, String attending) {
        Fname = fname;
        Lname = lname;
        Side = side;
        SeatNo = seatNo;
        InvitesSent = invitesSent;
        Attending = attending;
    }

    public GuestInfoConClass(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getSide() {
        return Side;
    }

    public void setSide(String side) {
        Side = side;
    }

    public int getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(int seatNo) {
        SeatNo = seatNo;
    }

    public String getInvitesSent() {
        return InvitesSent;
    }

    public void setInvitesSent(String invitesSent) {
        InvitesSent = invitesSent;
    }

    public String getAttending() {
        return Attending;
    }

    public void setAttending(String attending) {
        Attending = attending;
    }
}
