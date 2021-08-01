package reboot.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import reboot.spring.bean.vo.FirstVo;

import java.util.List;

public class FirstRepository implements IFirstRepository {

    @Autowired
    FirstDao firstDao;

    @Override
    public void create(FirstVo firstVo) {
        firstDao.create(firstVo);
    }

    @Override
    public void update(FirstVo firstVo) {
        firstDao.update(firstVo);
    }

    @Override
    public List<FirstVo> list() {
        return firstDao.list();
    }

    @Override
    public List<FirstVo> searchByName(String name) {
        return firstDao.searchByName(name);
    }

    @Override
    public FirstVo getInfo(String loginId) {
        return firstDao.getInfo(loginId);
    }

    @Override
    public void delete(String loginId) {
        firstDao.delete(loginId);
    }
}
