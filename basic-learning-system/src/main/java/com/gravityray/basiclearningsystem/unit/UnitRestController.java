package com.gravityray.basiclearningsystem.unit;


import com.gravityray.basiclearningsystem.course.CourseConverter;
import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.unit.model.UnitDto;
import com.gravityray.basiclearningsystem.unit.model.UnitEntity;
import com.gravityray.basiclearningsystem.unit.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UnitRestController {

    private final UnitService unitService;
    private final CourseConverter courseConverter;

    public UnitRestController(
            UnitService unitService,
            CourseConverter courseConverter) {
        this.unitService = unitService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/unit/{unitId}")
    public UnitDto getUnit(@PathVariable long unitId) {
        UnitEntity unitEntity = unitService.getUnit(unitId);
        UnitDto unitDto = courseConverter.toDto(unitEntity);

        unitDto.setLessons(
                unitService.getUnitLessons(unitId)
                        .stream()
                        .map(courseConverter::toDto)
                        .collect(Collectors.toList()));

        return unitDto;
    }

    @PostMapping("/unit")
    public UnitDto createUnit(@RequestBody UnitDto unit) {
        long unitId = unitService.addUnit(courseConverter.toEntity(unit));
        unit.setId(unitId);
        return unit;
    }

    @PutMapping("/unit")
    public void updateUnit(@RequestBody UnitDto unit) {
        unitService.updateUnit(courseConverter.toEntity(unit));
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
