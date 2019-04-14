package dekanat.entity;

public class Auditory {

    private int id;
    private String number;
    private int building;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Auditory{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", building=" + building +
                '}';
    }
}
