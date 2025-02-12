package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    Page<Review> getMyReviewList(Long memberId, Integer page);
    MemberResponseDTO.MyReviewPreviewListDTO getMyReviewPreviewList(Page<Review> reviewList);
}
