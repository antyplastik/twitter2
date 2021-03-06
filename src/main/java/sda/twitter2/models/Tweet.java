package sda.twitter2.models;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(length = 255)
    private String message;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime date;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Response> responses;

}
