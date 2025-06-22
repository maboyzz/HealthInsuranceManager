package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.dto.request.CreateHealthInsuranceCardReq;
import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import com.nthuy.healthinsurancemanager.service.HealthInsuranceCardService;
import com.nthuy.healthinsurancemanager.until.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthInsuranceCardController {

    private final HealthInsuranceCardService cardService;

    public HealthInsuranceCardController(HealthInsuranceCardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    @ApiMessage("Tạo thẻ bảo hiểm y tế")
    public ResponseEntity<String> createHealthInsuranceCard(
            @Valid @RequestBody CreateHealthInsuranceCardReq request
    )  {
        Long cardId = cardService.createHealthInsuranceCard(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tạo thẻ bảo hiểm thành công với ID = "+cardId);
    }

    @DeleteMapping("/cards/{id}")
    @ApiMessage("Xoá thẻ bảo hiểm y tế")
    public ResponseEntity<String> deleteHealthInsuranceCard(
            @PathVariable Long id
    ) {
        boolean isValidId = cardService.existsById(id);
        if (!isValidId) {
            throw new IdInvalidException("ID " + id + " không hợp lệ");
        }
        cardService.deleteHealthInsuranceCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xoá người dùng thành công");
    }
    @GetMapping("/cards")
    @ApiMessage("Lấy danh sách thẻ bảo hiểm y tế")
    public ResponseEntity<List<HealthInsuranceCardEntity>> getAllHealthInsuranceCards() {
        return ResponseEntity.ok(this.cardService.handleGetAllHealthInsuranceCards());
    }

}