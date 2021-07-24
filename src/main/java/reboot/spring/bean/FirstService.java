package reboot.spring.bean;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reboot.spring.bean.vo.FirstVo;


@NoArgsConstructor
@AllArgsConstructor
public class FirstService implements IFirstService {

    IFirstRepository firstRepository;

    @Override
    public void create(FirstVo firstVo) {
        firstRepository.create(firstVo);
    }

    @Override
    public void update(FirstVo firstVo) {
        firstRepository.update(firstVo);
    }

    @Override
    public List<FirstVo> list() {
        return firstRepository.list();
    }

    @Override
    public List<FirstVo> searchByName(String name) {
        return firstRepository.searchByName(name);
    }

    @Override
    public FirstVo getInfo(String loingId) {
        return firstRepository.getInfo(loingId);
    }

    @Override
    public void delete(String loginId) {
        firstRepository.delete(loginId);
    }
}
