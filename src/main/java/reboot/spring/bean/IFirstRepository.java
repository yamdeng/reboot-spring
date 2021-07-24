package reboot.spring.bean;

import java.util.List;
import reboot.spring.bean.vo.FirstVo;

public interface IFirstRepository {

    public void create(FirstVo firstVo);

    public void update(FirstVo firstVo);

    public List<FirstVo> list();

    public List<FirstVo> searchByName(String name);

    public FirstVo getInfo(String loginId);

    public void delete(String loginId);

}
