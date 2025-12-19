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
    private

}