package Model;


import java.util.ArrayList;
import java.util.List;

public class subject {


    private int id ;
    private String name;
    private List<quetions> qList= new ArrayList<>();
    public subject() {

    }
    public subject(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public subject(int id, String name,ArrayList qList) {
        super();
        this.id = id;
        this.name = name;
        this.qList=qList;
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
    @Override
    public String toString() {
        return "subjects [id=" + id + ", name=" + name + "]";

    }

}
