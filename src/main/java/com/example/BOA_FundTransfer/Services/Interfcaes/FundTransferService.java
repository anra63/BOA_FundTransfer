package com.example.BOA_FundTransfer.Services.Interfcaes;

import com.example.BOA_FundTransfer.Model.FundTransfer;

import java.util.List;
import java.util.Optional;

public interface FundTransferService {

    // Create a fund transfer record
    FundTransfer createTransfer(FundTransfer fundTransfer);
    // Find all transfers by sender account ID
    List<FundTransfer> findAllBySenderAccountId(Integer senderAccountId);

    // Find all transfers by receiver account ID
    List<FundTransfer> findAllByReceiverAccountId(Integer receiverAccountId);

    // Retrieve all fund transfers
    List<FundTransfer> getAllTransfers();

    // Retrieve a specific transfer by ID
    Optional<FundTransfer> getTransferById(Long transferId);

    // Retrieve all transfers by sender account ID
    List<FundTransfer> getTransfersBySenderAccountId(Integer senderAccountId);

    // Retrieve all transfers by receiver account ID
    List<FundTransfer> getTransfersByReceiverAccountId(Integer receiverAccountId);
}
