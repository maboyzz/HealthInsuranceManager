package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.dto.request.CreateHealthInsuranceCardReq;
import com.nthuy.healthinsurancemanager.dto.request.RenewHealthCardReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateHealthCardReq;
import com.nthuy.healthinsurancemanager.dto.response.GetCardByIdRes;
import com.nthuy.healthinsurancemanager.dto.response.GetHealthInsuranceCardRes;
import com.nthuy.healthinsurancemanager.dto.response.RestResponse;
import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import com.nthuy.healthinsurancemanager.service.HealthInsuranceCardService;
import com.nthuy.healthinsurancemanager.until.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public ResponseEntity<RestResponse<Long>> createHealthInsuranceCard(
            @Valid @RequestBody CreateHealthInsuranceCardReq request
    ) {
        Long cardId = cardService.createHealthInsuranceCard(request);
        RestResponse<Long> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Tạo thẻ bảo hiểm thành công");
        response.setData(cardId);
        // Set errorCode if needed
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/cards/renew")
    @ApiMessage("gia han thẻ bảo hiểm y tế")
    public ResponseEntity<RestResponse<Long>> renewHealthInsuranceCard(
            @Valid @RequestBody RenewHealthCardReq request
    ) {

         this.cardService.renewHealthInsuranceCard(request);
        RestResponse<Long> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Gia hạn thẻ bảo hiểm thành công");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xoá thành công");
    }
    @GetMapping("/cards")
    @ApiMessage("Lấy danh sách thẻ bảo hiểm y tế")
    public ResponseEntity<ResultPaginationDTO> getAllHealthInsuranceCards(
            @Filter Specification<HealthInsuranceCardEntity> spec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(this.cardService.handleGetAllHealthInsuranceCards(spec, pageable));
    }
    @PutMapping("/cards/{id}")
    @ApiMessage("Cập nhật thông tin thẻ bảo hiểm y tế")
    public ResponseEntity<RestResponse<HealthInsuranceCardEntity>> updateHealthInsuranceCard(
            @PathVariable long id, @Valid @RequestBody UpdateHealthCardReq healthInsuranceCard
    ) {
        this.cardService.updateHealthInsuranceCar(healthInsuranceCard, id);
        RestResponse<HealthInsuranceCardEntity> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Cập nhật thẻ bảo hiểm thành công");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/cards/{id}")
    @ApiMessage("Lấy thông tin thẻ bảo hiểm y tế")
    public ResponseEntity<GetCardByIdRes> getHealthInsuranceCardById(
            @PathVariable Long id
    ) {
        GetCardByIdRes card = cardService.getHealthInsuranceCardById(id);
        return ResponseEntity.ok(card);
    }
    @PutMapping("/cards/{cardNumber}/close")
    @ApiMessage("Đóng thẻ bảo hiểm y tế")
    public ResponseEntity<RestResponse<Void>> closeHealthCard(@PathVariable String cardNumber) {
        cardService.closeHealthInsuranceCard(cardNumber);

        RestResponse<Void> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Đóng thẻ thành công");
        response.setErrorCode(null);
        response.setData(null);

        return ResponseEntity.ok(response);
    }


}