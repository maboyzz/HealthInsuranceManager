package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.Exception.BadRequestValidationException;
import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.dto.Meta;
import com.nthuy.healthinsurancemanager.dto.request.CreateHealthInsuranceCardReq;
import com.nthuy.healthinsurancemanager.dto.request.RenewHealthCardReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.response.GetHealthInsuranceCardRes;
import com.nthuy.healthinsurancemanager.dto.response.GetSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.HealthInsuranceCardRepository;
import com.nthuy.healthinsurancemanager.repository.UserRepository;
import com.nthuy.healthinsurancemanager.repository.entity.HealthInsuranceCardEntity;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        UserEntity user = userRepository.findByIdCardNumber(request.getIdCardNumber())
                .orElseThrow(() -> new IdInvalidException("Người dùng với CCCD " + request.getIdCardNumber() + " không tồn tại"));

        long userId = user.getId();
        cardRepository.findByUserId(userId)
                .ifPresent(card -> {
                    throw new IllegalStateException("Người dùng với CCCD " + request.getIdCardNumber() + " đã có thẻ bảo hiểm");
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
    public ResultPaginationDTO handleGetAllHealthInsuranceCards(Specification<HealthInsuranceCardEntity> spec, Pageable pageable) {
        Page<HealthInsuranceCardEntity> card = cardRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPage(card.getTotalPages());
        meta.setTotal(card.getTotalElements());
        resultPaginationDTO.setMeta(meta);

        List<GetHealthInsuranceCardRes> cardRes = card.getContent().stream()
                .map(healthInsuranceCardEntity -> new GetHealthInsuranceCardRes(
                        healthInsuranceCardEntity.getCardId(),
                        healthInsuranceCardEntity.getCardNumber(),
                        healthInsuranceCardEntity.getIssueDate(),
                        healthInsuranceCardEntity.getExpirationDate(),
                        healthInsuranceCardEntity.getUser().getFullName(),
                        healthInsuranceCardEntity.getUser().getIdCardNumber(),
                        healthInsuranceCardEntity.getStatus()
                ))
                .toList();
        resultPaginationDTO.setResults(cardRes);
        return resultPaginationDTO;
    }
    public void renewHealthInsuranceCard(RenewHealthCardReq request) throws IdInvalidException {
        Optional<HealthInsuranceCardEntity> cardOptional = this.cardRepository.findByCardNumber(request.getCardNumber());
        if (cardOptional.isPresent()) {
            HealthInsuranceCardEntity card = cardOptional.get();
            card.setIssueDate(request.getIssueDate());
            card.setExpirationDate(request.getExpirationDate());
            this.cardRepository.save(card);
        } else {
            throw new IdInvalidException("Thẻ bảo hiểm với mã " + request.getCardNumber() + " không tồn tại");
        }
    }
}
