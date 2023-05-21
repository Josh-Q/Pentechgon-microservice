package helloworld.api.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "savings_history")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@SequenceGenerator(name = "savings_history_generator", sequenceName = "savings_history_sequence", allocationSize = 1)
public class SavingsHistory extends BaseEntity {

    public SavingsHistory(Users users) {
        this.user = users;
        this.maxStreakSavings = 0;
        this.currentStreakSavings = 0;
        this.maxStreakDays = 0;
        this.currentStreakDays = 0;
        this.totalSavings = 0;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    @Column(name ="max_streak_savings")
    private Integer maxStreakSavings;

    @Column(name ="current_streak_savings")
    private Integer currentStreakSavings;

    @Column(name ="max_streak_days")
    private Integer maxStreakDays;

    @Column(name ="current_streak_days")
    private Integer currentStreakDays;

    @Column(name ="total_savings")
    private Integer totalSavings;
}
