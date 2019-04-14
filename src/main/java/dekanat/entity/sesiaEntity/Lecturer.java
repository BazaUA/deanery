package dekanat.entity.sesiaEntity;

public class Lecturer{

    private int id;
    private String lastname;
    private String postition;
    private int cafedraId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPostition() {
        return postition;
    }

    public void setPostition(String postition) {
        this.postition = postition;
    }

    public int getCafedraId() {
        return cafedraId;
    }

    public void setCafedraId(int cafedraId) {
        this.cafedraId = cafedraId;
    }

    @Override
    public String toString() {
        return lastname;
    }
}
