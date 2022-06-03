package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@ExtendWith(MockitoExtension.class)
public class SectionControllerTest {
  private static final String SECTION1_NAME = "Teacher";
  private static final String SECTION2_NAME = "Student";
  private static final SectionTO SECTION1_TO = new SectionTO(1L, SECTION1_NAME);

  @Mock
  private SectionRepository sectionRepository;
  @InjectMocks
  private SectionController sectionController;
  @Spy
  private Section section1 = new Section(SECTION1_NAME);
  private Section section2 = new Section(SECTION2_NAME);


  @Test
  public void allSections() {
    when(sectionRepository.findAll(Sort.by("name"))).thenReturn(List.of(section1, section2));

    ResponseEntity<List<SectionTO>> allSections = sectionController.allSections();

    assertEquals(2, allSections.getBody().size());
    assertEquals(SECTION1_NAME, allSections.getBody().get(0).getName());
    assertEquals(SECTION2_NAME, allSections.getBody().get(1).getName());
  }

  @Test
  public void createSection() {
    when(sectionRepository.save(any(Section.class))).thenReturn(section1);
    when(section1.getId()).thenReturn(123L);

    ResponseEntity<?> response = sectionController.createSection(SECTION1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/sections/123", response.getHeaders().getLocation().getPath());
  }

  @Test
  public void sectionDetails() {
    when(sectionRepository.getById(123L)).thenReturn(section1);
    when(section1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = sectionController.sectionDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(SECTION1_NAME, ((SectionTO) response.getBody()).getName());
  }
}
