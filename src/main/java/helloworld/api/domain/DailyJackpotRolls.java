package helloworld.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import helloworld.api.dto.JackpotRollValuesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "daily_jackpot_rolls")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DailyJackpotRolls extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private Users user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "dailyJackpotRolls", cascade = CascadeType.ALL)
    private List<JackpotRollValues> jackpotRollValues;
}
