package umc.spring.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MemberGender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        MemberGender gender = null;
        switch (request.getGender()) {
            case 1:
                gender = MemberGender.M;
                break;
            case 2:
                gender = MemberGender.F;
                break;
            case 3:
                gender = MemberGender.N;
                break;
        }
        return Member.builder()
                .gender(gender)
                .address(request.getAddress())
                .spec_address(request.getSpecAddress())
                .memberPreferList(new ArrayList<>())
                .name(request.getName())
                .build();

    }

    public static MemberResponseDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(Review review) {
        return MemberResponseDTO.MyReviewPreviewDTO.builder()
                .score(review.getReviewGrade())
                .content(review.getReviewContent())
                .ownerNickname(review.getMember().getName())
                .createdAt(review.getCreatedAt().toLocalDate().atStartOfDay())
                .storeName(review.getStore().getStoreName())
                .build();
    }

    public static MemberResponseDTO.MyReviewPreviewListDTO toMyReviewPreviewListDTO(Page<Review> myReviewList) {
        List<MemberResponseDTO.MyReviewPreviewDTO> reviewPreviewDTOList = myReviewList.stream()
                .map(MemberConverter::toMyReviewPreviewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MyReviewPreviewListDTO.builder()
                .isLast(myReviewList.isLast())
                .isFirst(myReviewList.isFirst())
                .totalPage(myReviewList.getTotalPages())
                .totalElements(myReviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();

    }

}
