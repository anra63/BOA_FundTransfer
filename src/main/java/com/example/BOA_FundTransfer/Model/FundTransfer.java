package com.example.BOA_FundTransfer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fund_transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(name = "sender_account_id")
    private Integer senderAccountId;

    @Column(name = "receiver_account_id")
    private Integer receiverAccountId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status;  // For example: "PENDING", "COMPLETED", "FAILED"
}
