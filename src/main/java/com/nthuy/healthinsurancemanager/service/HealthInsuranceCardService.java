package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.Exception.BadRequestValidationException;
import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.dto.request.CreateHealthInsuranceCardReq;
import com.nthuy.healthinsurancemanager.repository.HealthInsuranceCardRepository;
import com.nthuy.healthinsurancemanager.repository.UserRepository;
import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthInsuranceCardService {


    private final HealthInsuranceCardRepository cardRepository;
    private final UserRepository userRepository;

    public HealthInsuranceCardService(
            HealthInsuranceCardRepository cardRepository,
            UserRepository userRepository
    ) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public boolean existsById(Long id) {
        return cardRepository.existsById(id);
    }

    public Long createHealthInsuranceCard(CreateHealthInsuranceCardReq request) throws IdInvalidException {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IdInvalidException("Người dùng với ID " + request.getUserId() + " không tồn tại"));
        cardRepository.findByUserId(request.getUserId())
                .ifPresent(card -> {
                    throw new IllegalStateException("Người dùng với ID " + request.getUserId() + " đã có thẻ bảo hiểm");
                });
        if (cardRepository.existsByCardNumber(request.getCardNumber())) {
            throw new IllegalArgumentException("Mã thẻ " + request.getCardNumber() + " đã tồn tại");
        }
        HealthInsuranceCardEntity card = new HealthInsuranceCardEntity();
        card.setCardNumber(request.getCardNumber());
        card.setIssueDate(request.getIssueDate());
        card.setExpirationDate(request.getExpirationDate());
        card.setUser(user);
        card = cardRepository.save(card);
        return card.getCardId();
    }

    public void deleteHealthInsuranceCard(Long id)  {
        HealthInsuranceCardEntity card = cardRepository.findById(id)
                .orElseThrow(() -> new BadRequestValidationException("Thẻ bảo hiểm với ID " + id + " không tồn tại"));
        // Xóa HealthInsuranceCard
        cardRepository.delete(card);
    }
    public List<HealthInsuranceCardEntity> handleGetAllHealthInsuranceCards() {
        return cardRepository.findAll();
    }
}
