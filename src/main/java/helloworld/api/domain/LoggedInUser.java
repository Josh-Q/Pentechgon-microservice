package helloworld.api.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "logged_in_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoggedInUser extends BaseEntity{

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "csrf_id")
    private String csrfId;
}
