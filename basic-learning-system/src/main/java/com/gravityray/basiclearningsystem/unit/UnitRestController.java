package com.gravityray.basiclearningsystem.unit;


import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.lesson.LessonConverter;
import com.gravityray.basiclearningsystem.unit.model.UnitDto;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import com.gravityray.basiclearningsystem.unit.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UnitRestController {

    private final UnitService unitService;
    private final UnitConverter unitConverter;
    private final LessonConverter lessonConverter;

    public UnitRestController(
            UnitService unitService,
            UnitConverter unitConverter,
            LessonConverter lessonConverter) {
        this.unitService = unitService;
        this.unitConverter = unitConverter;
        this.lessonConverter = lessonConverter;
    }

    @GetMapping("/unit/{unitId}")
    public UnitDto getUnit(@PathVariable long unitId) {
        UnitEntity unitEntity = unitService.getUnit(unitId);
        UnitDto unitDto = unitConverter.toDto(unitEntity);

        unitDto.setLessons(
                unitEntity.getLessonList()
                        .stream()
                        .map(lessonConverter::toDto)
                        .collect(Collectors.toList()));

        return unitDto;
    }

    @PostMapping("/unit")
    public UnitDto createUnit(@RequestBody UnitDto unit) {
        long unitId = unitService.addUnit(unitConverter.toEntity(unit));
        unit.setId(unitId);
        return unit;
    }

    @PutMapping("/unit")
    public void updateUnit(@RequestBody UnitDto unit) {
        unitService.updateUnit(unitConverter.toEntity(unit));
    }

    @PostMapping("/change-unit-ordinal")
    public void changeUnitOrdinal(@RequestBody ChangeOrdinalRequest request) {
        unitService.changeUnitOrdinal(request.getId(), request.getDelta());
    }

    @DeleteMapping("/unit/{unit_id}")
    public void deleteUnit(@PathVariable("unit_id") long unitId) {
        unitService.deleteUnit(unitId);
    }
}
