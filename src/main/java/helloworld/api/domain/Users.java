package helloworld.api.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", allocationSize = 1)
public class Users extends BaseEntity{

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name ="has_rolled_today")
    private boolean hasRolledToday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyJackpotRolls> dailyJackpotRolls;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SavingsHistory savingsHistory;
}
