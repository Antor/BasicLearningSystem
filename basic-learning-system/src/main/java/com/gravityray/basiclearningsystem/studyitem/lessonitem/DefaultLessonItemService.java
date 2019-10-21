package com.gravityray.basiclearningsystem.studyitem.lessonitem;

import com.gravityray.basiclearningsystem.adminpanel.lessonitem.CreateLessonItemForm;
import com.gravityray.basiclearningsystem.adminpanel.lessonitem.EditLessonItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultLessonItemService implements LessonItemService {

    private final LessonItemDao lessonItemDao;
    private final Validator validator;

    public DefaultLessonItemService(LessonItemDao lessonItemDao, Validator validator) {
        this.lessonItemDao = lessonItemDao;
        this.validator = validator;
    }

    @Override
    public LessonItemEntity getLessonItem(long id) {
        return lessonItemDao.findById(id).orElse(null);
    }

    @Override
    public long addLessonItem(LessonItemEntity lessonItemEntity) {
        return lessonItemDao.save(lessonItemEntity).getId();
    }

    @Override
    public void updateLessonItem(LessonItemEntity lessonItemEntity) {
        lessonItemDao.save(lessonItemEntity);
    }

    @Override
    public void changeLessonItemOrdinal(long lessonItemId, int delta) {
        // TODO
    }

    @Override
    public void deleteLessonItem(long id) {
        lessonItemDao.deleteById(id);
    }

    @Override
    public void completeLessonItem(long userId, long lessonItemId) {
        // TODO
    }

    @Transactional
    @Override
    public void createLessonItem(long lessonId, CreateLessonItemForm createLessonItemForm)
        throws CreateLessonItemFormNotValidException {
        Set<ConstraintViolation<CreateLessonItemForm>> constraintViolationSet =
                validator.validate(createLessonItemForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new CreateLessonItemFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }

        LessonItemEntity lessonItemEntity = new LessonItemEntity();
        lessonItemEntity.setTitle(createLessonItemForm.getTitle());
        lessonItemEntity.setContent(createLessonItemForm.getContent());
        lessonItemEntity.setOrdinal(0);// TODO: set proper value
        lessonItemEntity.setLessonId(lessonId);

        lessonItemDao.save(lessonItemEntity);
    }

    @Transactional
    @Override
    public void updateLessonItem(EditLessonItemForm editLessonItemForm)
            throws EditLessonItemFormNotValidException {
        Set<ConstraintViolation<EditLessonItemForm>> constraintViolationSet =
                validator.validate(editLessonItemForm);
        if (!constraintViolationSet.isEmpty()) {
            throw new EditLessonItemFormNotValidException(
                    constraintViolationSet.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList()));
        }
        lessonItemDao.findById(editLessonItemForm.getId())
                .ifPresent(lessonItemEntity -> {
                    lessonItemEntity.setTitle(editLessonItemForm.getTitle());
                    lessonItemEntity.setContent(editLessonItemForm.getContent());
                });
    }
}
