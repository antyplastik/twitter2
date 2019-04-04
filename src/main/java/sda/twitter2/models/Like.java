package sda.twitter2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tweetId")
    private Tweet tweetId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime date;

    private boolean active;
}
