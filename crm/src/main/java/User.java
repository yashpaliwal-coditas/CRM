import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    private int id;
    private String name,email,mobileNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Issues> issuesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<IssuesResolved> issuesResolvedList;
    @ManyToOne
    Crm crm;
    @OneToOne()
    private Calls call;
    @OneToMany(mappedBy = "user")
    private List<CompletedCall> completedCallList;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<IssuesResolved> getIssuesResolvedList() {
        return issuesResolvedList;
    }

    public void setIssuesResolvedList(List<IssuesResolved> issuesResolvedList) {
        this.issuesResolvedList = issuesResolvedList;
    }

    public Calls getCall() {
        return call;
    }

    public void setCall(Calls call) {
        this.call = call;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Issues> getIssuesList() {
        return issuesList;
    }

    public void setIssuesList(List<Issues> issuesList) {
        this.issuesList = issuesList;
    }

    public Crm getCrm() {
        return crm;
    }

    public void setCrm(Crm crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", crm=" + crm +
                '}';
    }
}
