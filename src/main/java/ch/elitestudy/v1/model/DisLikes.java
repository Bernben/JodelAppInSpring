package ch.elitestudy.v1.model;

import javax.persistence.*;

@Entity
@Table(name = "dislikes",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"feedid", "username"})
)
public class DisLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "feedId")
    private String feedId;

    @Column(name = "username")
    private String username;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


