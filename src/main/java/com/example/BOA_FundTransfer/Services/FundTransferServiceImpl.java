package com.example.BOA_FundTransfer.Services;

import com.example.BOA_AccountService.Model.Account;
import com.example.BOA_FundTransfer.Model.FundTransfer;
import com.example.BOA_FundTransfer.Repositories.FundTransferRepository;
import com.example.BOA_FundTransfer.Services.Interfcaes.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    private static final Logger logger = LogManager.getLogger(FundTransferServiceImpl.class);

    private final FundTransferRepository fundTransferRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public FundTransferServiceImpl(FundTransferRepository fundTransferRepository, RestTemplate restTemplate) {
        this.fundTransferRepository = fundTransferRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public FundTransfer createTransfer(FundTransfer fundTransfer) {
        logger.info("Initiating fund transfer from account {} to account {}", fundTransfer.getSenderAccountId(), fundTransfer.getReceiverAccountId());

        // Fetch sender and receiver accounts
        String senderAccountUrl = "http://localhost:8081/account/" + fundTransfer.getSenderAccountId();
        String receiverAccountUrl = "http://localhost:8081/account/" + fundTransfer.getReceiverAccountId();

        Account senderAccount = restTemplate.getForObject(senderAccountUrl, Account.class);
        Account receiverAccount = restTemplate.getForObject(receiverAccountUrl, Account.class);

        if (senderAccount == null || receiverAccount == null) {
            throw new RuntimeException("Sender or receiver account not found");
        }

        // Check if sender has enough balance
        if (senderAccount.getBalance() < fundTransfer.getAmount()) {
            throw new RuntimeException("Insufficient balance in sender's account");
        }

        // Perform transfer
        senderAccount.setBalance(senderAccount.getBalance() - fundTransfer.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() + fundTransfer.getAmount());

        // Update accounts (you should save the updated sender and receiver account here)
        restTemplate.put(senderAccountUrl, senderAccount);
        restTemplate.put(receiverAccountUrl, receiverAccount);

        // Set transfer status and save the transfer record
        fundTransfer.setStatus("SUCCESS");
        FundTransfer savedTransfer = fundTransferRepository.save(fundTransfer);

        logger.info("Fund transfer completed successfully. Transfer ID: {}", savedTransfer.getTransferId());
        return savedTransfer;
    }

    @Override
    public Optional<FundTransfer> getTransfer(Long transferId) {
        logger.info("Fetching fund transfer with ID: {}", transferId);
        return Optional.ofNullable(fundTransferRepository.findById(transferId).orElse(null));
    }

    @Override
    public List<FundTransfer> getAllTransfers() {
        logger.info("Fetching all fund transfers");
        return fundTransferRepository.findAll();
    }

    @Override
    public List<FundTransfer> findAllBySenderAccountId(Integer senderAccountId) {
        // Implementation for retrieving transfers by sender account ID
        return fundTransferRepository.findBySenderAccountId(senderAccountId);
    }

    @Override
    public List<FundTransfer> findAllByReceiverAccountId(Integer receiverAccountId) {
        // Implementation for retrieving transfers by receiver account ID
        return fundTransferRepository.findByReceiverAccountId(receiverAccountId);
    }

    @Override
    public List<FundTransfer> getTransfersBySenderAccountId(Integer senderAccountId) {
        // Implementation for retrieving transfers by sender account ID
        return fundTransferRepository.findBySenderAccountId(senderAccountId);
    }

    @Override
    public List<FundTransfer> getTransfersByReceiverAccountId(Integer receiverAccountId) {
        // Implementation for retrieving transfers by receiver account ID
        return fundTransferRepository.findByReceiverAccountId(receiverAccountId);
    }

    @Override
    public Optional<FundTransfer> getTransferById(Long transferId) {
        return Optional.empty();
    }
}

