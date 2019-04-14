package dekanat.entity.sesiaEntity;

public class Cafedra {
    private int id;
    private String name;
    private int hall;


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

    public int getHall() {
        return hall;
    }

    public void setHall(int hall) {
        this.hall = hall;
    }

    @Override
    public String toString() {
        return name;
    }
}
