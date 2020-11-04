package ch.elitestudy.v1.model;

import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username")
	private String username;


	@Column(name = "feedId")
	private long feedId;




	@Column(name = "comment")
	private String comment;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public String getTimestamp() {

		Date postedDate = new Date();
		postedDate.setTime(timestamp.getTime());
		String prettyTimeString = new PrettyTime().format(postedDate);

		return prettyTimeString;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userId) {
		this.username = userId;
	}

	public Long getFeedId() {
		return feedId;
	}

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}