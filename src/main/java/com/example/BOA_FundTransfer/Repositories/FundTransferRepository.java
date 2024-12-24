package com.example.BOA_FundTransfer.Repositories;

import com.example.BOA_FundTransfer.Model.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {
    List<FundTransfer> findAllBySenderAccountId(Integer senderAccountId);

    List<FundTransfer> findAllByReceiverAccountId(Integer receiverAccountId);

    List<FundTransfer> findByReceiverAccountId(Integer receiverAccountId);

    List<FundTransfer> findBySenderAccountId(Integer senderAccountId);
    // You can add custom queries if needed
}
