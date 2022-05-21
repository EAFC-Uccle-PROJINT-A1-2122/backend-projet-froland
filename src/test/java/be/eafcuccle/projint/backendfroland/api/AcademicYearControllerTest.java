package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYear;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYearRepository;

@ExtendWith(MockitoExtension.class)
public class AcademicYearControllerTest {
  private static final LocalDate YEAR1_BEGINNING = LocalDate.of(2021, 9, 1);
  private static final LocalDate YEAR1_END = LocalDate.of(2022, 6, 30);
  private static final LocalDate YEAR2_BEGINNING = LocalDate.of(2020, 9, 1);
  private static final LocalDate YEAR2_END = LocalDate.of(2021, 6, 30);
  private static final AcademicYearTO ROLE1_TO = new AcademicYearTO(1L, YEAR1_BEGINNING, YEAR1_END);

  @Mock
  private AcademicYearRepository academicYearRepository;
  @InjectMocks
  private AcademicYearController academicYearController;
  @Spy
  private AcademicYear academicYear1 = new AcademicYear(YEAR1_BEGINNING, YEAR1_END);
  private AcademicYear academicYear2 = new AcademicYear(YEAR2_BEGINNING, YEAR2_END);

  @Test
  public void academicYearDetails() {
    when(academicYearRepository.getById(123L)).thenReturn(academicYear1);
    when(academicYear1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = academicYearController.academicYearDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(YEAR1_BEGINNING, ((AcademicYearTO) response.getBody()).getBeginning());
  }

  @Test
  public void allAcademicYears() {
    when(academicYearRepository.findAll()).thenReturn(List.of(academicYear1, academicYear2));

    List<AcademicYearTO> allAcademicYears = academicYearController.allAcademicYears();

    assertEquals(2, allAcademicYears.size());
    assertEquals(YEAR1_BEGINNING, allAcademicYears.get(0).getBeginning());
    assertEquals(YEAR2_END, allAcademicYears.get(1).getEnd());
  }

  @Test
  public void createAcademicYear() {
    when(academicYearRepository.save(any(AcademicYear.class))).thenReturn(academicYear1);
    when(academicYear1.getId()).thenReturn(123L);

    ResponseEntity<?> response = academicYearController.createAcademicYear(ROLE1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/academicYears/123", response.getHeaders().getLocation().getPath());
  }
}
