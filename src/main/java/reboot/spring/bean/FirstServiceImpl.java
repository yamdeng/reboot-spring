package reboot.spring.bean;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reboot.spring.bean.vo.FirstVo;

@NoArgsConstructor
@AllArgsConstructor
public class FirstServiceImpl {

    private FirstDao firstDao;

    public void create(FirstVo firstVo) {
        firstDao.create(firstVo);
    }

    public void update(FirstVo firstVo) {
        firstDao.update(firstVo);
    }

    public List<FirstVo> list() {
        return firstDao.list();
    }

    public List<FirstVo> searchByName(String name) {
        return firstDao.searchByName(name);
    }

    public FirstVo getInfo(String loginId) {
        return firstDao.getInfo(loginId);
    }

    public void delete(String loginId) {
        firstDao.delete(loginId);
    }

}
