pacakage com.example.demo.entity;
import java.Time.*;
import jakarta.persistence.*;

@Entity
public class FinancialProfile{
    @OneToOneUser
    @Id;
    @GenerativedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String user;
    private Double monthlyIncome;
    private Double monthlyExpenses;
    private Double existingLoanEmi;
    private Integer creditScore;
    private Double savingsBalance;
    private Timestamp lastUpdatedAt;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getUser(){
        return user;
    }
    public 

}