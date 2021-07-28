package reboot.spring.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import reboot.spring.boot.domain.Member;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM MEMBER WHERE email = #{state}")
    public Member findByEmail(@Param("email") String email);

}
