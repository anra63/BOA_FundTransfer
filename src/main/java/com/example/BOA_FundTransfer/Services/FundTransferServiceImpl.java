package com.example.BOA_FundTransfer.Services;

import com.example.BOA_FundTransfer.Model.FundTransfer;
import com.example.BOA_FundTransfer.Repositories.FundTransferRepository;
import com.example.BOA_FundTransfer.Services.Interfcaes.FundTransferService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundTransferServiceImpl implements FundTransferService {

    private final FundTransferRepository fundTransferRepository;
    private static final Logger logger = LogManager.getLogger(FundTransferServiceImpl.class);

    @Override
    public FundTransfer createTransfer(FundTransfer fundTransfer) {
        logger.info("Initiating transfer from account {} to account {}", fundTransfer.getSenderAccountId(), fundTransfer.getReceiverAccountId());
        FundTransfer savedTransfer = fundTransferRepository.save(fundTransfer);
        logger.info("Fund transfer created with transfer ID: {}", savedTransfer.getTransferId());
        return savedTransfer;
    }

    @Override
    public List<FundTransfer> findAllBySenderAccountId(Integer senderAccountId) {
        return List.of();
    }

    @Override
    public List<FundTransfer> findAllByReceiverAccountId(Integer receiverAccountId) {
        return List.of();
    }

    @Override
    public List<FundTransfer> getAllTransfers() {
        logger.info("Fetching all fund transfers");
        return fundTransferRepository.findAll();
    }

    @Override
    public Optional<FundTransfer> getTransferById(Long transferId) {
        logger.info("Fetching fund transfer with transfer ID: {}", transferId);
        return fundTransferRepository.findById(transferId);
    }

    @Override
    public List<FundTransfer> getTransfersBySenderAccountId(Integer senderAccountId) {
        logger.info("Fetching fund transfers by sender account ID: {}", senderAccountId);
        return fundTransferRepository.findAllBySenderAccountId(senderAccountId);
    }

    @Override
    public List<FundTransfer> getTransfersByReceiverAccountId(Integer receiverAccountId) {
        logger.info("Fetching fund transfers by receiver account ID: {}", receiverAccountId);
        return fundTransferRepository.findAllByReceiverAccountId(receiverAccountId);
    }
}
