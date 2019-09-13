package com.gravityray.basiclearningsystem.course;


import com.gravityray.basiclearningsystem.course.model.ChangeOrdinalRequest;
import com.gravityray.basiclearningsystem.course.model.UnitDto;
import com.gravityray.basiclearningsystem.course.model.UnitEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UnitRestController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    public UnitRestController(
            CourseService courseService,
            CourseConverter courseConverter) {
        this.courseService = courseService;
        this.courseConverter = courseConverter;
    }

    @GetMapping("/unit/{unitId}")
    public UnitDto getUnit(@PathVariable long unitId) {
        UnitEntity unitEntity = courseService.getUnit(unitId);
        UnitDto unitDto = courseConverter.toDto(unitEntity);

        unitDto.setLessons(
                courseService.getUnitLessons(unitId)
                        .stream()
                        .map(courseConverter::toDto)
                        .collect(Collectors.toList()));

        return unitDto;
    }

    @PostMapping("/unit")
    public UnitDto createUnit(@RequestBody UnitDto unit) {
        long unitId = courseService.addUnit(courseConverter.toEntity(unit));
        unit.setId(unitId);
        return unit;
    }

    @PutMapping("/unit")
    public void updateUnit(@RequestBody UnitDto unit) {
        courseService.updateUnit(courseConverter.toEntity(unit));
    }

    @PostMapping("/change-unit-ordinal")
    public void changeUnitOrdinal(@RequestBody ChangeOrdinalRequest request) {
        courseService.changeUnitOrdinal(request.getId(), request.getDelta());
    }

    @DeleteMapping("/unit/{unit_id}")
    public void deleteUnit(@PathVariable("unit_id") long unitId) {
        courseService.deleteUnit(unitId);
    }
}
