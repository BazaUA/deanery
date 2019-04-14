package dekanat.entity.sesiaEntity;

import java.util.Date;

public class When {

    private int id;

    private Date date;

    private int groupId;

    private int auditoriaId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(int auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    @Override
    public String toString() {
        return "When{" +
                "id=" + id +
                ", date=" + date +
                ", groupId=" + groupId +
                ", auditoriaId=" + auditoriaId +
                '}';
    }
}
