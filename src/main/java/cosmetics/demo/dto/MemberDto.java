package cosmetics.demo.dto;

import cosmetics.demo.Domain.Entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String password;

    public MemberEntity toEntity(){
        MemberEntity memberEntity = MemberEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
        return memberEntity;
    }

    public MemberDto(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
