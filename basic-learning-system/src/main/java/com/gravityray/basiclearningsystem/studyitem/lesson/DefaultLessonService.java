package com.gravityray.basiclearningsystem.studyitem.lesson;

import com.gravityray.basiclearningsystem.adminpanel.unit.LessonCreateInfo;
import com.gravityray.basiclearningsystem.adminpanel.lesson.EditLessonForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultLessonService implements LessonService {

    private final LessonDao lessonDao;
    private final Validator validator;

    public DefaultLessonService(LessonDao lessonDao, Validator validator) {
        this.lessonDao = lessonDao;
        this.validator = validator;
    }

    @Override
    public LessonEntity getLesson(long id) {
        return lessonDao.findById(id).orElse(null);
    }

    @Override
    public long addLesson(LessonEntity lessonEntity) {
        return lessonDao.save(lessonEntity).getId();
    }

    @Override
    public void updateLesson(LessonEntity lessonEntity) {
        lessonDao.save(lessonEntity);
    }

    @Override
    public void changeLessonOrdinal(long lessonId, int delta) {
        // TODO
    }

    @Override
    public void deleteLesson(long id) {
        lessonDao.deleteById(id);
    }



    @Transactional
    @Override
    public void updateLesson(EditLessonForm editLessonForm) throws EditLessonFormNotValidException {
        Set<ConstraintViolation<EditLessonForm>> constraintViolationSet =
                validator.validate(editLessonForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditLessonFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }
        lessonDao.findById(editLessonForm.getId())
                .ifPresent(unitEntity -> {
                    unitEntity.setTitle(editLessonForm.getTitle());
                    unitEntity.setDescription(editLessonForm.getDescription());
                });
    }
}
