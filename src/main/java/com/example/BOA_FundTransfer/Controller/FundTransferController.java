package com.example.BOA_FundTransfer.Controller;

import com.example.BOA_FundTransfer.Model.FundTransfer;
import com.example.BOA_FundTransfer.Services.Interfcaes.FundTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fund-transfer")
@RequiredArgsConstructor
public class FundTransferController {

    private final FundTransferService fundTransferService;

    // Create a fund transfer
    @PostMapping
    public ResponseEntity<FundTransfer> createTransfer(@RequestBody FundTransfer fundTransfer) {
        FundTransfer createdTransfer = fundTransferService.createTransfer(fundTransfer);
        return new ResponseEntity<>(createdTransfer, HttpStatus.CREATED);
    }

    // Get all fund transfers
    @GetMapping
    public ResponseEntity<List<FundTransfer>> getAllTransfers() {
        List<FundTransfer> transfers = fundTransferService.getAllTransfers();
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    // Get a specific fund transfer by transfer ID
    @GetMapping("/{transferId}")
    public ResponseEntity<FundTransfer> getTransferById(@PathVariable Long transferId) {
        Optional<FundTransfer> transfer = fundTransferService.getTransferById(transferId);
        return transfer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all fund transfers by sender account ID
    @GetMapping("/sender/{senderAccountId}")
    public ResponseEntity<List<FundTransfer>> getTransfersBySenderAccountId(@PathVariable Integer senderAccountId) {
        List<FundTransfer> transfers = fundTransferService.getTransfersBySenderAccountId(senderAccountId);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    // Get all fund transfers by receiver account ID
    @GetMapping("/receiver/{receiverAccountId}")
    public ResponseEntity<List<FundTransfer>> getTransfersByReceiverAccountId(@PathVariable Integer receiverAccountId) {
        List<FundTransfer> transfers = fundTransferService.getTransfersByReceiverAccountId(receiverAccountId);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }
}
