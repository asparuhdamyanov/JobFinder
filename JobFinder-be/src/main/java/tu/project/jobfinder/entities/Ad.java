package tu.project.jobfinder.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "company_name")
    private String company_name;

    @ManyToMany()
    @JoinTable(name="ad_users",
            joinColumns = @JoinColumn(name="ad_id"),
            inverseJoinColumns = @JoinColumn(name= "users_id"))
    private Set<Users> users = new HashSet<>();

    public Ad() {
    }

    public Ad(Long id, String title, String description, String company_name) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company_name = company_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
