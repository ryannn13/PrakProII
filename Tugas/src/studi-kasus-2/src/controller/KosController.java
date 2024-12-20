package controller;

import models.Kos;
import models.KosMapper;
import models.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import views.KosView;

public class KosController {
    private KosView kosView;

    public KosController(KosView kosView) {
        this.kosView = kosView;
    }

    public void showView() {
        kosView.loadData();
    }

    public void addKos(Kos kos) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KosMapper mapper = session.getMapper(KosMapper.class);
            mapper.insertKos(kos);
            session.commit();
        } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void updateKos(Kos kos) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KosMapper mapper = session.getMapper(KosMapper.class);
            mapper.updateKos(kos);
            session.commit();
        } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void deleteKos(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KosMapper mapper = session.getMapper(KosMapper.class);
            mapper.deleteKos(id);
            session.commit();
        } catch (Exception e) {
        e.printStackTrace();
    }
    }
}