package dekanat.service;

import dekanat.entity.LessonEntity;

import java.util.ArrayList;
import java.util.List;

public class LessonService {

    public LessonEntity getById(int id) {
        LessonEntity entity = new LessonEntity();
        entity.setCourseCathedraId(1);
        entity.setCourseId(1);
        entity.setLecturerCathedraId(1);
        entity.setSemesterId(1);
        entity.setType("Лекція");
        entity.setId(1);

        return entity;
    }

    public List<LessonEntity> getAll() {
        List<LessonEntity> entities = new ArrayList<>();
        entities.add(getById(1));
        entities.add(getById(2));

        return entities;
    }

    public void deleteById(int id) {
        return;
    }

    public void editById(int id) {
        return;
    }

}
