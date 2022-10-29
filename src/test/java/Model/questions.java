package Model;


import javax.persistence.*;

@Entity
@Table(name = "questions")
public class questions {
    @Id
    @Column(name = "question")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "question_name")
    private String name;

    public questions(){

    }
    public questions(int id, String name){
        this.id=id;
        this.name=name;
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
        return "questions [id=" + id + ", name=" + name + "]";

    }
}
