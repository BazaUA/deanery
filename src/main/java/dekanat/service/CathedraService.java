package dekanat.service;

import dekanat.dao.CathedraDAO;
import dekanat.entity.CathedraEntity;

import java.util.List;

public class CathedraService {
    private CathedraDAO cathedraDAO;

    public CathedraService() {
        cathedraDAO = new CathedraDAO();
    }

    public CathedraEntity getById(int id) {

        return cathedraDAO.getById(id);
    }

    public List<CathedraEntity> getAll() {
        return cathedraDAO.getAll();
    }

}
