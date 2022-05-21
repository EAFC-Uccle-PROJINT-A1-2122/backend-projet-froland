package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYear;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYearRepository;

@RestController
@RequestMapping("/api/v1/academicYears")
public class AcademicYearController {
  private final AcademicYearRepository academicYearRepository;

  public AcademicYearController(AcademicYearRepository academicYearRepository) {
    this.academicYearRepository = academicYearRepository;
  }

  @GetMapping
  public List<AcademicYearTO> allCourses() {
    List<AcademicYear> academicYears = academicYearRepository.findAll();
    return academicYears.stream().map(AcademicYearController::convertEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AcademicYearTO> academicYearDetails(@PathVariable Long academicYearId) {
    AcademicYear academicYear = academicYearRepository.getById(academicYearId);
    AcademicYearTO academicYearTO = convertEntity(academicYear);
    return ResponseEntity.ok().eTag(Long.toString(academicYear.getVersion())).body(academicYearTO);
  }

  @PostMapping
  public ResponseEntity<?> createAcademicYear(@RequestBody AcademicYearTO academicYearTO, UriComponentsBuilder uriBuilder) {
    AcademicYear academicYear = convertTO(academicYearTO);
    Long id = academicYearRepository.save(academicYear).getId();
    URI newAcademicYearUri = uriBuilder.path("/api/v1/academicYears/{id}").build(id);
    return ResponseEntity.created(newAcademicYearUri).build();
  }

  private static AcademicYear convertTO(AcademicYearTO academicYearTO) {
    return new AcademicYear(academicYearTO.getBeginning(), academicYearTO.getEnd());
  }

  private static AcademicYearTO convertEntity(AcademicYear academicYear) {
    return new AcademicYearTO(academicYear.getId(), academicYear.getBeginning(), academicYear.getEnd());
  }
}
