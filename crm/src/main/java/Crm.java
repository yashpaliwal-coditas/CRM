import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Crm {
    @Id
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "crm")
    private List<User> userList ;

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
        return "Crm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
