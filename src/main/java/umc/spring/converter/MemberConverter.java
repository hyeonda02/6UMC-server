package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.MemberGender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

}
